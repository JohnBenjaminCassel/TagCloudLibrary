package com.johnbenjamincassel.taglibs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

import com.johnbenjamincassel.conditionable.AbstractExpression;
import com.johnbenjamincassel.conditionable.ConditionalExpressionInterface;
import com.johnbenjamincassel.conditionable.Variable;
import com.johnbenjamincassel.utilities.CollectionPrinter;
import com.johnbenjamincassel.utilities.datastructures.HashToHashSet;
import com.johnbenjamincassel.utilities.exceptions.UndefinedOperatorException;
import com.johnbenjamincassel.utilities.simpleMath.ValueExpression;

public class CloudExpression implements ConditionalExpressionInterface, Cloudlike {

	private static long s_cloud_variable_id;
	static {
		 s_cloud_variable_id = 1;
	}
	
	protected synchronized long getNextId() {
		long id = s_cloud_variable_id;
		s_cloud_variable_id++;
		return id;
	}
	ArrayList<Variable> m_tag_variables = new ArrayList<Variable>();
	ArrayList<CloudExpression> m_children
		= new ArrayList<CloudExpression>();
	HashSet<String> m_tags = new HashSet<String>();
	HashMap<String, ValueExpression> m_tag_weight_expressions
		= new HashMap<String, ValueExpression>();
	private Variable<Cloudable> m_cloud_variable;
	
	public CloudExpression() {
		String var_name = "autogen_cloud_variable" + getNextId();
		m_cloud_variable = new Variable<Cloudable>(var_name, Cloudable.class);
	}
	
	public boolean bind(String v, Object o) {
		if(m_cloud_variable.bind(v, o)) {
			//System.out.println("binding "+o+" to "+v);
			HashSet<Cloudable> bindings = m_cloud_variable.getBindValues();
			if(bindings == null) {
				return false;
			}
			for(Cloudable cl : bindings) {
				if(!m_children.isEmpty()) {
					throw new RuntimeException("Figure out CloudExpression child binding");
				}
				HashSet<String> remaining_tags 
					= cl.getLevelwiseCloud().getTags();
				remaining_tags.removeAll(m_tags);
				for(Variable t_var : m_tag_variables) {			
					t_var.addConstraintSet(v, remaining_tags);
					HashSet<Object> bind_values 
						= new HashSet<Object>(t_var.getBindValues());
					bind_values.removeAll(remaining_tags);
					if(! bind_values.isEmpty()) {
						CollectionPrinter.printCollection(bind_values);
						throw new RuntimeException("bad bind values");
					}
				}
			}
			
			return true;
		}
		return false;
	}

	public boolean canBeSatisfied()  {
		if(!m_cloud_variable.canBeSatisfied()) { 
			return false; 
		}
		if(!m_cloud_variable.isBound()) { return true; }
		boolean was_satisfied = false;
		HashSet<Cloudable> bind_values = m_cloud_variable.getBindValues();
		
		for(Cloudable c : bind_values) {
			TagCloud levelwise_cloud = c.getLevelwiseCloud();
			if(levelwise_cloud.hasTags(m_tags)) {
				boolean satisfied_expressions = true;
				for(String tag_w_weight_expression : m_tag_weight_expressions.keySet()) {
					double tag_weight = levelwise_cloud.getTagWeight(tag_w_weight_expression);
					try {
						if(! m_tag_weight_expressions.get(tag_w_weight_expression).evaluate(tag_weight)) {
							satisfied_expressions = false;
							break;
						}
					} catch (UndefinedOperatorException e) {
						e.printStackTrace();
						return false;
					}
				}
				if(satisfied_expressions) {
					was_satisfied = true;
					break;
				}
			}
		}
		if(! was_satisfied) {
			return false;
		}
		for(Variable t_var: m_tag_variables) {
			if(! t_var.canBeSatisfied()) {
				return false;
			}
		}
		for(CloudExpression child : m_children) {
			if(! child.canBeSatisfied()) {
				return false;
			}
		}
		return true;
		
		
	}


	public void collectVariables(HashSet<String> set) {
		m_cloud_variable.collectVariables(set);
		for(Variable t_var : m_tag_variables) { 
			t_var.collectVariables(set);
		}
		for(CloudExpression child : m_children) {
			child.collectVariables(set);
		}
	}

	@Override
	public boolean isSatisfied() {
		if(! canBeSatisfied()) {
			return false;
		}
		if(!m_cloud_variable.isSatisfied()) { 
			return false; 
		}
		for(Variable t_var: m_tag_variables) {
			if(! t_var.isSatisfied()) {
				return false;
			}
		}
		for(CloudExpression child : m_children) {
			if(! child.isSatisfied()) {
				return false;
			}
		}
		return true;
	}

	@Override
	public void unbind(String v, Object o) {
		for(Variable t_var : m_tag_variables) { 
			t_var.unbind(v, o);
		}
		for(CloudExpression child : m_children) {
			child.unbind(v, o);
		}
		m_cloud_variable.unbind(v, o);
	}

	public void addTag(String tag) {
		m_tags.add(tag);
		
	}

	public void addTagVariable(Variable va) {
		m_tag_variables.add(va);
	}

	public void addChildExpression(CloudExpression child) {
		m_children.add(child);
	}

	
	
	@Override
	public HashSet<Object> getBindings(String var) {
		HashSet<Object> bindings = m_cloud_variable.getBindings(var);
		for(Variable t_var : m_tag_variables) { 
			HashSet<Object> arg_bindings = t_var.getBindings(var);
			if(arg_bindings == null) { continue; }
			if(bindings != null) {
				bindings.retainAll(arg_bindings);
			}
			else {
				bindings=arg_bindings;
			}
		}
		for(CloudExpression child : m_children) {
			HashSet<Object> arg_bindings = child.getBindings(var);
			if(arg_bindings == null) { continue; }
			if(bindings != null) {
				bindings.retainAll(arg_bindings);
			}
			else {
				bindings=arg_bindings;
			}
		}
		return bindings;
	}


	@Override
	public HashToHashSet<String, Object> getAllBindings() {
		HashSet<String> variables = new HashSet<String>();
		collectVariables(variables);
		HashToHashSet<String, Object> all_bindings = new HashToHashSet<String, Object>();
		for(String var: variables) {
			all_bindings.putAll(var, getBindings(var));
		}
		return all_bindings;
	}

	@Override
	public HashSet<ConditionalExpressionInterface> getConditions(HashSet<ConditionalExpressionInterface> set) {
		set.add(this);
		return set;
	}

	public void addTags(Collection<String> tags) {
		for(String tag : tags) {
			addTag(tag);
		}
		
	}

	@Override
	public HashSet<AbstractExpression> collectSubexpressions(
			HashSet<AbstractExpression> subexpressions) {
		subexpressions.add(this);
		for(Variable v : m_tag_variables) {
			v.collectSubexpressions(subexpressions);
		}
		m_cloud_variable.collectSubexpressions(subexpressions);
		for(CloudExpression child : m_children) {
			child.collectSubexpressions(subexpressions);
		}
		return subexpressions;
	}

	

	public void setTagValueExpression(String tag, ValueExpression ve) {
		m_tag_weight_expressions.put(tag, ve);
		
	}
}

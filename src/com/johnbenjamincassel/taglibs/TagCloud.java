package com.johnbenjamincassel.taglibs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Map;
import java.util.Set;

import com.johnbenjamincassel.conditionable.Conditionable;
import com.johnbenjamincassel.utilities.FilterType;
import com.johnbenjamincassel.utilities.PointFilter;
import com.johnbenjamincassel.utilities.datastructures.WeightedThing;
import com.johnbenjamincassel.utilities.exceptions.UndefinedOperatorException;
import com.johnbenjamincassel.utilities.simpleMath.ValueExpression;

public class TagCloud implements Cloudable, Conditionable, Cloneable, Comparable<TagCloud> {

	// ----------------- static methods ----------------------
	
	public static TagCloud maxCloud(TagCloud x, TagCloud y) {
		TagCloud tc = new TagCloud();
		tc.addIndexedTags(x.getIndexedTags());
		for(Long tag : y.getTagIndexes()) {
			Double x_val = tc.getTagValue(tag);
			Double y_val = y.getTagValue(tag);
			if(x_val == null || Math.abs(x_val) < Math.abs(y_val)) {
				tc.addWeightedTag(tag, y_val);
			}
		}
		return tc;
	}

	public static TagCloud weightedJoin(TagCloud x, TagCloud y, double alpha) {
		try {
			TagCloud x_clone = (TagCloud) x.clone();
			x_clone.scale(alpha);
			TagCloud y_clone = (TagCloud) y.clone();
			y_clone.scale(1-alpha);
			x_clone.transformByCloud(y_clone);
			return x_clone;
		} catch (CloneNotSupportedException e) {
			// TODO Auto-generated catch block
			e.printStackTrace();
		}
		return null;
	}
	
	// ------------ instance methods and variables -----------------------
	
	protected HashMap<Long, Double> m_tags = new HashMap<Long, Double>();

	public TagCloud(Collection<String> tags) {
		TagIndex ti = TagIndex.getIndex();
		for(String tag : tags) { 
			m_tags.put(ti.indexTag(tag), 1.);
		}
	}

	public TagCloud() { }

	public boolean equals(Object o) {
		TagCloud tc = (TagCloud) o;
		return m_tags.equals(tc.getIndexedTags());
	}
	
	public int hashCode() {
		ArrayList<Long> keys = new ArrayList<Long>(m_tags.keySet());
		Collections.sort(keys);
		int hashCode = 0;
		int multiplier = 1;
		for(Long key : keys) {
			hashCode += (key.hashCode() + (5*m_tags.get(key).hashCode())) * multiplier;
			multiplier += 2;
		}
		return hashCode;
	}
	
	protected HashSet<Long> getTagIndexes() {
		return new HashSet<Long>(m_tags.keySet());
	}
	
	public Double getTagValue(Long tag) {
		return m_tags.get(tag);
	}
	
	public HashMap<Long, Double> getIndexedTags() {
		return m_tags;
	}
	
	public void transformByCloud(TagCloud tc) {
		for(Map.Entry<Long, Double> entry : tc.m_tags.entrySet()) {
			Double weight = m_tags.get(entry.getKey());
			if(weight == null) { weight = 0.0; }
			weight += entry.getValue();
			m_tags.put(entry.getKey(), weight);
		}
	}
	
	public TagCloud scale(double alpha) {
		for(Map.Entry<Long, Double> entry : m_tags.entrySet()) {
			entry.setValue(entry.getValue() * alpha);
		}
		return this;
	}
	
	public void addTags(HashMap<String, Double> tags) { addTags(tags, 1.0); }

	public void subtractTags(HashMap<String, Double> tags) { addTags(tags, -1.0); }

	public void addTags(HashMap<String, Double> tags, double percentage) {
		for(Map.Entry<String, Double> tag_weight : tags.entrySet()) {
			addWeightedTag(tag_weight.getKey(), 
				tag_weight.getValue() * percentage);
		}
	}
	
	

	public void addIndexedTags(HashMap<Long, Double> tags) {  
		for(Map.Entry<Long, Double> tag : tags.entrySet()) {
			addWeightedTag(tag.getKey(), tag.getValue());
		}
	}
	
	public void subtractTags(HashMap<String, Double> tags, double percentage) {
		 addTags(tags, -percentage);
	}
	
	public void addTag(String tag) {
		addWeightedTag(tag, 1.);
	}
	
	public void addWeightedTag(String tag, double weight) {
		TagIndex ti = TagIndex.getIndex();
		long index = ti.indexTag(tag);
		addWeightedTag(index, weight);
	}
	
	public void addWeightedTag(WeightedThing<String> wt) {
		addWeightedTag(wt.getThing(), wt.getWeight());
	}	

	public void setWeightedTag(long index, double weight) {
		CloudIndex cloud_index = CloudIndex.getCloudIndex();
		m_tags.put(index, weight);
		cloud_index.indexCloud(index, this);	
	}
	
	public void addWeightedTag(long index, double weight) {
		if(m_tags.containsKey(index)) {
			weight += m_tags.get(index);
		}
		CloudIndex cloud_index = CloudIndex.getCloudIndex();
		m_tags.put(index, weight);
		cloud_index.indexCloud(index, this);	
	}
	
	public void filter(double threshold, FilterType type) {
		TagIndex ti = TagIndex.getIndex();
		ArrayList<String> removed_tags = new ArrayList<String>();
		for(Map.Entry<Long, Double> entry : m_tags.entrySet()) {
			double weight = entry.getValue();
			boolean filter = PointFilter.filterPoint(weight, threshold, type); 
			if(filter) {
				removed_tags.add(ti.reverseIndex(entry.getKey()));
			}
		}
		for(String tag : removed_tags) { removeTag(tag); }
	}
	
	public void saturate(double threshold, boolean high) {
		TagIndex ti = TagIndex.getIndex();

		for(Map.Entry<Long, Double> entry : m_tags.entrySet()) {
			double weight = entry.getValue();
			boolean saturate = high ? (weight > threshold) : (weight < threshold); 
			if(saturate) {
				entry.setValue(threshold);
			}
		}
	
	}	
	
	public void removeTags(Collection<Long> tags) {
		for(Long tag : tags) {
			removeTag(tag);
		}
	}
	
	public void removeTag(Long tag) {
		m_tags.remove(tag);
	}
	
	public void removeTag(String tag) {
		TagIndex ti = TagIndex.getIndex();
		Long index = ti.findTag(tag);
		if(index == null){ return; }
		m_tags.remove(index);
		CloudIndex cloud_index = CloudIndex.getCloudIndex();
		cloud_index.removeCloudIndex(index, this);
	}

	public boolean containsTag(String tag) {
		TagIndex ti = TagIndex.getIndex();
		Long index = ti.findTag(tag);
		if(index == null){ return false; }
		return m_tags.containsKey(index);
	}
	
	public StringBuffer getRepresentation(StringBuffer sb) {
		sb.append("(");
		getTagRepresentation(sb);
		sb.append(")");
		return sb;
	}

	protected StringBuffer getTagRepresentation(StringBuffer sb) {
		TagIndex ti = TagIndex.getIndex();
		for(Map.Entry<Long,Double> entry : m_tags.entrySet()) {
			sb.append(ti.reverseIndex(entry.getKey()));
			sb.append(":");
			sb.append(entry.getValue());
			sb.append(" ");
		}
		return sb;
	}
	
	public boolean evaluateCondition(String condition, ArrayList<Object> arguments) {
		if(condition.equals("contains")) {
			for(Object arg : arguments) {
				if(! (arg instanceof String)) {
					return false;
				}
				if(! containsTag((String) arg)) {
					return false;
				}
			}
			
			return true;
		}
		else if(condition.equals(">")) {
			double tag_weight = getTagWeight((String) arguments.get(0));
			double d = Double.parseDouble((String) arguments.get(1));
			try {
				return ValueExpression.evaluate('>', tag_weight, d);
			} catch (UndefinedOperatorException e) {
				return false;
			}
		}
		else if(condition.equals("<")) {
			double tag_weight = getTagWeight((String) arguments.get(0));
			double d = Double.parseDouble((String) arguments.get(1));
			try {
				return ValueExpression.evaluate('<', tag_weight, d);
			} catch (UndefinedOperatorException e) {
				return false;
			}
		}
		else if(condition.equals("=")) {
			double tag_weight = getTagWeight((String) arguments.get(0));
			double d = Double.parseDouble((String) arguments.get(1));
			try {
				return ValueExpression.evaluate('=', tag_weight, d);
			} catch (UndefinedOperatorException e) {
				return false;
			}
		}
		return false;
	}

	public TagCloud copy() {
		TagCloud copy =  new TagCloud();
		for(Long key : m_tags.keySet()) {
			copy.addWeightedTag(key, m_tags.get(key));
		}
		return copy;
	}

	
	public TagCloud collapse() {
		return this;
	}

	@Override
	public TagCloud getLevelwiseCloud() {
		return this;
	}

	@Override
	public int getChildCount() {
		return 0;
	}

	public boolean hasTags(Collection<String> tags) {
		return  getTags().containsAll(tags);
	}
	
	public boolean hasTagIndexes(HashSet<Long>tags) {
		for(Long key : tags) {
			if(! m_tags.containsKey(key)) { return false; }
		}
		return true;
	}

	public HashMap<String, Double> getWeightedTags() {
		TagIndex ti = TagIndex.getIndex();
		HashMap<String, Double> tags
			= new HashMap<String, Double>();
		for(Long index : getTagIndexes()) {
			tags.put(ti.reverseIndex(index),
				m_tags.get(index));
		}
		return tags;
	}
	
	@Override
	public HashSet<String> getTags() {
		return getTags(new HashSet<String>());
	}

	@Override
	public HashSet<String> getTags(HashSet<String> tags) {
		TagIndex ti = TagIndex.getIndex();
		for(Long index : getTagIndexes()) {
			tags.add(ti.reverseIndex(index));
		}
		return tags;
	}
	
	public String toString() {
		StringBuffer sb = new StringBuffer();
		getRepresentation(sb);
		return sb.toString();
	}

	@Override
	public int compareTo(TagCloud o) {
		ArrayList<Long> my_keys = new ArrayList<Long>(m_tags.keySet());
		Collections.sort(my_keys);
		HashMap<Long, Double> their_tags = o.getIndexedTags();
		ArrayList<Long> their_keys = new ArrayList<Long>(their_tags.keySet());
		Collections.sort(their_keys);
		int mincount = Math.min(my_keys.size(), their_keys.size());
		for(int i = 0;i <mincount;i++) {
			int comp = new Long(my_keys.get(i)).compareTo(their_keys.get(i));
			if(comp != 0) { return comp; }
			comp = new Double(m_tags.get(my_keys.get(i))).compareTo(their_tags.get(their_keys.get(i)));
			if(comp != 0) { return comp; }
		}
		return (new Integer(my_keys.size())).compareTo(their_keys.size());
	}

	public double getTagWeight(String tag) {
		return m_tags.get(TagIndex.getIndex().findTag(tag));
	}

	public void setTagWeight(String tag, double weight) {
		m_tags.put(TagIndex.getIndex().findTag(tag), weight);
	}
}

package com.johnbenjamincassel.taglibs;

import com.johnbenjamincassel.utilities.exceptions.UndefinedOperatorException;
import com.johnbenjamincassel.utilities.simpleMath.MathEvaluator;
import com.johnbenjamincassel.utilities.simpleMath.MathOperator;

public class CloudMathExpression {
	
	String m_tag_arg;
	double m_weight_arg;
	String m_result;
	private char m_op;
	 
	
	public CloudMathExpression(String result, String tag_arg) {
		m_result = result;
		m_tag_arg = tag_arg;
	}


	public void evaluate(TagCloud tc) throws UndefinedOperatorException {
		
		double arg_weight = tc.getTagWeight(m_tag_arg);
		double result_weight = MathEvaluator.evaluate(m_op, arg_weight, m_weight_arg);
		tc.setTagWeight(m_result, result_weight);
	}


	public void setMathOperator(char operator) {
		m_op = operator;
	}


	public void setValueArg(double w) {
		m_weight_arg = w;
		
	}


	
}

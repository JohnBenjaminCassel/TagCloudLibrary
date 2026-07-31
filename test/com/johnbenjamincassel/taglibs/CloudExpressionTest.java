package com.johnbenjamincassel.taglibs;

import java.util.ArrayList;
import java.util.HashMap;

import org.antlr.runtime.RecognitionException;
import org.apache.log4j.BasicConfigurator;
import org.apache.log4j.Level;
import org.apache.log4j.Logger;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;

import com.johnbenjamincassel.conditionable.BacktrackingEvaluator;
import com.johnbenjamincassel.conditionable.ConditionableImplementationParser;
import com.johnbenjamincassel.utilities.CollectionPrinter;

public class CloudExpressionTest {

	@Before
	public void setUp() {
		BasicConfigurator.configure();
		Logger.getLogger("com.johnbenjamincassel.conditionable").setLevel(Level.INFO);
	}
	
	@Test
	public void testCloudMatch() {
		try {
			StructuredTagCloud cloud 
				= (StructuredTagCloud) StructuredCloudGrammarParser.parseCloud(
						"cloud (a b c)");
			Assert.assertNotNull(cloud);
			CloudExpression ex = (CloudExpression) StructuredCloudGrammarParser.parseCloud(
				"expression ($a b c)");
			Assert.assertNotNull(ex);
			BacktrackingEvaluator be =new BacktrackingEvaluator(ex);
			ArrayList<Object> values = new ArrayList<Object>();
			values.add(cloud);
			HashMap results = be.evaluate(values);
			Assert.assertNotNull(results);
			Assert.assertEquals("a", results.get("$a"));
		} catch (RecognitionException e) {
			Assert.fail(e.getMessage());
		}
	}
	
	@Test
	public void testCloudNotMatch() {
		try {
			StructuredTagCloud cloud 
				= (StructuredTagCloud) StructuredCloudGrammarParser.parseCloud(
						"cloud (a b)");
			Assert.assertNotNull(cloud);
			CloudExpression ex = (CloudExpression) StructuredCloudGrammarParser.parseCloud(
				"expression (b c)");
			Assert.assertNotNull(ex);
			BacktrackingEvaluator be =new BacktrackingEvaluator(ex);
			ArrayList<Object> values = new ArrayList<Object>();
			values.add(cloud);
			HashMap results = be.evaluate(values);
			if(results != null) {
				CollectionPrinter.printMap(results);
			}
			Assert.assertNull(results);
		} catch (RecognitionException e) {
			Assert.fail(e.getMessage());
		}
	}	
	
	
}

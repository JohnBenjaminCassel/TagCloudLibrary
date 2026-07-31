package com.johnbenjamincassel.taglibs;

import org.junit.Test;

import junit.framework.Assert;

import com.johnbenjamincassel.taglibs.StructuredTagCloud;

public class EqualityAndHashCodeTests {

	@Test
	public void testStructuredCloudCopyEquality() {
		StructuredTagCloud sc = new StructuredTagCloud();
		sc.addTag("a");
		sc.addTag("b");
		StructuredTagCloud sc_clone = sc.copy();
		Assert.assertEquals(sc, sc_clone);
	}

	@Test
	public void testStructuredCloudEquality() {
		StructuredTagCloud sc = new StructuredTagCloud();
		sc.addTag("a");
		sc.addTag("b");
		StructuredTagCloud sc2 = sc.copy();
		sc2.addTag("a");
		sc2.addTag("b");
		Assert.assertEquals(sc, sc2);
	}
	
	@Test
	public void testStructuredCloudNonEquality() {
		StructuredTagCloud sc = new StructuredTagCloud();
		sc.addTag("a");
		sc.addTag("b");
		StructuredTagCloud sc2 = sc.copy();
		sc2.addTag("b");
		sc2.addTag("c");
		Assert.assertNotSame(sc, sc2);
	}
	
	@Test
	public void testCloudHashEquality() {
		TagCloud tc = new TagCloud();
		tc.addTag("a");
		tc.addTag("b");
		TagCloud tc_clone = tc.copy();
		Assert.assertEquals(tc.hashCode(), tc_clone.hashCode());
	}
	
	@Test
	public void testStructuredCloudHashEquality() {
		StructuredTagCloud sc = new StructuredTagCloud();
		sc.addTag("a");
		sc.addTag("b");
		StructuredTagCloud sc_clone = sc.copy();
		Assert.assertEquals(sc.hashCode(), sc_clone.hashCode());
	}
	
}

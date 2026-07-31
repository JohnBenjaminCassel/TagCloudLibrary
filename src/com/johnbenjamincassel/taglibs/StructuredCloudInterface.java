package com.johnbenjamincassel.taglibs;

import java.util.Collection;

import com.johnbenjamincassel.utilities.exceptions.UndefinedOperatorException;

public interface StructuredCloudInterface extends Cloneable {
	public void addStructuredCloud(StructuredTagCloud add_cloud);
	public void removeStructuredCloud(StructuredTagCloud remove_cloud);
	public StructuredCloudInterface copy();
	public void shiftCloud(CloudMathExpression shift) throws UndefinedOperatorException;
}

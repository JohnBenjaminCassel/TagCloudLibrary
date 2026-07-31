package com.johnbenjamincassel.taglibs;

import java.util.Collection;
import java.util.HashMap;
import java.util.HashSet;

public interface Cloudable extends Cloudlike {
	public void addTag(String tag);
	public void removeTag(String tag);
	public void addWeightedTag(String tag, double weight);
	public StringBuffer getRepresentation(StringBuffer sb);
	public TagCloud collapse();
	public HashSet<String> getTags();
	public HashSet<String> getTags(HashSet<String> set);
	public void addIndexedTags(HashMap<Long, Double> tags);
	public TagCloud getLevelwiseCloud();
	public int getChildCount();
}

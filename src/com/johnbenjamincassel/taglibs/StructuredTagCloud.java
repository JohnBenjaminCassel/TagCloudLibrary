package com.johnbenjamincassel.taglibs;

import java.util.ArrayList;
import java.util.Collection;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Map;

import com.johnbenjamincassel.utilities.CollectionsUtilities;
import com.johnbenjamincassel.utilities.Representable;
import com.johnbenjamincassel.utilities.datastructures.WeightedThing;
import com.johnbenjamincassel.utilities.exceptions.UndefinedOperatorException;

public class StructuredTagCloud extends TagCloud 
	implements StructuredCloudInterface, Comparable<TagCloud>, Representable {
		
	private ArrayList<StructuredTagCloud> m_children
		= new ArrayList<StructuredTagCloud>();
	
	private StructuredTagCloud m_parent_cloud = null;
	
	public StructuredTagCloud(StructuredTagCloud parent_cloud) {
		m_parent_cloud = parent_cloud;
	}
	
	public StructuredTagCloud() {
		m_parent_cloud = null;
	}

	public void addChild(StructuredTagCloud child_cloud) {
		m_children.add(child_cloud);
	}
	
	private void addChildren(Collection<StructuredTagCloud> children) {
		m_children.addAll(children);
	}
	
	public void addStructuredCloud(StructuredTagCloud cloud) {
		addIndexedTags(cloud.getIndexedTags());
		addChildren(cloud.getChildren());
	}
	
	public TagCloud collapse() {
		TagCloud collapsed_cloud = getLevelwiseCloud();
		for(StructuredTagCloud child : m_children) {
			collapsed_cloud.transformByCloud(child.collapse());
		}
		return collapsed_cloud;
	}
	
	public TagCloud getLevelwiseCloud() {
		TagCloud top_cloud = new TagCloud();
		for(Map.Entry<Long, Double> entry : m_tags.entrySet()) {
			top_cloud.addWeightedTag(entry.getKey(), entry.getValue());
		}
		return top_cloud;
	}

	public StructuredTagCloud copy() {
		StructuredTagCloud cloud = new StructuredTagCloud();
		cloud.addStructuredCloud(this);
		return cloud;
	}
	
	public static StringBuffer getRepresentation(Collection<StructuredTagCloud> coll,
			StringBuffer sb) {
		for(StructuredTagCloud s : coll) {
			sb.append("\t");
			s.getRepresentation(sb);
			sb.append("\n");
		}
		return sb;
	}
	
	public StringBuffer getRepresentation(StringBuffer sb) {
		sb.append("(");
		getTagRepresentation(sb);
		for(StructuredTagCloud child : getChildren()) {
			child.getRepresentation(sb);
		}
		sb.append(")");
		return sb;
	}
	
	public StructuredTagCloud copy(StructuredTagCloud parent_cloud) {
		StructuredTagCloud tc = new StructuredTagCloud(parent_cloud);
		tc.addIndexedTags(m_tags);
		for(StructuredTagCloud child : getChildren()) {
			tc.addChild((StructuredTagCloud) child.copy(this));
		}
		return tc; 
	}

	@Override
	public HashSet<String> getTags(HashSet<String> tags) {
		super.getTags(tags);
		for(StructuredTagCloud child : getChildren()) {
			child.getTags();
		}
		return tags;
	}

	
	
	private ArrayList<StructuredTagCloud> getChildren() {
		return m_children;
	}
	
	public int getChildCount() {
		return m_children.size();
	}

	public CloudExpression generateCloudExpression() {
		CloudExpression ce = new CloudExpression();
		ce.addTags(super.getTags());
		if(! m_children.isEmpty()) {
			throw new RuntimeException("FIX ME	"); 
		}
		return ce;
	}

	public void removeStructuredCloud(StructuredTagCloud remove_cloud) {
		removeTags(remove_cloud.getIndexedTags().keySet());
		if(! remove_cloud.getChildren().isEmpty()) {
			throw new RuntimeException("FIX ME");
		}
	}
	
	

	public boolean equals(Object o) {
		if(! (o instanceof TagCloud)) {
			return false;
		}
		StructuredTagCloud sc = (StructuredTagCloud) o;
		if(! m_tags.equals(sc.getIndexedTags())) {
			return false;
		}
		if(m_children.isEmpty() && sc.getChildren().isEmpty()) {
			return true;
		}
		if(getChildCount() != sc.getChildCount()) {
			return false;
		}
		
		throw new RuntimeException("FIX ME");
	}
	
	public int hashCode() {
		int hash = (super.hashCode() * 7);
		for(StructuredTagCloud child : m_children) {
			hash += (child.hashCode() * 31);
		}
		return hash;
	}

	@Override
	public int compareTo(TagCloud arg0) {
		if(arg0 instanceof StructuredTagCloud) {
			return compareTo((StructuredTagCloud) arg0);
		}
		int comp = super.compareTo(arg0);
		if(comp != 0) { return comp; }
		return new Integer(m_children.size()).compareTo(0);
	}
	
	public static int compareSTCLists(ArrayList<StructuredTagCloud> c1, 
			ArrayList<StructuredTagCloud> c2) {
		Collections.sort(c1);
		Collections.sort(c2);
		int min_count = Math.min(c1.size(), c2.size());
		for(int i = 0;i < min_count;i++) {
			int comp = c1.get(i).compareTo(c2.get(i));
			if(comp != 0) {
				return comp;
			}
		}
		return new Integer(c1.size()).compareTo(c2.size());
	}
	
	public int compareTo(StructuredTagCloud arg0) {
		int comp = super.compareTo(arg0);
		if(comp != 0) { return comp; }
		return compareSTCLists(m_children, arg0.getChildren());
	}

	public static StructuredTagCloud averageClouds(
			ArrayList<StructuredTagCloud> cloud_array) {
		StructuredTagCloud ave_cloud = new StructuredTagCloud();
		for(StructuredTagCloud stc : cloud_array) {
			ave_cloud.addStructuredCloud(stc);
		}
		ave_cloud.scale(1.0/cloud_array.size());
		return ave_cloud;
	}

	// this is the special case where there are no equivalence functions for children
	public static StructuredTagCloud takeMaximumTagOverClouds(
			ArrayList<StructuredTagCloud> cloud_list) {
		StructuredTagCloud max_cloud = new StructuredTagCloud();
		for(StructuredTagCloud stc : cloud_list) {
			for(Long tag : stc.getTagIndexes()) {
				Double sv = stc.getTagValue(tag);
				Double mv = max_cloud.getTagValue(tag);
				if(mv == null || mv.doubleValue() < sv.doubleValue()) {
					max_cloud.addWeightedTag(tag, sv);
				}
			}
			max_cloud.addChildren(stc.getChildren());
		}
		
		return max_cloud;
	}

	@Override
	public void shiftCloud(CloudMathExpression shift) throws UndefinedOperatorException {
		shift.evaluate(this);
	}
}

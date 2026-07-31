package com.johnbenjamincassel.taglibs;

import java.util.ArrayList;
import java.util.HashMap;

import com.johnbenjamincassel.utilities.datastructures.HashToHashSet;

public class CloudIndex {

	public static CloudIndex s_index
		= new CloudIndex();
	
	private HashToHashSet<Long, TagCloud> m_cloud_index
		= new HashToHashSet<Long, TagCloud>();
	
	public static CloudIndex getCloudIndex() {
		return s_index;
	}
	
	public void indexCloud(long index, TagCloud tc) {
		m_cloud_index.put(index, tc);
	}

	public void removeCloudIndex(long index, TagCloud cloud) {
		m_cloud_index.remove(index, cloud);
	}
}

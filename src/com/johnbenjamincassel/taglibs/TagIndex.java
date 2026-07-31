package com.johnbenjamincassel.taglibs;

import java.util.TreeMap;

import com.johnbenjamincassel.utilities.datastructures.Trie;

public class TagIndex  {
	
	private static TagIndex s_index = new TagIndex();

	private Trie<Long> m_trie = new Trie<Long>();
	private TreeMap<Long, String> m_reverse_index
		= new TreeMap<Long, String>();
	
	long m_index = 0;
	
	public static TagIndex getIndex() {
		return s_index;
	}

	public long indexTag(String tag) {
		long found_index = m_trie.setIfNotFound(tag, m_index);
		if(found_index == m_index) { 
			m_reverse_index.put(m_index, tag);
			m_index++; 
		}
		return found_index;
	}
	
	public Long findTag(String tag) {
		return m_trie.getPayloadByString(tag);
	}
	
	public String reverseIndex(Long index) {
		return m_reverse_index.get(index);
	}
}

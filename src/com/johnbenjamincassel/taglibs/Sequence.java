package com.johnbenjamincassel.taglibs;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;

public class Sequence<T extends InstantInterface> implements Cloudable {
	
	ArrayList<SequenceInterval<T>> m_intervals
		= new ArrayList<SequenceInterval<T>>();
	
	SequenceInterval<T> m_current_interval = null;

	private String m_name;
	
	public Sequence(String name, T start_instant) {
		m_name = name;
		m_current_interval = new SequenceInterval<T>(start_instant);
		m_intervals.add(m_current_interval);
	}
	
	public void addTag(String tag) {
		if(m_current_interval == null) { 
			throw new RuntimeException("No pending interval"); 
		}
		m_current_interval.m_cloudable.addTag(tag);
	}
	
	public void removeTag(String tag) {
		if(m_current_interval == null) { 
			throw new RuntimeException("No pending interval"); 
		}
		m_current_interval.m_cloudable.removeTag(tag);
	}
	
	public void addWeightedTag(String tag, double weight) {
		if(m_current_interval == null) { 
			throw new RuntimeException("No pending interval"); 
		}
		m_current_interval.m_cloudable.addWeightedTag(tag, weight);
	}
	
	
	
	public void startInterval(T start_time) {
		m_current_interval.m_finish_instant = start_time;
		m_current_interval = new SequenceInterval(start_time);
		m_intervals.add(m_current_interval);
	}
	
	public void appendSequence(Sequence<T> seq) {
		if(! seq.isFinished()) { throw new RuntimeException("appended sequence not finished"); }
		
		SequenceInterval<T> first_interval = seq.getFirstInterval();
		m_current_interval.m_finish_instant = first_interval.m_start_instant;
		SequenceInterval<T> last_interval = seq.getLastInterval();
		
		m_intervals.add(new SequenceInterval(first_interval.m_start_instant, 
				last_interval.m_finish_instant, seq));
		m_current_interval = new SequenceInterval(last_interval.m_finish_instant);
		m_intervals.add(m_current_interval);	
	}
	
	public void addIndexedTags(HashMap<Long, Double> tags) {
		m_current_interval.getCloudable().addIndexedTags(tags);	
	}	
	
	public boolean isFinished() { 
		return (m_current_interval == null);
	}
	
	public SequenceInterval<T> getFirstInterval() {
		return m_intervals.get(0);
	}

	public SequenceInterval<T> getLastInterval() {
		return m_intervals.get(m_intervals.size() - 1);
	}
	
	public void finishSequence(T final_time) {
		m_current_interval.m_finish_instant = final_time;
		m_current_interval = null;
	}
	

	@Override
	public TagCloud collapse() {
		TagCloud tc = new TagCloud();
		for(SequenceInterval<T> si : m_intervals) {
			tc = TagCloud.maxCloud(tc, 
					si.getCloudable().collapse());
		}
		return tc;
	}
	
	public Sequence<T> generateForwardTransform() {
		Sequence<T> forward_transform = null;
		HashMap<Long, Double> previous_tags = null;
		HashMap<Long, Double> tags;
		
		for(SequenceInterval<T> interval : m_intervals) {
			if(forward_transform == null) {
				forward_transform = new Sequence(m_name + "_fortran", interval.m_start_instant);
			}
			else {
				forward_transform.startInterval(interval.getStartInstant());
			}
			
			
			tags = interval.getCloudable().collapse().getIndexedTags();
			
			forward_transform.addIndexedTags(tags);
			
			if(previous_tags != null) {
				forward_transform.addIndexedTags(previous_tags);
			}
			
			previous_tags = tags;
		}
		if(isFinished()) {
			forward_transform.finishSequence(getLastInterval().getFinishInstant());
		}
		
		return forward_transform;
	}

	public Sequence<T> generateBackwardsTransform() {
		Sequence<T> backwards_transform = null;

		LinkedList<SequenceInterval<T>> interval_queue 
			= new LinkedList<SequenceInterval<T>>(m_intervals);
		
		while(! interval_queue.isEmpty()) {
			SequenceInterval<T> interval = interval_queue.poll();
			SequenceInterval<T> next_interval = null;
			
			if(! interval_queue.isEmpty()) {
				next_interval = interval_queue.peek();
			}
			
			if(backwards_transform == null) {
				backwards_transform = new Sequence(m_name + "_backtran", interval.m_start_instant);
			}
			else {
				backwards_transform.startInterval(interval.getStartInstant());
			}
			
			
			backwards_transform.addIndexedTags(interval.getCloudable().collapse().getIndexedTags());
			
			if(next_interval != null) {
				backwards_transform.addIndexedTags(next_interval.getCloudable().collapse().getIndexedTags());
			}
		}
		if(isFinished()) {
			backwards_transform.finishSequence(getLastInterval().getFinishInstant());
		}
		
		return backwards_transform;
	}
	
	public StringBuffer getRepresentation(StringBuffer sb) {
		sb.append("<");
		m_intervals.get(0).getRepresentation(sb);
		for(int i = 1;i < m_intervals.size();i++) { 
			sb.append(',');
			m_intervals.get(i).getRepresentation(sb);
		}
		sb.append(">");
		return sb;
	}
	
	
	class SequenceInterval<T extends InstantInterface> {

		T m_start_instant;
		T m_finish_instant = null;
		Cloudable m_cloudable;
		
		public SequenceInterval(T start_instant) {
			m_start_instant = start_instant;
			m_cloudable = new TagCloud();
		}
		
		public T getFinishInstant() {
			return m_finish_instant;
		}

		public T getStartInstant() {
			return m_start_instant;
		}

		public Cloudable getCloudable() {
			return m_cloudable;
		}

		public StringBuffer getRepresentation(StringBuffer sb) {
			m_start_instant.getRepresentation(sb);
			
			m_cloudable.getRepresentation(sb);
			return sb;
		}

		public SequenceInterval(T start_instant, T finish_instant, Cloudable cloudable) {
			m_start_instant = start_instant;
			m_finish_instant = finish_instant;
			m_cloudable = cloudable;
		}
		
		public void finishSequence(T finish_instant) {
			m_finish_instant = finish_instant;
		}
		
		public boolean holdsRelationWithRespectToInstant(T instant, 
			IntervalPoint ip, InstantRelation ir) {
			T my_instant = getIntervalPoint(ip);
			return my_instant.hasRelationTo(ir, instant);
		}

		private T getIntervalPoint(IntervalPoint ip) {
			switch(ip) {
			case START:
				return m_start_instant;
			case FINISH:
				return m_finish_instant;
			default:
				throw new RuntimeException("Illegal point type: "+ ip);
			}
		}
	}


	@Override
	public TagCloud getLevelwiseCloud() {
		TagCloud tc = new TagCloud();
		for(SequenceInterval<T> si : m_intervals) {
			tc = TagCloud.maxCloud(tc, 
					si.getCloudable().getLevelwiseCloud());
		}
		return tc;
	}

	@Override
	public int getChildCount() {
		return m_intervals.size();
	}

	@Override
	public HashSet<String> getTags() {
		return getTags(new HashSet<String>());
	}

	@Override
	public HashSet<String> getTags(HashSet<String> set) {
		for(SequenceInterval<T> si : m_intervals) {
			si.getCloudable().getTags(set);
		}
		return set;
	}
}

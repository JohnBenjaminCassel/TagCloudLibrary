package com.johnbenjamincassel.taglibs;

public class LongInstant implements InstantInterface {

	Long m_long;
	
	public LongInstant(long instant) {
		m_long =instant;
	}
	
	public InstantInterface getDelta() {
		return new LongInstant(m_long+1);
	}
	
	@Override
	public int compareTo(InstantInterface instant) {
		Long instant_long = ((LongInstant) instant).m_long;
		return m_long.compareTo(instant_long);
		
	}


	@Override
	public void getRepresentation(StringBuffer sb) {
		sb.append(m_long);		
	}

	@Override
	public boolean hasRelationTo(InstantRelation ir, InstantInterface instant) {
		int comparison = compareTo(instant);
		return ((comparison == 0) && (ir == InstantRelation.AT))
			|| ((comparison < 0) && (ir == InstantRelation.BEFORE))
			|| ((comparison > 0) && (ir == InstantRelation.AFTER));
	}

	@Override
	public InstantInterface duplicate() {
		return new LongInstant(m_long);
	}

}

package com.johnbenjamincassel.taglibs;

import org.joda.time.DateTime;
import org.joda.time.Duration;
import org.joda.time.base.BaseSingleFieldPeriod;

import com.johnbenjamincassel.utilities.temporal.TemporalRelation;


public class AbsoluteTimeInstant implements InstantInterface {

	private DateTime m_time;

	public AbsoluteTimeInstant(DateTime current_time) {
		m_time = current_time;
	}

	public AbsoluteTimeInstant() {
		m_time = new DateTime();
	}

	@Override
	public int compareTo(InstantInterface instant) {
		if(instant instanceof AbsoluteTimeInstant) {
			return m_time.compareTo(((AbsoluteTimeInstant)instant).getDateTime());
		}
		throw new RuntimeException("IMPLEMENT ME");
	}

	private DateTime getDateTime() {
		return m_time;
	}

	@Override
	public void getRepresentation(StringBuffer sb) {
		throw new RuntimeException("IMPLEMENT ME");
	}

	@Override
	public boolean hasRelationTo(InstantRelation ir, InstantInterface instant) {
		throw new RuntimeException("IMPLEMENT ME");
	}

	@Override
	public InstantInterface getDelta() {
		throw new RuntimeException("IMPLEMENT ME");
	}

	@Override
	public InstantInterface duplicate() {
		throw new RuntimeException("IMPLEMENT ME");
	}

	public boolean satisfies(AbsoluteTimeInstant time, 
			TemporalRelation tr) {
		if(tr==TemporalRelation.AFTER) {
			return m_time.isAfter(time.getDateTime());
		}
		throw new RuntimeException("unsupported temporal relation: "+tr);
	}

	public AbsoluteTimeInstant addPeriod(BaseSingleFieldPeriod period) {
		return new AbsoluteTimeInstant(m_time.plus(period));
	}

	public Duration getDurationBetween(AbsoluteTimeInstant time) {
		return new Duration(m_time, time.getDateTime());
	}

}

package com.johnbenjamincassel.taglibs;

import com.johnbenjamincassel.utilities.temporal.TemporalInstant;

public interface InstantInterface extends Comparable<InstantInterface>, TemporalInstant {

	public void getRepresentation(StringBuffer sb);
	public boolean hasRelationTo(InstantRelation ir, InstantInterface instant);
	public InstantInterface getDelta();
	public InstantInterface duplicate();
}

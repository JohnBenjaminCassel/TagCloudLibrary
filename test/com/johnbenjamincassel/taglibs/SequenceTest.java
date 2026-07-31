package com.johnbenjamincassel.taglibs;

import java.util.Random;

import org.junit.Test;

import com.johnbenjamincassel.utilities.generators.ShiftDrivenItemsetGenerator;

public class SequenceTest {
	
	@Test
	public void testSequence() {
		Random r = new Random(1);
		ShiftDrivenItemsetGenerator sdig = new ShiftDrivenItemsetGenerator(r);
		long instant = 1;
		Sequence<LongInstant> s = new Sequence("test", new LongInstant(instant));
		for(instant = 2;instant < 10;instant++) {
			for(Integer i : sdig.generateItemset(0, 0.3, 0.5)) {
				s.addTag(i.toString());
			}
			s.startInterval(new LongInstant(instant));
		}
		s.finishSequence(new LongInstant(11));
		StringBuffer sb = s.getRepresentation(new StringBuffer());
		sb.append("\n\n");
		Sequence s_fortran = s.generateForwardTransform();
		s_fortran.getRepresentation(sb);
		sb.append("\n\n");
		Sequence s_backtran = s.generateBackwardsTransform();
		s_backtran.getRepresentation(sb);
		System.out.println(sb.toString());
	}
	
}

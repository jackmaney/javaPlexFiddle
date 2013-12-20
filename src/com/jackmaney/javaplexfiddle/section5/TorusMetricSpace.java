package com.jackmaney.javaplexfiddle.section5;

import com.jackmaney.javaplexfiddle.section41.Exercise4;

import edu.stanford.math.plex4.metric.impl.ObjectSearchableFiniteMetricSpace;

public class TorusMetricSpace extends ObjectSearchableFiniteMetricSpace<double[]>{

	public TorusMetricSpace(double[][] arg0) {
		super(arg0);
	}

	@Override
	public double distance(double[] p, double[] q) {
		return Exercise4.torusDistance(p, q);
	}

	

}

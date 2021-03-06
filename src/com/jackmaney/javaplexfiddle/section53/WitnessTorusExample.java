package com.jackmaney.javaplexfiddle.section53;

import com.jackmaney.javaplexfiddle.Util;
import com.jackmaney.javaplexfiddle.section51.TorusMetricSpace;

import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.metric.landmark.MaxMinLandmarkSelector;
import edu.stanford.math.plex4.streams.impl.WitnessStream;

public class WitnessTorusExample {

	public static void main(String[] args) {
		
		int n = 1000;
		int numLandmarkPoints = 60;
		int maxDimension = 3;
		int numDivisions = 25;
		double maxDistance = 1.1;
		
		double[][] pointCloud = Util.randomPointsFromUnitSquare(n);
		TorusMetricSpace space = new TorusMetricSpace(pointCloud);
		
		MaxMinLandmarkSelector<double[]> selector
			= new MaxMinLandmarkSelector<>(space, numLandmarkPoints);
		
		WitnessStream<double[]> stream = 
				new WitnessStream<>(space, selector, maxDimension
						, maxDistance, numDivisions);
		
		stream.finalizeStream();
		System.out.println(stream.getSize());
		
		AbstractPersistenceAlgorithm<Simplex> persistence 
		= Plex4.getModularSimplicialAlgorithm(maxDimension, 2);

		BarcodeCollection<Double> intervals 
			= persistence.computeIntervals(stream);
	
		try {
			Plex4.createBarcodePlot(intervals, "WitnessTorus", maxDistance);
			System.out.println("Clear!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

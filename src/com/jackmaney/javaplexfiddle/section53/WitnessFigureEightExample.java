package com.jackmaney.javaplexfiddle.section53;

import com.jackmaney.javaplexfiddle.Util;

import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.metric.impl.EuclideanMetricSpace;
import edu.stanford.math.plex4.metric.landmark.MaxMinLandmarkSelector;
import edu.stanford.math.plex4.streams.impl.WitnessStream;

public class WitnessFigureEightExample {

	public static void main(String[] args) {
		
		int n = 10000;
		int numLandmarkPoints = 50;
		int maxDimension = 3;
		int numDivisions = 50;
		double maxDistance = 4;
		
		double[][] pointCloud = Util.randomPointsFromFigureEight(n);
		
		EuclideanMetricSpace space = new EuclideanMetricSpace(pointCloud);
		
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
			Plex4.createBarcodePlot(intervals, "WitnessFigureEight", 5);
			System.out.println("Clear!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

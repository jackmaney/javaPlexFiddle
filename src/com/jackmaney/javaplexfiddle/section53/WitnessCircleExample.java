package com.jackmaney.javaplexfiddle.section53;


import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.metric.impl.EuclideanMetricSpace;
import edu.stanford.math.plex4.metric.landmark.MaxMinLandmarkSelector;
import edu.stanford.math.plex4.streams.impl.WitnessStream;

public class WitnessCircleExample {

	public static void main(String[] args) {
		int n = 1000;
		int numLandmarkPoints = 50;
		int maxDimension = 3;
		int numDivisions = 50;
		double maxDistance = 1;
		
		double[][] pointCloud = new double[n][2];
		
		for(int i = 0; i < n; i++){
			double theta = 2 * Math.PI * Math.random();
			pointCloud[i][0] = Math.cos(theta);
			pointCloud[i][1] = Math.sin(theta);
		}
		
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
			Plex4.createBarcodePlot(intervals, "WitnessCircle", 2);
			System.out.println("Clear!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

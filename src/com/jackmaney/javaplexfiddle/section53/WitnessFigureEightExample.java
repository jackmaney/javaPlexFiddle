package com.jackmaney.javaplexfiddle.section53;

import java.io.PrintWriter;

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
		
		int n = 2000;
		double r = 10;
		int numLandmarkPoints = 75;
		int maxDimension = 3;
		int numDivisions = 50;
		double maxDistance = 2 * r;
		
		double[][] pointCloud = new double[n][2];
		
		for(int i = 0; i < n; i++){
			double theta = 2 * Math.PI * Math.random();
			double x = r * Math.cos(theta);
			double y = r * Math.sin(theta);
			
			if(Math.random() < 0.5){
				y -= r;
			}
			else{
				y += r;
			}
			
			pointCloud[i][0] = x;
			pointCloud[i][1] = y;
		}
		
//		try {
//			PrintWriter writer = new PrintWriter("figure_eight.csv","UTF-8");
//			for(int i = 0; i < n; i++){
//				writer.println(pointCloud[i][0] + "," + pointCloud[i][1]);
//			}
//			writer.close();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		
//		System.exit(0);
		
		
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
			Plex4.createBarcodePlot(intervals, "WitnessFigureEight_radius_" + r, 2 * r);
			System.out.println("Clear!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

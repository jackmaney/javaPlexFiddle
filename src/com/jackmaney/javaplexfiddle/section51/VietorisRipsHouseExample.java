package com.jackmaney.javaplexfiddle.section51;

import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.metric.impl.EuclideanMetricSpace;
import edu.stanford.math.plex4.streams.impl.VietorisRipsStream;

public class VietorisRipsHouseExample {

	public static void main(String[] args) {
		int maxDimension = 3;
		int maxDistance = 4;
		int numDivisions = 100;
		
		double [][] pointCloud = {
				new double[] {-1,0},
				new double[] {1,0},
				new double[] {1,2},
				new double[] {-1,2},
				new double[] {0,3}
		};
		
		EuclideanMetricSpace space = new EuclideanMetricSpace(pointCloud);
		VietorisRipsStream<double[]> stream = 
				new VietorisRipsStream<>(space, maxDistance
						,maxDimension,numDivisions);
		
		stream.finalizeStream();
		
		AbstractPersistenceAlgorithm<Simplex> persistence 
			= Plex4.getModularSimplicialAlgorithm(maxDimension, 2);
		
		BarcodeCollection<Double> intervals 
			= persistence.computeIntervals(stream);
		
		System.out.println(intervals);
		
		try {
			Plex4.createBarcodePlot(intervals, "VietorisRipsHouse", 4);
			System.out.println("Clear!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}
	
}

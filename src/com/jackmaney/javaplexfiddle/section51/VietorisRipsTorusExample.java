package com.jackmaney.javaplexfiddle.section51;

import com.jackmaney.javaplexfiddle.Util;

import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.streams.impl.VietorisRipsStream;

public class VietorisRipsTorusExample {

	public static void main(String[] args) {
		int n = 100; // 400 points?! Are you kidding? I run out of memory with 100...
		double[][] pointCloud = Util.randomPointsFromUnitSquare(n);
		
		TorusMetricSpace space = new TorusMetricSpace(pointCloud);
		
		int maxDimension = 3;
		double maxDistance = 0.6;
		int numDivisions = 50;
		
		VietorisRipsStream<double[]> stream = new VietorisRipsStream<>(space
				,maxDistance,maxDimension,numDivisions);
		
		stream.finalizeStream();
		
		System.out.println(stream.getSize());
		
		AbstractPersistenceAlgorithm<Simplex> persistence 
			= Plex4.getModularSimplicialAlgorithm(maxDimension, 2);
	
		BarcodeCollection<Double> intervals 
			= persistence.computeIntervals(stream);
	
		try {
			Plex4.createBarcodePlot(intervals, "VietorisRipsTorus", 0.9);
			System.out.println("Clear!");
		} catch (Exception e) {
			e.printStackTrace();
		}

	}

}

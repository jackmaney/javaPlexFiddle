package com.jackmaney.javaplexfiddle.section32;

import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.streams.impl.ExplicitSimplexStream;

public class HouseExample {

	public static void main(String[] args) {
		ExplicitSimplexStream stream = new ExplicitSimplexStream();
		
		for(int i = 1; i <= 4; i++){
			stream.addVertex(i, 0);
		}
		
		stream.addVertex(5, 1);
		
		stream.addElement(new int[] {1,2},0);
		stream.addElement(new int[] {2,3},0);
		stream.addElement(new int[] {3,4},0);
		stream.addElement(new int[] {4,1},0);
		
		stream.addElement(new int[] {3,5},2);
		stream.addElement(new int[] {4,5},3);
		
		stream.addElement(new int[] {3,4,5},7);
		
		stream.finalizeStream();
		
		System.out.println("Size of complex: " + stream.getSize());
		
		AbstractPersistenceAlgorithm<Simplex> persistence 
		= Plex4.getModularSimplicialAlgorithm(3, 2);
		
		BarcodeCollection<Double> intervals 
		= persistence.computeIntervals(stream);
		
		System.out.println(intervals);
		
		try {
			Plex4.createBarcodePlot(intervals, "House", 20);
			System.out.println("Clear!");
		} catch (Exception e) {
			e.printStackTrace();
		}
		
		

	}

}

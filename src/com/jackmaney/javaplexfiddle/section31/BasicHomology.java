package com.jackmaney.javaplexfiddle.section31;
import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.streams.impl.ExplicitSimplexStream;


public class BasicHomology {

	public static void main(String[] args) {
		ExplicitSimplexStream stream = new ExplicitSimplexStream();
		
		stream.addVertex(0);
		stream.addVertex(1);
		stream.addVertex(2);
		
		stream.addElement(new int[] {0,1});
		stream.addElement(new int[] {1,2});
		stream.addElement(new int[] {2,0});
		
		stream.finalizeStream();
		
		System.out.println("Size of complex: " + stream.getSize());
		
		AbstractPersistenceAlgorithm<Simplex> persistence 
			= Plex4.getModularSimplicialAlgorithm(3, 2);
		
		BarcodeCollection<Double> circle_intervals 
			= persistence.computeIntervals(stream);
		
		System.out.println(circle_intervals);

	}

}
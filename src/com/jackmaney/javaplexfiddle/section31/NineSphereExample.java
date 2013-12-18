package com.jackmaney.javaplexfiddle.section31;

import com.jackmaney.javaplexfiddle.Util;

import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.streams.impl.ExplicitSimplexStream;

public class NineSphereExample {

	public static void main(String[] args) {
		int dimension = 9;
		ExplicitSimplexStream stream = new ExplicitSimplexStream();
		stream.addElement(Util.intRange(dimension + 1));
		stream.ensureAllFaces();
		stream.removeElementIfPresent(Util.intRange(dimension + 1));
		stream.finalizeStream();
		
		System.out.println("Size of complex: " + stream.getSize());
		
		AbstractPersistenceAlgorithm<Simplex> persistence 
		= Plex4.getModularSimplicialAlgorithm(dimension + 1, 2);
		
		BarcodeCollection<Double> intervals 
		= persistence.computeIntervals(stream);
		
		System.out.println(intervals);
		System.out.println();
		BarcodeCollection<Integer> intervals2 
		= persistence.computeIndexIntervals(stream);
		
		System.out.println(intervals2);

	}

}

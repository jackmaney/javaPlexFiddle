package com.jackmaney.javaplexfiddle.section31;

import edu.stanford.math.plex4.api.Plex4;
import edu.stanford.math.plex4.homology.barcodes.BarcodeCollection;
import edu.stanford.math.plex4.homology.chain_basis.Simplex;
import edu.stanford.math.plex4.homology.interfaces.AbstractPersistenceAlgorithm;
import edu.stanford.math.plex4.streams.impl.ExplicitSimplexStream;

public class KleinBottleHomology {

	public static void main(String[] args) {
		ExplicitSimplexStream stream = new ExplicitSimplexStream();
		
		for(int i = 1; i <= 9; i++){
			stream.addVertex(i);
		}
		
		stream.addElement(new int[] {1,2});
		stream.addElement(new int[] {2,3});
		stream.addElement(new int[] {3,1});
		stream.addElement(new int[] {1,4});
		stream.addElement(new int[] {4,2});
		stream.addElement(new int[] {4,5});
		stream.addElement(new int[] {5,2});
		stream.addElement(new int[] {5,3});
		stream.addElement(new int[] {5,6});
		stream.addElement(new int[] {6,3});
		stream.addElement(new int[] {6,1});
		stream.addElement(new int[] {6,4});
		stream.addElement(new int[] {7,4});
		stream.addElement(new int[] {7,5});
		stream.addElement(new int[] {7,8});
		stream.addElement(new int[] {8,5});
		stream.addElement(new int[] {8,6});
		stream.addElement(new int[] {8,9});
		stream.addElement(new int[] {9,6});
		stream.addElement(new int[] {9,4});
		stream.addElement(new int[] {9,7});
		stream.addElement(new int[] {1,7});
		stream.addElement(new int[] {1,8});
		stream.addElement(new int[] {3,8});
		stream.addElement(new int[] {3,9});
		stream.addElement(new int[] {2,9});
		stream.addElement(new int[] {2,7});
		
		stream.addElement(new int[] {1,2,4});
		stream.addElement(new int[] {2,4,5});
		stream.addElement(new int[] {2,3,5});
		stream.addElement(new int[] {3,5,6});
		stream.addElement(new int[] {3,6,1});
		stream.addElement(new int[] {1,6,4});
		stream.addElement(new int[] {4,5,7});
		stream.addElement(new int[] {5,7,8});
		stream.addElement(new int[] {8,5,6});
		stream.addElement(new int[] {6,8,9});
		stream.addElement(new int[] {4,6,9});
		stream.addElement(new int[] {9,4,7});
		stream.addElement(new int[] {8,7,1});
		stream.addElement(new int[] {1,3,8});
		stream.addElement(new int[] {3,8,9});
		stream.addElement(new int[] {3,2,9});
		stream.addElement(new int[] {2,9,7});
		stream.addElement(new int[] {7,2,1});
		
		stream.finalizeStream();
		
		System.out.println("Size of complex: " + stream.getSize());
		
		AbstractPersistenceAlgorithm<Simplex> persistence2
		= Plex4.getModularSimplicialAlgorithm(4, 2);
		
		BarcodeCollection<Double> intervals2 
		= persistence2.computeIntervals(stream);
		System.out.println("Z_2 coefficients:");
		System.out.println(intervals2);
		System.out.println();
		
		AbstractPersistenceAlgorithm<Simplex> persistence3
		= Plex4.getModularSimplicialAlgorithm(4, 3);
		
		BarcodeCollection<Double> intervals3 
		= persistence3.computeIntervals(stream);
		
		System.out.println("Z_3 coefficients:");
		System.out.println(intervals3);
		System.out.println();

	}

}

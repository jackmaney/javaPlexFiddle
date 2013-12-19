package com.jackmaney.javaplexfiddle.section41;

//import edu.stanford.math.plex4.metric.impl.EuclideanMetricSpace;
import edu.stanford.math.plex4.metric.impl.ExplicitMetricSpace;

public class EuclideanHouseExample {

	public static void main(String[] args) {
//		double [][] pointCloud = {
//				{-1,0},
//				{1,0},
//				{1,2},
//				{-1,2},
//				{0,3}
//		};
//		
//		EuclideanMetricSpace space = new EuclideanMetricSpace(pointCloud);
		
		double[][] distances = {
				{0,2,Math.sqrt(8),2,Math.sqrt(10)},
				{2,0,2,Math.sqrt(8),Math.sqrt(10)},
				{Math.sqrt(8),2,0,2,Math.sqrt(2)},
				{2,Math.sqrt(8),2,0,Math.sqrt(2)},
				{Math.sqrt(10),Math.sqrt(10),Math.sqrt(2),Math.sqrt(2),0}
		};
		
		ExplicitMetricSpace space = new ExplicitMetricSpace(distances);
		
		//System.out.println(space.distance(0,2));

	}

}

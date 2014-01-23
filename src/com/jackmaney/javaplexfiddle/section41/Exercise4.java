package com.jackmaney.javaplexfiddle.section41;

import com.jackmaney.javaplexfiddle.Util;

public class Exercise4 {
	
	public static double torusDistance(double[] p,double[] q){
		if(p.length != 2 || q.length != 2){
			throw new IllegalArgumentException();
		}
		
		double x1 = p[0];
		double y1 = p[1];
		double x2 = q[0];
		double y2 = q[1];
		
		/*
		 * There's an even easier way: since |x2 - x1| and 
		 * 1 - |x2 - x1| map to the same x coordinate (and likewise
		 * for y), we can just do this: 
		 */
		
		double deltaX = Math.abs(x2 - x1);
		double deltaY = Math.abs(y2 - y1);
		
//		System.out.println("deltaX: " + deltaX + ", deltaY: " + deltaY);
//		System.out.println("1 - deltaX: " + (1 - deltaX) + ", 1 - deltaY: "
//				+ (1 - deltaY));
		
		return Math.sqrt(
				Math.pow(Math.min(deltaX, 1-deltaX),2) +
				Math.pow(Math.min(deltaY, 1-deltaY), 2)
			);
			
	}
	
	public static double[][] getDistanceMatrix(double[][] points){
		int n = points.length;
		
		double[][] result = new double[n][n];
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				result[i][j] = torusDistance(points[i], points[j]);
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
		int n = 3;
		double[][] points = Util.randomPointsFromUnitSquare(n);
		
		for(int i = 0; i < n; i++){
			System.out.println("Length: " + points[i].length);
			System.out.println(points[i][0] + ", " + points[i][1]);
		}
		
		System.out.println();
		
		double[][] distanceMatrix = getDistanceMatrix(points);
		
		for(int i = 0; i < n; i++){
			StringBuilder sb = new StringBuilder();
			sb.append(distanceMatrix[i][0]);
			
			for(int j = 1; j < n; j++){
				sb.append(", " + distanceMatrix[i][j]);
			}
			
			System.out.println(sb.toString());
		}

	}

}

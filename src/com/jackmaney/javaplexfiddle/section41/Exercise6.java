package com.jackmaney.javaplexfiddle.section41;

import com.jackmaney.javaplexfiddle.Util;

public class Exercise6 {
	
	public static double distanceOnUnitSphere(double[] p,double[] q){
		
		/*
		 * Since we're on the unit sphere, the distance is the same as
		 * the angle (in radians) between the points. 
		 */
		
		return Math.acos(Util.dotProduct(p, q));
		
	}
	
	public static double projectivePlaneDistance(double[] p,double[] q){
		if(p.length != 3 || q.length != 3){
			throw new IllegalArgumentException();
		}
		
		double[] negP = {-1 * p[0],-1 * p[1],-1 * p[2]};
		double[] negQ = {-1 * q[0],-1 * q[1],-1 * q[2]};
		
		double d1 = distanceOnUnitSphere(p, q);
		double d2 = distanceOnUnitSphere(p, negQ);
		double d3 = distanceOnUnitSphere(negP, q);
		double d4 = distanceOnUnitSphere(negP, negQ);
		
		return Util.arrayMin(new double[] {d1,d2,d3,d4});
		
	}
	
	public static double[][] getDistanceMatrix(double[][] points){
		int n = points.length;
		
		double[][] result = new double[n][n];
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				if(i == j){ //Preventing weird precision errors with acos near zero
					result[i][j] = 0;
				}
				else{
					result[i][j] = projectivePlaneDistance(points[i], points[j]);
				}
			}
		}
		
		return result;
	}
	
	public static void main(String[] args) {
		
		int n = 3;
		double[][] points = Util.randomPointsFromUnitSphere(n);
		
		for(int i = 0; i < n; i++){
			System.out.println("Length: " + points[i].length);
			System.out.println(points[i][0] + ", " + points[i][1] 
					+ ", " + points[i][2]);
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

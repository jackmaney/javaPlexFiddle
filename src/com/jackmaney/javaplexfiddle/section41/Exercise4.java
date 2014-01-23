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
		 * First the easy cases: the points are equal 
		 * or are on either a horizontal or vertical line.
		 */
		
		double deltaX = x2 - x1;
		double deltaY = y2 - y1;
		
		if(deltaX == 0 && deltaY == 0){
			return 0;
		}
		else if(deltaX == 0){ 
			return Math.min(Math.abs(deltaY), 1 - Math.abs(deltaY));
		}
		else if(deltaY == 0){ 
			return Math.min(Math.abs(deltaX), 1 - Math.abs(deltaX));
		}
		
		/*
		 * We now have two possibilities for our two points within our square:
		 * Either there's an upper-left and lower-right point or
		 * there's a lower-left and upper-right point (ie deltaY / deltaX is
		 * either positive or negative).
		 * 
		 * Before we delve into the code branching, however, let's set aside the 
		 * Euclidean distance between these points, which we'll call d.
		 * 
		 */
		
		double d = Math.sqrt(Math.pow(deltaX,2) + Math.pow(deltaY,2));
		
		// Figure out which of the two is to the right of the other.
		double[] rightMostPoint = deltaX > 0 ? q : p;
		double[] leftMostPoint = deltaX < 0 ? q : p;
		
		/*
		 * We'll also need the Euclidean distance between these two points after
		 * shifting rightMostPoint all the way around to the left
		 * via the transformation x |-> (x + 1 - rightMostPoint[0]) % 1.
		 * 
		 * After this transformation, we have the points
		 * (leftMostPoint[0] + 1 - rightMostPoint[0], leftMostPoint[1]) and
		 * (0,rightMostPoint[1])
		 */
		
		double dx = Math.sqrt(
				Math.pow( (leftMostPoint[0] + 1 - rightMostPoint[0]), 2) + 
				Math.pow(leftMostPoint[1] - rightMostPoint[1], 2)
				);
		
		/*
		 * We also do the same thing with a vertical shift, figuring out
		 * which point is above the other.
		 */
		
		double[] upperPoint = deltaY > 0 ? q : p;
		double[] lowerPoint = deltaX < 0 ? q : p;
		
		/*
		 * Same deal as above, we apply the transformation
		 * y |-> (y + 1 - upperPoint[1]) % 1
		 * 
		 * and we get the points
		 * (lowerPoint[0], lowerPoint[1] + 1 - upperPoint[1]) and
		 * (upperPoint[0], 0).
		 */
		
		double dy = Math.sqrt(
				Math.pow(lowerPoint[0] - upperPoint[0], 2) +
				Math.pow(lowerPoint[1] + 1 - upperPoint[1], 2)
				);
				
		
		
		//System.out.println("d = " + d + ", dx = " + dx + ", dy = " + dy);
		
		return Math.min(Math.min(d,dx), dy);
		
		/*
		 * I'll leave it as an exercise to you to show that we don't need
		 * to figure out what happens if we shift lowerPoint down towards the top
		 * of the torus or leftPoint back towards the right side.
		 */
			
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

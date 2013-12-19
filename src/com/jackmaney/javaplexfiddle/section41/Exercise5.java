package com.jackmaney.javaplexfiddle.section41;

import com.jackmaney.javaplexfiddle.Util;

public class Exercise5 {

	public static double kleinBottleDistance(double[] p,double[] q){
		if(p.length != 2 || q.length != 2){
			throw new IllegalArgumentException();
		}
		
		double x1 = p[0];
		double y1 = p[1];
		double x2 = q[0];
		double y2 = q[1];
		
		/*
		 * Our Klein bottle has flipped orientation on the left and right
		 * sides of the square, so we first check equality and if the points
		 * lie on a vertical line.
		 */
		
		double deltaX = x2 - x1;
		double deltaY = y2 - y1;
		
		if(deltaX == 0 && deltaY == 0){
			return 0;
		}
		else if(deltaX == 0){
			return Math.min(Math.abs(deltaY), 1 - Math.abs(deltaY));
		}
		
		/*
		 * Now, since the left and right sides of our square have opposite
		 * orientation, we have to compare d and dx (as defined in Exercise 4).
		 * 
		 * Again, d = sqrt(deltaX^2 + deltaY^2)
		 * 
		 * We now concentrate on dx. For the moment, assume that 
		 * deltaX and deltaY are both nonnegative (we'll take care of
		 * the other cases below, just like we did for Exercise 4).
		 * 
		 * Since the left and right sides of the square have opposite
		 * orientation, it appears as though we have two transformations
		 * to worry about:
		 * 	x |-> x + 1 - x2 (to the right)  and  x |-> x - x1 (to the left)
		 * 
		 * However, it turns out that the Euclidean distance of the images
		 * of P and Q after each of these transformations is the same.
		 * 
		 * To see why, just apply the transformations... For the first one,
		 * we get shifted points P'(x1 + 1 - x2,y1) and Q'(0,1-y2). 
		 * For the second transformation, we have shifted points 
		 * P''(1,1-y1) and Q''(x2 - x1,y2). The Euclidean distance
		 * between P' and Q' is the same as that between P'' and Q''.
		 */
		
		double d = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		double dx = Math.sqrt(
						Math.pow(1 - Math.abs(deltaX),2) 
						+ Math.pow(1 - y1 - y2,2)
				);
		
		if(deltaY == 0){
			return Math.min(d, dx);
		}
		
		/*
		 * So, again, we now either have a pair of upper-left and lower-right
		 * points or a pair of lower-left and upper-right points.
		 * 
		 * As in Exercise 4, we'll shift arguments appropriately so that 
		 * we end up with P on the lower-left and Q on the upper-right.
		 * 
		 * Note that our shift is vertical: 
		 * 		y |-> y + 1 - (y-coordinate of upper-left point)
		 */
		
		if((deltaX < 0 && deltaY > 0) || (deltaX > 0 && deltaY < 0)){
			double[] lowerRight = x1 < x2 ? q : p;
			double[] upperLeft = x2 < x1 ? q : p;
			
			double[] shiftedUL = {upperLeft[0],0};
			double[] shiftedLR = {lowerRight[0]
						,lowerRight[1] + 1 - upperLeft[1]};
			
			return kleinBottleDistance(shiftedUL, shiftedLR);
			
		}
		
		/*
		 * Again, if p is the upper-right point, flip the arguments.
		 */
		
		if(deltaX < 0){
			return kleinBottleDistance(q, p);
		}
		
		/*
		 * Now we have P on the lower left and Q on the upper right.
		 * We already have d and dx computed. Now, we only need dy.
		 * 
		 * Applying y |-> y + 1 - y2, we get P'(x1,y1 + 1 - y2) and
		 * Q'(x2,0)
		 */
		
		double dy = Math.sqrt(
					deltaX * deltaX + (1 - deltaY) * (1 - deltaY)
				);
		
		return Math.min(Math.min(d,dx), dy);
	}
	
	public static double[][] getDistanceMatrix(double[][] points){
		int n = points.length;
		
		double[][] result = new double[n][n];
		
		for(int i = 0; i < n; i++){
			for(int j = 0; j < n; j++){
				result[i][j] = kleinBottleDistance(points[i], points[j]);
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

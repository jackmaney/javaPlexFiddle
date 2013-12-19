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
			return Math.max(Math.abs(deltaY), 1 - Math.abs(deltaY));
		}
		else if(deltaY == 0){ 
			return Math.max(Math.abs(deltaX), 1 - Math.abs(deltaX));
		}
		
		/*
		 * We now have two possibilities for our two points within our square:
		 * Either there's an upper-left and lower-right point or
		 * there's a lower-left and upper-right point (ie deltaY / deltaX is
		 * either positive or negative).
		 * 
		 * To prevent further code branching, we'll check to see if the
		 * slope is negative and if so, apply the horizontal shift
		 * 				x |-> x + 1 - (lower-right x coordinate)
		 * and start over. (Note that we could have just as easily
		 * shifted vertically)
		 */
		
		if((deltaX < 0 && deltaY > 0) || (deltaX > 0 && deltaY < 0)){
			double[] lowerRight = x1 < x2 ? q : p;
			double[] upperLeft = x2 < x1 ? q : p;
			
			double[] shiftedLR = {0,lowerRight[1]};
			double[] shiftedUL = {upperLeft[0] + 1 - lowerRight[0]
					,upperLeft[1]};
			
			return torusDistance(shiftedLR, shiftedUL);
			
		}
		
		/*
		 * Now, we have deltaX and deltaY both of the same sign.
		 * If they're both negative, then p is to the upper-right
		 * and q is to the upper-left. Let's flip that around, shall we?
		 */
		
		if(deltaX < 0 && deltaY < 0){
			return torusDistance(q, p);
		}
		
		/*
		 * Now we can finally assume that p is to the lower-left and
		 * q is to the upper-right.
		 * 
		 * Since we're on a torus, we have three different numbers to compare:
		 * 
		 * 		* d, the normal Euclidean distance between q and p.
		 * 		* dx, the Euclidean distance after shifting q over to the
		 * 				left side of the square via the transformation
		 * 				x |-> x + 1 - x2
		 * 		* dy, the Euclidean distance after shifting q vertically
		 * 				to the bottom of the square via the transformation
		 * 				y |-> y + 1 - y2
		 * 
		 * Note that since x1 < x2 and y1 < y2, these transformations don't 
		 * wrap p around the torus (ie x1 + 1 - x2 and y1 + 1 -y2 are still
		 * less than 1).
		 * 
		 * We already know that d = sqrt(deltaX^2 + deltaY^2).
		 * 
		 * For dx, our points become P'(x1 + 1 - x2,y1) = (1-deltaX,y1)
		 * and Q'(0,y2). So, dx = sqrt((1 - deltaX)^2 + deltaY^2).
		 * 
		 * Similarly, dy = sqrt(deltaX^2 + (1 - deltaY)^2).
		 */
		
		double d = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
		double dx = Math.sqrt(
					(1 - deltaX)*(1-deltaX) + deltaY * deltaY
				);
		double dy = Math.sqrt(
					deltaX * deltaX + (1-deltaY)*(1-deltaY)
				);
		
		return Math.min(Math.min(d,dx), dy);
		
		/*
		 * Fun trivia fact: d <= dx iff deltaX <= 1/2 and
		 * d <= dy iff deltaY <= 1/2.
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

package com.jackmaney.javaplexfiddle.section41;

import sun.reflect.ReflectionFactory.GetReflectionFactoryAction;

public class Exercise4 {

	public static double[][] randomPointsFromUnitSquare(int n){
		if(n <= 0){
			throw new IllegalArgumentException();
		}
		
		double[][] result = new double[n][2];
		
		for(int i = 0; i < n; i++){
			result[i][0] = Math.random();
			result[i][1] = Math.random();
		}
		
		return result;
	}
	
	public static double torusDistance(double[] p,double[] q){
		if(p.length != 2 || q.length != 2){
//			System.out.println("wtf");
//			for(int i = 0; i < p.length; i++){
//				System.out.println(p[i]);
//			}
//			System.out.println("hmmm...");
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
		
		double deltaX = Math.abs(x1 - x2);
		double deltaY = Math.abs(y1 - y2);
		
		if(deltaX == 0 && deltaY == 0){
			return 0;
		}
		else if(deltaX == 0){ //horizontal
			return Math.max(deltaY, 1 - deltaY);
		}
		else if(deltaY == 0){ //vertical
			return Math.max(deltaX, 1 - deltaX);
		}
		else{
			/*
			 * Now, we can assume that we have an upper-right point
			 * and a lower-left point. WLOG, assume they're p and q (resp).
			 * 
			 * Now, let d denote the usual Euclidean distance. If y2 - y1
			 * is at least 1/2, we apply the shifting transformation
			 * y -> y + (1 - y2) to get new points P'(x1,y1 + 1 - y2)
			 * and Q'(x2,0). (Note that since y2 - y1 >= 1/2, we have
			 * y1 - y2 + 1 <= 1/2.) 
			 * 
			 * Denoting by dy the distance between P' and Q' (ie the 
			 * distance on the torus going through the "top" and "bottom" 
			 * of the square), our condition of y2 - y1 <= 1/2 
			 * forces d <= dy; in fact, y2 - y1 <= 1/2 if and only if 
			 * d <= dy. 
			 * 
			 * Replacing y with x--and getting an associated distance dx--we
			 * see that x2 - x1 <= 1/2 if and only if d <= dx.
			 * 
			 */
			double d = Math.sqrt(deltaX * deltaX + deltaY * deltaY);
			double dy = Math.sqrt(deltaX * deltaX + (1-deltaY)*(1-deltaY));
			double dx = Math.sqrt((1-deltaX)*(1-deltaX) + deltaY*deltaY);
			//System.out.println("d,dx,dy = " + d + ", " + dx + ", " + dy);
			
			if(deltaX <= 0.5 && deltaY <= 0.5){
				return d;
			}
			else if(deltaY > 0.5 && deltaX<=0.5){
				return dy;
			}
			else if(deltaY <= 0.5 && deltaX > 0.5){
				return dx;
			}
			else{
				return Math.min(dx,dy);
			}
			
			
		}
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
		double[][] points = randomPointsFromUnitSquare(n);
		
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

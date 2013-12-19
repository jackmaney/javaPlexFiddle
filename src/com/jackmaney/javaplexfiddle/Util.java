package com.jackmaney.javaplexfiddle;

public class Util {
	public static int[] intRange(int a,int b){
		if(a > b){
			throw new IllegalArgumentException();
		}
		
		int[] result = new int[b-a+1];
		
		for(int i = a; i <= b; i++){
			result[i-a] = i;
		}
		
		return result;
	}
	
	public static int[] intRange(int a){
		return intRange(0,a);
	}
	
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
	
	public static double[][] randomPointsFromUnitSphere(int n){
		if(n <= 0){
			throw new IllegalArgumentException();
		}
		
		double[][] result = new double[n][3];
		
		for(int i = 0; i < n; i++){
			
			//Spherical Coordinates!
			double phi = Math.PI * Math.random();
			double theta = 2 * Math.PI * Math.random();
			
			
			double x = Math.cos(theta) * Math.sin(phi);
			double y = Math.sin(theta) * Math.sin(phi);
			double z = Math.cos(phi);
			
			result[i][0] = x;
			result[i][1] = y;
			result[i][2] = z;
		}
		
		return result;
		
	}
	
	public static double dotProduct(double[] p,double[] q){
		if(p.length != q.length){
			throw new IllegalArgumentException();
		}
		
		double result = 0;
		for(int i = 0; i < p.length; i++){
			result += p[i] * q[i];
		}
		
		return result;
	}
	
	//Gah....
	
	public static double arrayMax(double[] x){
		if(x.length == 0){
			throw new IllegalArgumentException();
		}
		
		double result = x[0];
		
		for(int i = 1; i < x.length; i++){
			if(result < x[i]){
				result = x[i];
			}
		}
		
		return result;
	}
	
	public static double arrayMin(double[] x){
		if(x.length == 0){
			throw new IllegalArgumentException();
		}
		
		double result = x[0];
		
		for(int i = 1; i < x.length; i++){
			if(result > x[i]){
				result = x[i];
			}
		}
		
		return result;
	}
}

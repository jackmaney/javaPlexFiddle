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
}

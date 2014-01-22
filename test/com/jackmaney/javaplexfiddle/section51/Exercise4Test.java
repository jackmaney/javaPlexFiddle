package com.jackmaney.javaplexfiddle.section51;

import static org.junit.Assert.*;

import org.junit.Test;

import com.jackmaney.javaplexfiddle.section41.Exercise4;

public class Exercise4Test {

	@Test
	public void horizontalWrap() {
		double[] a = {0.1,0.1};
		double[] b = {0.9,0.1};
		
		System.out.println(Exercise4.torusDistance(a, b));
		assertTrue(Exercise4.torusDistance(a, b) == 0.2);
	}
	
	@Test
	public void verticalWrap(){
		double[] a = {0.5,0.2};
		double[] b = {0.5,0.99};
		
		assertTrue(Exercise4.torusDistance(a, b) == 0.21);
	}

}

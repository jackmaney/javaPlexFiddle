package com.jackmaney.javaplexfiddle.section51;

import static org.junit.Assert.*;

import java.util.Arrays;

import org.junit.Test;

import com.jackmaney.javaplexfiddle.section41.Exercise4;

public class Exercise4Test {

	@Test
	public void horizontalWrap() {
		double[] a = {0.1,0.1};
		double[] b = {0.9,0.1};
		
		assertTrue(Math.abs(Exercise4.torusDistance(a, b) - 0.2) < 1e-5);
	}
	
	@Test
	public void verticalWrap(){
		double[] a = {0.5,0.2};
		double[] b = {0.5,0.99};
		assertTrue(Math.abs(Exercise4.torusDistance(a, b) - 0.21) < 1e-5);
	}
	
	@Test
	public void noWrap(){
		double[] a = {0.5,0.5};
		double[] b = {0.6,0.7};
		
		double expected = Math.sqrt(0.1 * 0.1 + 0.2 * 0.2);
		assertTrue(Math.abs(expected - Exercise4.torusDistance(a, b)) < 1e-5);
	}
	
	@Test
	public void horizontalWrapPosSlope(){
		double[] a = {0.2,0.1};
		double[] b = {0.9, 0.7};
		
		double expected = Math.sqrt(0.3 * 0.3 + 0.6 * 0.6);

		assertTrue(Math.abs(expected - Exercise4.torusDistance(a, b)) < 1e-5);
	}
	
	@Test
	public void horizontalWrapNegSlope(){
		double[] a = {0.9,0.1};
		double[] b = {0.05,0.85};
		
		double expected = Math.sqrt(0.15 * 0.15 + 0.75 * 0.75);
		
		assertTrue(Math.abs(expected - Exercise4.torusDistance(a, b)) < 1e-5);
	}
	
	@Test
	public void verticalWrapPosSlope(){
		double[] a = {0.5,0.1};
		double[] b = {0.7,0.99};
		
		double expected = Math.sqrt(0.2* 0.2 + 0.11 * 0.11);
		assertTrue(Math.abs(expected - Exercise4.torusDistance(a, b)) < 1e-5);
	}
	
	@Test
	public void verticalWrapNegSlope(){
		double[] a = {0.1,0.01};
		double[] b = {0.5,0.7};
		
		double expected = Math.sqrt(0.4 * 0.4 + 0.31 * 0.31);
		
		assertTrue(Math.abs(expected - Exercise4.torusDistance(a, b)) < 1e-5);
	}

}

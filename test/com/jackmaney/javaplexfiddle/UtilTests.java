package com.jackmaney.javaplexfiddle;

import static org.junit.Assert.*;

import org.junit.Test;

public class UtilTests {

	@Test
	public void basicIntRangeTest() {
		int a = 3;
		int b = 8;
		int[] range = Util.intRange(a, b);

		assertTrue(range.length == 6);
		for(int i = 3; i <= 8; i++){
			assertTrue(range[i-3] == i);
		}
	}
	
	@Test
	public void equalsIntRangeTest(){
		int[] range = Util.intRange(100, 100);
		assertTrue(range.length == 1);
		assertTrue(range[0] == 100);
	}
	
	@Test
	public void beginZeroIntRangeTest(){
		int[] range = Util.intRange(50);
		
		assertTrue(range.length == 51);
		for(int i = 0; i <= 50; i++){
			assertTrue(range[i] == i);
		}
	}

}

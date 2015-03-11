package com.xebia.tdd.training.chapter1;

import org.junit.Assert;
import org.junit.Test;

public class UnitTest {

	@Test
	public void shouldSumTheIntegersCorrectly() {
		// Setup
		Unit unit = new Unit();
		Integer a = 10;
		Integer b = 20;
		
		//Execution
		Integer sum = unit.sum(a, b);
		
		
		//Verification
		Assert.assertTrue(sum == 30);
		
	}

	
	
}

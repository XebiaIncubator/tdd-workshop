package com.xebia.tdd.training.chapter1;

import static org.junit.Assert.assertTrue;

import org.junit.Test;


public class UnitTest {


	// Tests for Sum
	@Test
	public void shouldAddIntegersCorrectly() {
		// Setup
		Unit unit = new Unit();
		Integer a = 10;
		Integer b = 20;

		// Execution
		Integer sum = unit.sum(a, b);

		// Verification
		assertTrue(30 == sum);

	}

	@Test
	public void shouldNotThrowNullPointerExceptionForNullArguments()
			throws Exception {
		// Setup
		Unit unit = new Unit();
		Integer a = 10;
		Integer b = null;

		// Execution
		Integer sum = unit.sum(a, b);

		// Verification
		assertTrue(10 == sum);

		// Execution
		sum = unit.sum(b, a);

		// Verification
		assertTrue(10 == sum);
	}

}

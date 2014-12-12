package com.xebia.tdd.training.chapter1;

import static org.junit.Assert.assertTrue;

import java.util.Arrays;
import java.util.Collection;

import org.junit.Test;
import org.junit.runner.RunWith;
import org.junit.runners.Parameterized;

@RunWith(Parameterized.class)
public class UnitTestWithParameters {

	private Integer a;
	private Integer b;
	private Integer result;
	private Unit unit;

	@Test
	public void shouldNotThrowNullPointerExceptionForNullArguments()
			throws Exception {

		// Setup
		unit = new Unit();
		
		// Execution
		Integer sum = unit.sum(a, b);

		// Verification
		assertTrue(result == sum);

	}

	public UnitTestWithParameters(Integer a, Integer b, Integer result) {
		this.a = a;
		this.b = b;
		this.result = result;
	}

	@SuppressWarnings("rawtypes")
	@Parameterized.Parameters
	public static Collection primeNumbers() {
		return Arrays.asList(new Object[][] { { 1, null, 1 }, { null, 1, 1 },
				{ null, null, 0 } });
	}
}

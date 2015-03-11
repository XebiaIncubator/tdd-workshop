package com.xebia.tdd.training.chapter1;

import static org.hamcrest.Matchers.contains;
import static org.hamcrest.Matchers.equalTo;
import static org.junit.Assert.assertThat;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;

public class UnitTestWithHamchrest {

	@Test
	public void shouldSumNegativeIntegersCorrectly() throws Exception {
		// Setup
		Unit unit = new Unit();
		Integer a = -10;
		Integer b = null;

		// Execution
		Integer sum = unit.sum(a, b);

		// Verification
		// Assert using Hamchrest
		assertThat(-10, equalTo(sum));
	}

	
	@Test
	public void shouldContainTheString(){
		List<String> remoteList = getRemoteList();

		Assert.assertTrue(remoteList.contains("Scala"));
		Assert.assertTrue(remoteList.contains("Java"));
		
		
		
		Assert.assertThat(remoteList, contains("Scala","Java"));
	}


	private List<String> getRemoteList() {
		return Arrays.asList("Scala","Java");
	}
}

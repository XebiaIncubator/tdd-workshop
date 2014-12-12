package com.xebia.tdd.training.chapter1;

import java.io.BufferedInputStream;
import java.io.File;

import org.junit.Test;

@SuppressWarnings("unused")
public class UnitTestEnvDependent {

	@Test
	public void shouldParseFileCorrectly() {
		File file = new File("/home/pratik/abc.xml");

		BufferedInputStream bufferedInputStream = new BufferedInputStream(
				UnitTestEnvDependent.class.getClassLoader()
						.getResourceAsStream("abc.xml"));

	}

}

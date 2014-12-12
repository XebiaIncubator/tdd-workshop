package com.xebia.tdd.training.chapter2;

public class StringCalculator {

	public int add(String numbersString) {
		if(numbersString !=null && numbersString == "")
			return 1;
		int result = 0;
		String[] split = numbersString.split(",");
		for (String string : split) {
			result = Integer.parseInt(string);
		}
		return result;
	}

}

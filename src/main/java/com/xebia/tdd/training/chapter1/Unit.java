package com.xebia.tdd.training.chapter1;

public class Unit {

	public Integer sum(Integer a, Integer b) {
		return getNonNUllValue(b) + getNonNUllValue(a);
	}

	private Integer getNonNUllValue(Integer b) {
		return b == null ? 0 : b;
	}

	public Integer subtract(Integer a, Integer b) {
		return b - a;
	}

}

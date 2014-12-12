package com.xebia.tdd.training.chapter1;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

public class Sort {

	public List<String> mergeAndSort(List<String>... lists) {
		List<String> finalList = new ArrayList<String>();
		for (List<String> list : lists) {
			finalList.addAll(list);
		}
		Collections.sort(finalList);
		return finalList;
	}
}

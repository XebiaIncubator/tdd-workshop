package com.xebia.tdd.training.chapter3;

import java.util.Collections;
import java.util.Comparator;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class CitySort_bkpo {

	@Autowired
	ConfigurationsDao_bkp configurationsDao;

	public List<String> sort(List<String> listOfCities) {
		final CitySortOrder_bkp sortOrder = configurationsDao.getSortOrder();
		Collections.sort(listOfCities, new Comparator<String>() {

			@Override
			public int compare(String o1, String o2) {
				if (sortOrder.equals(CitySortOrder_bkp.ASC)) {
					return o1.compareTo(o2);
				}
				return o2.compareTo(o1);
			}
		});
		return listOfCities;
	}

}

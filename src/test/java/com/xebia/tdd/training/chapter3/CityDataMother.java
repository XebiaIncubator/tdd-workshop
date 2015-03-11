package com.xebia.tdd.training.chapter3;

import java.util.Arrays;
import java.util.List;

public class CityDataMother {

	static List<City> getCities() {
		List<City> cities= Arrays.asList(new City("Delhi"), new City("Gurgaon"), new City("Noida"));
		return cities;
	}

}

package com.xebia.tdd.training.legacy1;

import java.util.List;

public class AdvertisementServiceProvider2 {

	public List<Advertisement> getAdvertisements() {
		throw new RuntimeException("Cannot connect to the service provider");
	}

}

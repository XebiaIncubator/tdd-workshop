package com.xebia.tdd.training.legacy1;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

public class AdvertisementService {


	public List<Advertisement> getAllAdvertisements() {
		List<Advertisement> advertisements = new ArrayList<Advertisement>();
		Map<String,Advertisement> advertisementsMap = new HashMap<String, Advertisement>();
		
		List<Advertisement> provider1Advertisements = new AdvertisementServiceProvider1().getAdvertisements();
		List<Advertisement> provider2Advertisements = new AdvertisementServiceProvider2().getAdvertisements();

	
		advertisements.addAll(provider1Advertisements);
		advertisements.addAll(provider2Advertisements);
		
		for (Advertisement advertisement : advertisements) {
			advertisementsMap.put(advertisement.getName(), advertisement);
		}
		
		return new ArrayList<Advertisement>(advertisementsMap.values());
	}
}

package com.xebia.tdd.training.legacy1;

public class AdvertisementController {

	public void show(){
		AdvertisementService advertisementService = new AdvertisementService();
		advertisementService.getAllAdvertisements();
	}
}

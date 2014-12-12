package com.xebia.tdd.training.legacy1;

import java.util.ArrayList;
import java.util.List;

import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.Mockito;

public class AdvertisementServiceTest {

	private AdvertisementService advertisementService;
	private AdvertisementServiceProvider1 serviceProvider1;
	private AdvertisementServiceProvider2 serviceProvider2;


	@Before
	public void setup(){
		advertisementService = new AdvertisementService();
		serviceProvider1 = Mockito.mock(AdvertisementServiceProvider1.class);
		serviceProvider2 = Mockito.mock(AdvertisementServiceProvider2.class);

	}
	
	//@Test
	public void shouldGetUniqueAdvertisements(){
		Mockito.when(serviceProvider1.getAdvertisements()).thenReturn(AdvertisementDataMother.getAdvertisements("Google","Yahoo"));
		Mockito.when(serviceProvider2.getAdvertisements()).thenReturn(AdvertisementDataMother.getAdvertisements("Google","Microsoft"));
		
		List<Advertisement> allAdvertisements = advertisementService.getAllAdvertisements();
		
		assertUniqueNames(allAdvertisements);
		
	}


	private void assertUniqueNames(List<Advertisement> allAdvertisements) {
		List<String> uniqueNames = new ArrayList<String>();
		for (Advertisement ad : allAdvertisements) {
			if(uniqueNames.contains(ad.getName())){
				System.out.println(ad.getName());
				Assert.fail("Non Unique Names");
			}
			else{
				uniqueNames.add(ad.getName());
			}
		}
	}
}

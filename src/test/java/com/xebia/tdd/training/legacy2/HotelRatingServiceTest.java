package com.xebia.tdd.training.legacy2;

import java.util.Arrays;
import java.util.List;

import org.junit.Assert;
import org.junit.Test;
import org.mockito.Mockito;

import com.xebia.tdd.training.dao.HotelRatingDAO;

public class HotelRatingServiceTest {

	private HotelRatingDAO hotelRatingDAO;
	private EmailService emailService;

	@org.junit.Before
	public void setup() {
		hotelRatingDAO = Mockito.mock(HotelRatingDAO.class);
	}

	@Test
	public void shouldReturnHotelRatingsCorrectly() {
		HotelRatingService hotelRatingService = new HotelRatingServiceExt();

		hotelRatingService.setHotelRatingDAO(hotelRatingDAO);
		hotelRatingService.setEmailService(emailService);
		Mockito.when(hotelRatingDAO.getAllHotelRatings()).thenReturn(Arrays.asList(new HotelRating()));

		List<HotelRating> updatedHotelRatings = hotelRatingService.getUpdatedHotelRatings();
		Assert.assertTrue(1 == updatedHotelRatings.size());
		// More Assertions to test ratings
		
	}

	class HotelRatingServiceExt extends HotelRatingService {
		@Override
		public void sendEmailToAdmin(List<HotelRating> allHotelRatings) {

		}
	}

}

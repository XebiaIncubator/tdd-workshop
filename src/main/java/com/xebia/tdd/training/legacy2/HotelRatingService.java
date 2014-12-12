package com.xebia.tdd.training.legacy2;

import java.util.List;

import com.xebia.tdd.training.dao.HotelRatingDAO;

public class HotelRatingService {

	private HotelRatingDAO hotelRatingDAO;
	private EmailService emailService;
	
	public List<HotelRating> getUpdatedHotelRatings(){
		List<HotelRating> allHotelRatings = hotelRatingDAO.getAllHotelRatings();
		sendEmailToAdmin(allHotelRatings);
		sendEmailToXYZ(new Object());
		updateRemoteDatabase(new Object());
		return allHotelRatings;
	}

	public void updateRemoteDatabase(Object object) {
		// TODO Auto-generated method stub
		
	}

	public void sendEmailToXYZ(Object object) {
		// TODO Auto-generated method stub
		
	}

	public void sendEmailToAdmin(List<HotelRating> allHotelRatings) {
		if(allHotelRatings == null || allHotelRatings.size() < 5 ){
			Email email = new Email("No Hotel Ratings Present");
			emailService.sendEmailToAdmin(email);
		}
	}

	public void setHotelRatingDAO(HotelRatingDAO hotelRatingDAO) {
		this.hotelRatingDAO = hotelRatingDAO;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
}

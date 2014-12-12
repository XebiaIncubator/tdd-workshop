package com.xebia.tdd.training.legacy6;

import com.xebia.tdd.training.legacy3.User;


public class ReservationService {

	public boolean doReservation(User user , Booking booking) throws Exception{
		
		boolean isAvailable = checkRoomAvailability(booking);
		if(!isAvailable) throw new Exception("Please Do a New Booking, Room Unavailable");
		
		boolean isValidCoupan = validateCoupan(booking);
		if(!isValidCoupan) throw new Exception("Coupan is Invalid");

		booking.isCouponValid(isValidCoupan);
		boolean paymentSuccessfull = doPayment(user, booking);
		if(!paymentSuccessfull) throw new Exception("Payment Failure");

		boolean reservationComplete = reserveRoom(booking);
		if(!reservationComplete) throw new Exception("Reservation Failed");
		
		return true;
	}

	public boolean reserveRoom(Booking booking) {
		RoomService roomService = new RoomService();
		boolean reservationComplete = roomService.reserve(booking);
		return reservationComplete;
	}

	public boolean checkRoomAvailability(Booking booking) {
		RoomService roomService = new RoomService();
		boolean isAvailable = roomService.isRoomAvailable(booking.getHotel(), booking.getRoomType());
		return isAvailable;
	}

	public boolean validateCoupan(Booking booking) {
		boolean isValidCoupan = CouponValidator.validatCoupans(booking);
		return isValidCoupan;
	}

	public boolean doPayment(User user, Booking booking) throws Exception {
		PaymentService paymentService = new PaymentService();
		return paymentService.doPayment(user,booking);
	}
}

package com.xebia.tdd.training.legacy2;

import com.xebia.tdd.training.legacy3.User;


public class EmailService {

	public void sendEmailToAdmin(Email email) {
		throw new RuntimeException("Cannot connect to IMAP server");
	}

	public void sendEmailToUser(User user) {
		throw new RuntimeException("Cannot connect to IMAP server");
	}

}

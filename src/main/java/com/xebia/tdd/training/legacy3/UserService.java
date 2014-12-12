package com.xebia.tdd.training.legacy3;

import com.xebia.tdd.training.legacy2.EmailService;

public class UserService {

	private UserDAO userDAO;
	private EmailService emailService;

	public boolean saveUser(User user) {
		user.setName("Name1");
		userDAO = new UserDAO();
		userDAO.save(user);

		sendEmailToUser(user);
		return true;
	}

	private boolean sendEmailToUser(User user) {
		emailService.sendEmailToUser(user);
		return true;
	}

	public void setUserDAO(UserDAO userDAO) {
		this.userDAO = userDAO;
	}

	public EmailService getEmailService() {
		return emailService;
	}

	public void setEmailService(EmailService emailService) {
		this.emailService = emailService;
	}
}

package com.xebia.tdd.training.legacy2;

public class Email {

	private String subject;
	private Integer nextAvailableNumber;
	private String message;

	public Email(String message) {
		this.message = message;
	}

	public String getSubject() {
		return subject;
	}

	public void setUniqueId(Integer nextAvailableNumber) {
		this.nextAvailableNumber = nextAvailableNumber;
		
	}

	public void setSubject(String subject) {
		this.subject = subject;
	}

	public Integer getNextAvailableNumber() {
		return nextAvailableNumber;
	}

	public void setNextAvailableNumber(Integer nextAvailableNumber) {
		this.nextAvailableNumber = nextAvailableNumber;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

}

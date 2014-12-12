package com.xebia.tdd.training.legacy5;

import java.util.List;

public class PaymentOptionsFactory {

	private PaymentOptionDAO paymentOptionsDAO;
	private List<PaymentOption> paymentOptions;
	
	public PaymentOptionsFactory() {
		this.paymentOptions = paymentOptionsDAO.getPaymentOptions();
	}

	public PaymentOption getPaymentOption(String optionName){
		for (PaymentOption option : getAllPaymentOptions()) {
			if(option.getName().equals(optionName))
				return option;
		}
		return null;
	}
	
	public void setPaymentOptionsDAO(PaymentOptionDAO paymentOptionsDAO) {
		this.paymentOptionsDAO = paymentOptionsDAO;
	}

	 List<PaymentOption> getAllPaymentOptions() {
		return paymentOptions;
	}
}

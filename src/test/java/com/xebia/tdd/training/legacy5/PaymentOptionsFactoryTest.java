package com.xebia.tdd.training.legacy5;

import java.util.Arrays;

import org.junit.Assert;
import org.junit.Test;
import org.junit.runner.RunWith;
import org.mockito.Mockito;
import org.powermock.api.mockito.PowerMockito;
import org.powermock.core.classloader.annotations.PrepareForTest;
import org.powermock.modules.junit4.PowerMockRunner;

@RunWith(PowerMockRunner.class)
@PrepareForTest({PaymentOptionsFactory.class})
public class PaymentOptionsFactoryTest {

	@Test
	public void shouldReturnThePaymentOptionCorrectly() throws Exception {
		PaymentOptionsFactory mock = PowerMockito.mock(PaymentOptionsFactory.class);
		PowerMockito.whenNew(PaymentOptionsFactory.class).withNoArguments().thenReturn(mock);
		PowerMockito.when(mock.getPaymentOption(Mockito.anyString())).thenCallRealMethod();
		PowerMockito.when(mock.getAllPaymentOptions()).thenReturn(Arrays.asList(new PaymentOption("CreditCard"), new PaymentOption("Bank")));

		PaymentOption paymentOption = mock.getPaymentOption("CreditCard");
		Assert.assertNotNull(paymentOption);
		
	}

}

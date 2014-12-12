package com.xebia.tdd.training.chapter4;

import org.hamcrest.Matcher;
import org.junit.Assert;
import org.junit.Before;
import org.junit.Test;
import org.mockito.ArgumentMatcher;
import org.mockito.InOrder;
import org.mockito.Mockito;

public class HotelEmployeeServiceTest {

	private HotelEmployeeService employeeService;
	private HotelEmployeeDAO hotelEmployeeDAO;
	private HotelEmployeeMailService emailService;

	@Before
	public void setup() {
		employeeService = new HotelEmployeeService();
		hotelEmployeeDAO = Mockito.mock(HotelEmployeeDAO.class);
		employeeService.setHotelEmployeeDAO(hotelEmployeeDAO);
	}

	// Interaction Testing Scenario
	@Test
	public void shouldSearchEmployeeCorrectly() {
		EmployeeSearchCriteria searchParameters = new EmployeeSearchCriteria();
		searchParameters.setEmployeeAge(12);
		searchParameters.setEmployeeCountry("IN");
		searchParameters.setEmployeeName("Name");

		employeeService.searchEmployee(searchParameters);

		Mockito.verify(hotelEmployeeDAO, Mockito.times(1)).searchEmployee(
				Mockito.eq("Name"), Mockito.eq(12), Mockito.anyString());
	}

	// Argument Matchers Scenario
	@Test
	public void shouldUpdateEmployeeCorrectly() throws Exception {

		employeeService.updateEmployee(new HotelEmployee());

		Matcher<HotelEmployee> matcher = new ArgumentMatcher<HotelEmployee>() {
			@Override
			public boolean matches(Object argument) {
				HotelEmployee employee = (HotelEmployee) argument;
				Assert.assertTrue(1 == employee.getSerialid());
				return true;
			}
		};

		Mockito.verify(hotelEmployeeDAO, Mockito.times(1)).updateEmployee(
				Mockito.argThat(matcher));
		Mockito.verifyNoMoreInteractions(hotelEmployeeDAO);
	}

	// Exception Throws Scenario
	@Test(expected = IllegalArgumentException.class)
	public void shouldThrowIllegalArgumentExceptionOnUpdateIfNoSerialId()
			throws Exception {

		HotelEmployee employee = new HotelEmployee();

		Mockito.when(hotelEmployeeDAO.updateEmployee(Mockito.eq(employee)))
				.thenThrow(new IllegalArgumentException());

		employeeService.updateEmployee(employee);

	}
	
	

	// Mocks Execution Order Scenario
	@Test
	public void shouldSendEmailAfterUpdatingTheDatabase() throws Exception {

		emailService = Mockito.mock(HotelEmployeeMailService.class);
		employeeService.setHotelEmployeeEmailService(emailService);

		HotelEmployee employee = new HotelEmployee();

		// Execution
		employeeService.updateEmployeeEmailAddress(employee, "a@a.com");
		
		

		InOrder inOrder = Mockito.inOrder(emailService, hotelEmployeeDAO);
		inOrder.verify(hotelEmployeeDAO).updateEmployee(Mockito.eq(employee));
		inOrder.verify(emailService).sendEmail(Mockito.eq(employee));

		Mockito.verifyNoMoreInteractions(hotelEmployeeDAO);
		Mockito.verifyNoMoreInteractions(emailService);

	}

	@org.junit.After
	public void tearDown() {
		Mockito.reset(hotelEmployeeDAO);
	}
}

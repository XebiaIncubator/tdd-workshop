package com.xebia.tdd.training.chapter4;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;

public class HotelEmployeeService {

	@Autowired
	HotelEmployeeDAO hotelEmployeeDAO;

	@Autowired
	HotelEmployeeMailService mailService;

	public List<HotelEmployee> searchEmployee(EmployeeSearchCriteria searchCriteria) {
	
		return hotelEmployeeDAO.searchEmployee(searchCriteria.getEmployeeName(),
				searchCriteria.getEmployeeAge(),searchCriteria.getEmployeeCountry());
		
	}
	
	
	public boolean updateEmployee(HotelEmployee employee) throws Exception {
		employee.setSerialId(employee.getSerialid() + 1);
		return hotelEmployeeDAO.updateEmployee(employee);
	}
	
	
	public void updateEmployeeEmailAddress(HotelEmployee employee, String emailAddress) throws Exception {
		hotelEmployeeDAO.updateEmployee(employee);
		mailService.sendEmail(employee);
	}

	public void setHotelEmployeeDAO(HotelEmployeeDAO hotelEmployeeDAO2) {
		hotelEmployeeDAO = hotelEmployeeDAO2;
	}

	public void setHotelEmployeeEmailService(
			HotelEmployeeMailService emailService) {
		mailService = emailService;
	}

}

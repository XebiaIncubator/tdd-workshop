package com.xebia.tdd.training.chapter4;

import java.util.List;

public class HotelEmployeeDAO {

	public boolean saveEmployee(HotelEmployee employee) {
		// Save employee in database
		return true;
	}

	public boolean updateEmployee(HotelEmployee employee) throws Exception {
			throw new Exception("Error while saving object to database");
	}

	public List<HotelEmployee> searchEmployee(String employeeName,
			Integer employeeAge, String employeeCountry) {
		// Search the result in the database
		return null;
	}

}

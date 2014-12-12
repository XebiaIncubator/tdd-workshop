package com.xebia.tdd.training.chapter4;

import java.util.Date;

public class HotelEmployee {

	private Integer serialid = 0;
	private Date modifiedDate;
	private String employeeName;
	private Integer employeeAge;
	private String employeeCountry;
	
	public void setSerialId(Integer serialid) {
		this.serialid = serialid;
	}

	public void setModifiedDate(Date modifiedDate) {
		this.modifiedDate = modifiedDate;
		
	}

	public Integer getSerialid() {
		return serialid;
	}

	public void setSerialid(Integer serialid) {
		this.serialid = serialid;
	}

	public Date getModifiedDate() {
		return modifiedDate;
	}

	public String getEmployeeName() {
		return employeeName;
	}

	public void setEmployeeName(String employeeName) {
		this.employeeName = employeeName;
	}

	public Integer getEmployeeAge() {
		return employeeAge;
	}

	public void setEmployeeAge(Integer employeeAge) {
		this.employeeAge = employeeAge;
	}

	public String getEmployeeCountry() {
		return employeeCountry;
	}

	public void setEmployeeCountry(String employeeCountry) {
		this.employeeCountry = employeeCountry;
	}

}

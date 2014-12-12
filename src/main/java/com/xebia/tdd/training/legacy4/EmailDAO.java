package com.xebia.tdd.training.legacy4;

import com.xebia.tdd.training.legacy2.Email;

public class EmailDAO {

	public boolean saveEmail(Email email){
		if(email.getSubject()== null) throw new RuntimeException("Name Cannot be empty");
		
		email.setUniqueId(DBUtils.getNextAvailableNumber());

		// Save in database later
		return true;
	}
}

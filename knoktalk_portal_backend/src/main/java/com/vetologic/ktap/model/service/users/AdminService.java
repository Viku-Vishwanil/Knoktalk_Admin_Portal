package com.vetologic.ktap.model.service.users;

import com.vetologic.ktap.beans.users.AdminBean;

/**
 * @author Faiser : 01-Sep-2020
 *
 */
public interface AdminService {

	AdminBean getUserByName(String username);
	
	boolean update(Object object);
	
	AdminBean getUserDetailsByEmailId(String emailId);
}

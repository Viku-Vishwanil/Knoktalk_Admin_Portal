package com.vetologic.ktap.model.service.users;

import java.util.List;

/**
 * @author Faiser : 07-Sep-2020
 *
 */
public interface VerificationRequestService {

	List<?> getAll(String beanClassName);

	boolean update(Object object);
	
	Object getById(String beanClassName, String id);
	
	List<?> getVerificationAndUserList();

}

package com.vetologic.ktap.model.service.users;

import java.util.List;

/**
 * @author Faiser : 02-Sep-2020
 *
 */
public interface UsersService {

	List<?> getAll(String beanClassName);
	
	boolean update(Object object);
	
	Object getById(String beanClassName, int id);
	
}

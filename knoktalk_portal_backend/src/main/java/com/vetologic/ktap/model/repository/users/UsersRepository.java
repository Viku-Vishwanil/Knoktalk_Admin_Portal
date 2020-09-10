package com.vetologic.ktap.model.repository.users;

import java.util.List;

/**
 * @author Faiser : 02-Sep-2020
 *
 */
public interface UsersRepository {
	
	List<?> getAll(String beanClassName);

	boolean update(Object object);
	
	Object getById(String beanClassName, int id);
	
}

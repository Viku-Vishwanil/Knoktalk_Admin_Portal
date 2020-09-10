/**
 * 
 */
package com.vetologic.ktap.model.repository.wallet;

import java.util.List;

/**
 * @author Faiser : 07-Sep-2020
 *
 */
public interface WalletRepository {

	List<?> getAll(String beanClassName);

	boolean update(Object object);
	
	Object getById(String beanClassName, int id);
	
	Object getByUserId(String beanClassName, int id);
}

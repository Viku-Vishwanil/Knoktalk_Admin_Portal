/**
 * 
 */
package com.vetologic.ktap.model.service.wallet;

import java.util.List;

/**
 * @author Faiser : 07-Sep-2020
 *
 */
public interface WalletService {

	List<?> getAll(String beanClassName);

	boolean update(Object object);
	
	Object getById(String beanClassName, int id);
	
	Object getByUserId(String beanClassName, int id);
}

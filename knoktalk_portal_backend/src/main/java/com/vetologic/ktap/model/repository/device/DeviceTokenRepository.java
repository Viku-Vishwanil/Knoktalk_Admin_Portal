/**
 * 
 */
package com.vetologic.ktap.model.repository.device;

import java.util.List;

/**
 * @author Faiser : 08-Sep-2020
 *
 */
public interface DeviceTokenRepository {

//	Object getByUserId(String beanClassName, int id);
	
	List<?> getByUserId(String beanClassName, int id);

}

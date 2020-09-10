/**
 * 
 */
package com.vetologic.ktap.model.service.device;

import java.util.List;

/**
 * @author Faiser : 08-Sep-2020
 *
 */
public interface DeviceTokenService {

//	Object getByUserId(String beanClassName, int id);
	
	List<?> getByUserId(String beanClassName, int id);

}

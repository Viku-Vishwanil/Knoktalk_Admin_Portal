/**
 * 
 */
package com.vetologic.ktap.model.service.device;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.vetologic.ktap.model.repository.device.DeviceTokenRepository;

/**
 * @author Faiser : 08-Sep-2020
 *
 */
@Service
public class DeviceTokenServiceImpl implements DeviceTokenService{

	@Autowired
	private DeviceTokenRepository deviceTokenRepository;

//	@Override
//	public Object getByUserId(String beanClassName, int id) {
//		return deviceTokenRepository.getByUserId(beanClassName, id);
//	}
	
	@Override
	public List<?> getByUserId(String beanClassName, int id) {
		return deviceTokenRepository.getByUserId(beanClassName, id);
	}
}

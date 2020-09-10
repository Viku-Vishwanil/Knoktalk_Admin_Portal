/**
 * 
 */
package com.vetologic.ktap.controller.device;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vetologic.ktap.beans.device.DeviceTokenBean;
import com.vetologic.ktap.beans.response.KtapResponse;
import com.vetologic.ktap.model.service.device.DeviceTokenService;

/**
 * @author Faiser : 08-Sep-2020
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("admin/device")
public class DeviceTokenController {

	private static Logger log = LoggerFactory.getLogger(DeviceTokenController.class);

	@Autowired
	private DeviceTokenService deviceTokenService;

//	@GetMapping(path = "/getDeviceDetailsByUserId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
//	public KtapResponse getDeviceDetailsByUserId(@PathVariable int id, KtapResponse ktapResponse) {
//		DeviceTokenBean device = (DeviceTokenBean) deviceTokenService.getByUserId("DeviceTokenBean", id);
//		if (device != null) {
//			ktapResponse.setObject(device);
//			ktapResponse.setSuccess(true);
//		} else {
//			ktapResponse.setSuccess(false);
//			ktapResponse.setMessage("device Not Exist");
//			log.info("This device Id: " + id + " Not Exist");
//		}
//		return ktapResponse;
//	}

	/**
	 * @author Faiser : 08-Sep-2020
	 *
	 *         This Api is used to get the Device details by user Id
	 *
	 * @param id
	 * @param ktapResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getDeviceDetailsByUserId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse getDeviceDetailsByUserId(@PathVariable int id, KtapResponse ktapResponse) {
		List<DeviceTokenBean> device = (List<DeviceTokenBean>) deviceTokenService.getByUserId("DeviceTokenBean", id);
		if (device.size() > 0) {
			ktapResponse.setListObject(device);
			ktapResponse.setSuccess(true);
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("device Not Exist");
			log.info("This device Id: " + id + " Not Exist");
		}
		return ktapResponse;
	}
}

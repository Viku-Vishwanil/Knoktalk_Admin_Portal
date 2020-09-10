package com.vetologic.ktap.controller.home;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vetologic.ktap.beans.response.KtapResponse;
import com.vetologic.ktap.beans.users.AdminBean;
import com.vetologic.ktap.model.service.users.AdminService;

/**
 * @author Faiser : 02-Sep-2020
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public class HomeController {

	private static Logger log = LoggerFactory.getLogger(HomeController.class);

	@Autowired
	private AdminService adminService;

	/**
	 * @author Faiser : 02-Sep-2020
	 *
	 * 
	 *
	 * @param principal
	 * @param ktapResponse
	 * @return
	 */
	@GetMapping(path = "/home", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse home(Principal principal, KtapResponse ktapResponse) {
		if (principal != null) {
			log.info("Hi, " + principal.getName() + "! Welcome, to KnokTalk Admin Portal Home Controller..");
			try {
				AdminBean admin = adminService.getUserByName(principal.getName());
				ktapResponse.setObject(admin);
				ktapResponse.setSuccess(true);
			} catch (Exception e) {
				ktapResponse.setSuccess(false);
				e.printStackTrace();
			}
		} else {
			ktapResponse.setSuccess(false);
			log.error("java.security.Principal data is empty.");
			log.error("Fails to Load KnokTalk Admin Portal Home.");
		}
		return ktapResponse;
	}
}

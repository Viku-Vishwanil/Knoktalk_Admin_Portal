package com.vetologic.ktap.controller.users;

import java.security.Principal;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vetologic.ktap.beans.response.KtapResponse;
import com.vetologic.ktap.beans.users.AdminBean;
import com.vetologic.ktap.model.service.users.AdminService;
import com.vetologic.ktap.utils.MailUtil;

/**
 * @author Faiser : 01-Sep-2020
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public class ChangePasswordController {

	private static Logger log = LoggerFactory.getLogger(ChangePasswordController.class);

	@Autowired
	private AdminService adminService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	MailUtil mailUtil;

	/**
	 * @author Faiser : 01-Sep-2020
	 *
	 *         This Api is used to change password
	 *
	 * @param ktapResponse
	 * @param principal
	 * @param currentPassword
	 * @param newPassword
	 * @return
	 */
	@PutMapping(path = "/changePassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse changePassword(KtapResponse ktapResponse, Principal principal,
			@RequestParam("currentPassword") String currentPassword, @RequestParam("newPassword") String newPassword) {
		try {
			AdminBean admin = adminService.getUserByName(principal.getName());
			if (admin != null) {
				boolean isPasswordMatches = passwordEncoder.matches(currentPassword, admin.getPassword());
				if (isPasswordMatches) {
					String hashedPassword = passwordEncoder.encode(newPassword);
					admin.setPassword(hashedPassword);
					if (adminService.update(admin)) {
						try {
							mailUtil.sendChangePasswordEmail(admin, newPassword);
							log.info("Password Changed Successfully & Sent Email Successfully");
						} catch (Exception e) {
							log.info("Password Changed Successfully & fails to send Email");
							log.error(e.getMessage());
						}
						ktapResponse.setSuccess(true);
						ktapResponse.setMessage("Password Changed Successfully");
					} else {
						ktapResponse.setSuccess(false);
						log.info("Fails to Change Password");
						ktapResponse.setMessage("Fails to Change Password");
					}
				} else {
					ktapResponse.setSuccess(false);
					log.info("Incorrect Current Password");
					ktapResponse.setMessage("Incorrect Current Password");
				}
			} else {
				ktapResponse.setSuccess(false);
				log.info("Something Went Wrong");
				ktapResponse.setMessage("Something Went Wrong! Try again.");
			}
		} catch (Exception e) {
			ktapResponse.setSuccess(false);
			log.info("Something Went Wrong");
			ktapResponse.setMessage("Something Went Wrong! Try again.");
			log.error(e.getMessage());
		}
		return ktapResponse;
	}
}

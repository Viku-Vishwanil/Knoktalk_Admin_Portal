package com.vetologic.ktap.controller.users;

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
import com.vetologic.ktap.utils.PasswordUtil;

/**
 * @author Faiser : 01-Sep-2020
 *
 */
@RestController
@CrossOrigin(origins = "*")
public class ForgetPasswordController {
	private static Logger log = LoggerFactory.getLogger(ForgetPasswordController.class);

	@Autowired
	private AdminService adminService;

	@Autowired
	private PasswordEncoder passwordEncoder;

	@Autowired
	MailUtil mailUtil;

	/**
	 * @author Faiser : 01-Sep-2020
	 *
	 *         This Api is used to Forgot password
	 *
	 * @param ktapResponse
	 * @param forgotPasswordEmailId
	 * @return
	 */
	@PutMapping(path = "/forgotPassword", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse forgotPassword(KtapResponse ktapResponse,
			@RequestParam("forgotPasswordEmailId") String forgotPasswordEmailId) {
		try {
			AdminBean admin = adminService.getUserDetailsByEmailId(forgotPasswordEmailId.toLowerCase());
			if (admin != null) {
				String generatedPassword = new PasswordUtil().generatePassword();
				String hashedPassword = passwordEncoder.encode(generatedPassword);
				admin.setPassword(hashedPassword);
				if (adminService.update(admin)) {
					admin.setPassword(generatedPassword);
					try {
						mailUtil.sendForgotPasswordEmail(admin);
					} catch (Exception e) {
						ktapResponse.setSuccess(false);
						ktapResponse.setMessage("Password Reset Successfully! But Fails to send mail.");
						log.info("Password Reset Successfully! But Fails to send mail.");
						log.error(e.getMessage());
						return ktapResponse;
					}
					ktapResponse.setSuccess(true);
					log.info("Password Reset Successfully and Mail Sent to your EmailId");
					ktapResponse.setMessage("Password Reset Successfully and Mail Sent to your EmailId");
				} else {
					ktapResponse.setSuccess(false);
					log.info("Fails to Reset Password");
					ktapResponse.setMessage("Fails to Reset Password");
				}
			} else {
				ktapResponse.setSuccess(false);
				log.info("This EmailId Not Exist");
				ktapResponse.setMessage("This EmailId Not Exist");
			}
		} catch (Exception e) {
			ktapResponse.setSuccess(false);
			log.info("Something Went Wrong");
			ktapResponse.setMessage("Something Went Wrong");
			log.error(e.getMessage());
		}
		return ktapResponse;
	}
}

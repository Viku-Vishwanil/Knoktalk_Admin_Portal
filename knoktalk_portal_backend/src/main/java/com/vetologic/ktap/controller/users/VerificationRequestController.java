package com.vetologic.ktap.controller.users;

import java.io.File;
import java.io.FileInputStream;
import java.nio.file.FileSystems;
import java.nio.file.Path;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.vetologic.ktap.beans.response.KtapResponse;
import com.vetologic.ktap.beans.users.VerificationRequestBean;
import com.vetologic.ktap.model.service.users.VerificationRequestService;

/**
 * @author Faiser : 04-Sep-2020
 *
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("admin/verificationrequest")
public class VerificationRequestController {

	private static Logger log = LoggerFactory.getLogger(VerificationRequestController.class);

	@Autowired
	private VerificationRequestService verificationRequestService;

	/**
	 * @author Faiser : 04-Sep-2020
	 *
	 *         This Api(getAllVerificationDetails) is used to get all verification
	 *         requests
	 *
	 * @param ktapResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllVerificationDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse getAllVerificationDetails(KtapResponse ktapResponse) {
		List<VerificationRequestBean> allVerificationDetails = (List<VerificationRequestBean>) verificationRequestService
				.getAll("VerificationRequestBean");
		if (allVerificationDetails.size() > 0) {
			ktapResponse.setListObject(allVerificationDetails);
			ktapResponse.setSuccess(true);
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("verification Request is Empty");
			log.info("verification Request is Empty");
		}
		return ktapResponse;
	}

	/**
	 * @author Faiser : 04-Sep-2020
	 *
	 *         This Api(getVerificationAndUserDetails) is used to get all
	 *         verification and used details(OnHold)
	 *
	 * @param ktapResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getVerificationAndUserDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse getVerificationAndUserDetails(KtapResponse ktapResponse) {
		List<VerificationRequestBean> allVerificationDetails = (List<VerificationRequestBean>) verificationRequestService
				.getVerificationAndUserList();
		System.err.println(allVerificationDetails.toString());
		if (allVerificationDetails.size() > 0) {
			ktapResponse.setListObject(allVerificationDetails);
			ktapResponse.setSuccess(true);
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("verification Request is Empty");
			log.info("verification Request is Empty");
		}
		return ktapResponse;
	}

	/**
	 * @author Faiser : 09-Sep-2020
	 *
	 *
	 *
	 * @param fileName
	 * @return
	 */
	public byte[] getFileData(String fileName) {
		byte[] byteArray = null;
		try {
			Path rootPath = FileSystems.getDefault().getPath("").toAbsolutePath();
			String filePath = rootPath + File.separator + "Uploads" + File.separator + "users" + File.separator
					+ File.separator + "attachment" + File.separator + fileName;

			File file = new File(filePath);
			byteArray = new byte[(int) file.length()];
			FileInputStream fis = null;

			fis = new FileInputStream(file);
			fis.read(byteArray);
			fis.close();

		} catch (Exception e) {
			log.error(e.getMessage());
		}
		return byteArray;
	}
}

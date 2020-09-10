package com.vetologic.ktap.controller.users;

import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vetologic.ktap.beans.response.KtapResponse;
import com.vetologic.ktap.beans.users.UsersBean;
import com.vetologic.ktap.model.service.users.UsersService;
import com.vetologic.ktap.utils.AppUtil;

/**
 * @author Faiser : 02-Sep-2020
 *
 * 
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("admin/users")
public class UsersController {

	private static Logger log = LoggerFactory.getLogger(UsersController.class);

	@Autowired
	private UsersService usersService;

	/**
	 * @author Faiser : 02-Sep-2020
	 * 
	 *         This API is used to get all user list
	 * 
	 * @param ktapResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllUsersDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse getAllUsersDetails(KtapResponse ktapResponse) {
		List<UsersBean> allUsersDetails = (List<UsersBean>) usersService.getAll("UsersBean");
		if (allUsersDetails.size() > 0) {
			ktapResponse.setListObject(allUsersDetails);
			ktapResponse.setSuccess(true);
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("User List is Empty");
			log.info("User List is Empty");
		}
		return ktapResponse;
	}

	/**
	 * @author Faiser : 02-Sep-2020
	 *
	 *         This API is used to block the user i.e., by setting Block as 1
	 *
	 * @param userId
	 * @param ktapResponse
	 * @return
	 */
	@PutMapping(path = "/blockUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse blockUser(@RequestParam("userId") int userId, KtapResponse ktapResponse) {
		UsersBean user = (UsersBean) usersService.getById("UsersBean", userId);
		if (user != null) {
			user.setUpdatedDate(AppUtil.currentDateWithTime());
			user.setBlock(1);
			if (usersService.update(user)) {
				ktapResponse.setSuccess(true);
				ktapResponse.setMessage("Blocked Sucessfully");
				log.info("This User Id: " + userId + " Blocked Sucessfully");
			} else {
				ktapResponse.setSuccess(false);
				ktapResponse.setMessage("Block Failed");
				log.info("Block Failed");
			}

		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("This User Not Exist");
			log.info("This User Id: " + userId + " Not Exist");
		}
		return ktapResponse;
	}

	/**
	 * @author Faiser : 02-Sep-2020
	 *
	 *         This API is used to Unblock the user i.e., by setting Block as 0
	 *
	 * @param userId
	 * @param ktapResponse
	 * @return
	 */
	@PutMapping(path = "/unBlockUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse unBlockUser(@RequestParam("userId") int userId, KtapResponse ktapResponse) {
		UsersBean user = (UsersBean) usersService.getById("UsersBean", userId);
		if (user != null) {
			user.setUpdatedDate(AppUtil.currentDateWithTime());
			user.setBlock(0);
			if (usersService.update(user)) {
				ktapResponse.setSuccess(true);
				ktapResponse.setMessage("UnBlocked Sucessfully");
				log.info("This User Id: " + userId + " UnBlocked Sucessfully");
			} else {
				ktapResponse.setSuccess(false);
				ktapResponse.setMessage("UnBlock Failed");
				log.info("UnBlock Failed");
			}

		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("This User Not Exist");
			log.info("This User Id: " + userId + " Not Exist");
		}
		return ktapResponse;
	}

	/**
	 * @author Faiser : 02-Sep-2020
	 *
	 *         This API is used to Delete the user i.e., by setting DeletionFlag as
	 *         1
	 *
	 * @param userId
	 * @param ktapResponse
	 * @return
	 */
	@PutMapping(path = "/deleteUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse deleteUser(@RequestParam("userId") int userId, KtapResponse ktapResponse) {
		UsersBean user = (UsersBean) usersService.getById("UsersBean", userId);
		if (user != null) {
			user.setUpdatedDate(AppUtil.currentDateWithTime());
			user.setDeletionFlag(1);
			if (usersService.update(user)) {
				ktapResponse.setSuccess(true);
				ktapResponse.setMessage("Deleted Sucessfully");
				log.info("This User Id: " + userId + " Deleted Sucessfully");
			} else {
				ktapResponse.setSuccess(false);
				ktapResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed");
			}

		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("This User Not Exist");
			log.info("This User Id: " + userId + " Not Exist");
		}
		return ktapResponse;
	}

	/**
	 * @author Faiser : 02-Sep-2020
	 *
	 *         This API is used to Approve the user i.e., by setting Verified as 1
	 *
	 * @param userId
	 * @param ktapResponse
	 * @return
	 */
	@PutMapping(path = "/approveUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse approveUser(@RequestParam("userId") int userId, KtapResponse ktapResponse) {
		UsersBean user = (UsersBean) usersService.getById("UsersBean", userId);
		if (user != null) {
			user.setUpdatedDate(AppUtil.currentDateWithTime());
			user.setVerified(1);
			if (usersService.update(user)) {
				ktapResponse.setSuccess(true);
				ktapResponse.setMessage("Approved Sucessfully");
				log.info("This User Id: " + userId + " Approved Sucessfully");
			} else {
				ktapResponse.setSuccess(false);
				ktapResponse.setMessage("Approve Failed");
				log.info("Approve Failed");
			}

		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("This User Not Exist");
			log.info("This User Id: " + userId + " Not Exist");
		}
		return ktapResponse;
	}

	/**
	 * @author Faiser : 02-Sep-2020
	 *
	 *         This API is used to Decline the user i.e., by setting Verified as 0
	 *
	 * @param userId
	 * @param ktapResponse
	 * @return
	 */
	@PutMapping(path = "/declineUser", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse declineUser(@RequestParam("userId") int userId, KtapResponse ktapResponse) {
		UsersBean user = (UsersBean) usersService.getById("UsersBean", userId);
		if (user != null) {
			user.setUpdatedDate(AppUtil.currentDateWithTime());
			user.setVerified(0);
			if (usersService.update(user)) {
				ktapResponse.setSuccess(true);
				ktapResponse.setMessage("Declined Sucessfully");
				log.info("This User Id: " + userId + " Declined Sucessfully");
			} else {
				ktapResponse.setSuccess(false);
				ktapResponse.setMessage("Decline Failed");
				log.info("Decline Failed");
			}

		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("This User Not Exist");
			log.info("This User Id: " + userId + " Not Exist");
		}
		return ktapResponse;
	}
}

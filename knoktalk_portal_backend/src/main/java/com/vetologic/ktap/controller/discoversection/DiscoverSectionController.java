package com.vetologic.ktap.controller.discoversection;
import java.security.Principal;
import java.util.List;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;
import com.vetologic.ktap.beans.discoversection.DiscoverSectionBean;
import com.vetologic.ktap.beans.response.KtapResponse;
import com.vetologic.ktap.model.service.discoversection.DiscoverSectionService;
import com.vetologic.ktap.utils.AppUtil;

/**
 * @author Rambabu : 02-Sep-2020
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("discover")
public class DiscoverSectionController {

	private static Logger log = LoggerFactory.getLogger(DiscoverSectionBean.class);

	@Autowired
	private DiscoverSectionService discoverSectionService;

	/**
	 * @author Rambabu : 03-Sep-2020
	 *
	 * This API Is Used To Save The Discover Section Details
	 *
	 * @param discover
	 * @param ktapResponse
	 * @param principal
	 * @return
	 */
	@PostMapping(path = "/saveDiscoverSectioDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse saveDiscoverSectioDetails(@RequestBody DiscoverSectionBean discover, KtapResponse ktapResponse,
			Principal principal) {
		discover.setDeletionFlag(0);
		discover.setCreatedDate(AppUtil.currentDateWithTime());
		int id = discoverSectionService.save(discover);
		if (id != 0) {
			ktapResponse.setSuccess(true);
			ktapResponse.setMessage("Saved Sucessfully");
			log.info("Saved Sucessfully & Saved Id is: " + id);
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("Saved UnSucessfully");
			log.info("Saved UnSucessfully");
		}
		return ktapResponse;
	}

	/**
	 * @author Rambabu : 03-Sep-2020
	 *
	 * @param ktapResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllDiscoverSectionDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse getAllDiscoverSectionDetails(KtapResponse ktapResponse) {
		List<DiscoverSectionBean> allDiscoverSectionDetails = (List<DiscoverSectionBean>) discoverSectionService
				.getAll("DiscoverSectionBean");
		if (allDiscoverSectionDetails.size() > 0) {
			ktapResponse.setListObject(allDiscoverSectionDetails);
			ktapResponse.setSuccess(true);
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("Jobs List is Empty");
			log.info("Jobs List is Empty");
		}
		return ktapResponse;
	}

	/**
	 * @author Rambabu : 03-Sep-2020
	 *
	 * @param id
	 * @param ktapResponse
	 * @return
	 */
	@GetMapping(path = "/getDiscoverSectionDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse getDiscoverSectionDetailsById(@PathVariable int id, KtapResponse ktapResponse) {
		DiscoverSectionBean discover = (DiscoverSectionBean) discoverSectionService.getById("DiscoverSectionBean", id);
		if (discover != null) {
			ktapResponse.setObject(discover);
			ktapResponse.setSuccess(true);
		} else {
			ktapResponse.setSuccess(false);
			log.info("This Discover Section id: " + id + " Not Exist");
			ktapResponse.setMessage("Discover Section Not Exist");
		}
		return ktapResponse;
	}

	/**
	 * @author Rambabu : 03-Sep-2020
	 *
	 * @param discover
	 * @param ktapResponse
	 * @param principal
	 * @return
	 */
	@PutMapping(path = "/updateDiscoverSectionDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse updateDiscoverSectionDetails(@RequestBody DiscoverSectionBean discover,
			KtapResponse ktapResponse, Principal principal) {
		DiscoverSectionBean discoverSection = (DiscoverSectionBean) discoverSectionService
				.getById("DiscoverSectionBean", discover.getDiscoverSectionId());

		if (discoverSection != null) {
			discoverSection.setDeletionFlag(0);
			discoverSection.setUpdatedDate(AppUtil.currentDateWithTime());
			discoverSection.setSectionName(discover.getSectionName());
			if (discoverSectionService.update(discoverSection)) {

				ktapResponse.setSuccess(true);
				ktapResponse.setMessage("Updated Successfully");
				log.info("This Discover Section Id: " + discover.getDiscoverSectionId() + " Updated Sucessfully");
			} else {
				ktapResponse.setSuccess(false);
				ktapResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("This Discover Section Not Exist");
			log.info("This Discover Section Id: " + discover.getDiscoverSectionId() + " Not Exist");
		}

		return ktapResponse;
	}

	/**
	 * @author Rambabu : 03-Sep-2020
	 *
	 * @param id
	 * @param ktapResponse
	 * @param principal
	 * @return
	 */
	@PutMapping(path = "/deleteDiscoverSectionDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse deleteDiscoverSectionDetails(@RequestParam("id") int id, KtapResponse ktapResponse,
			Principal principal) {

		DiscoverSectionBean discoverSection = (DiscoverSectionBean) discoverSectionService
				.getById("DiscoverSectionBean", id);
		if (discoverSection != null) {
			discoverSection.setDeletionFlag(1);
			discoverSection.setUpdatedDate(AppUtil.currentDateWithTime());
			if (discoverSectionService.update(discoverSection)) {
				ktapResponse.setSuccess(true);
				ktapResponse.setMessage("Deleted Successfully");
				log.info("This DiscoverSection Id: " + id + " Deleted Successfully");
			} else {
				ktapResponse.setSuccess(false);
				ktapResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed");
			}

		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("This DiscoverSection Not Exist");
			log.info("This DiscoverSection Id: " + id + " Not Exist");
		}
		return ktapResponse;

	}

}

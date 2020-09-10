package com.vetologic.ktap.controller.sounds;

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

import com.vetologic.ktap.beans.response.KtapResponse;
import com.vetologic.ktap.beans.sounds.SectionBean;
import com.vetologic.ktap.model.service.sounds.SectionService;
import com.vetologic.ktap.utils.AppUtil;

/**
 * @author VIKASH : 3 Sep, 2020
 *
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("sound")
public class SectionController {

	private static Logger log = LoggerFactory.getLogger(SectionBean.class);

	@Autowired
	private SectionService sectionService;

	/**
	 * @author VIKASH : 3 Sep, 2020
	 *
	 * This API is Used to Save the Section Details.
	 *
	 * @param section
	 * @param ktapResponse
	 * @return
	 */
	@PostMapping(path = "/saveSectioDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse saveDiscoverSectioDetails(@RequestBody SectionBean section, KtapResponse ktapResponse) {
		section.setDeletionFlag(0);
		section.setCreatedDate(AppUtil.currentDateWithTime());
		int id = sectionService.save(section);
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
	 * @author VIKASH : 3 Sep, 2020
	 *
	 * This API is Used to Get the Existing All Section Details
	 *
	 * @param ktapResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllSectionDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse getAllSectionDetails(KtapResponse ktapResponse) {
		List<SectionBean> allSectionDetails = (List<SectionBean>) sectionService
				.getAll("SectionBean");
		if (allSectionDetails.size() > 0) {
			ktapResponse.setListObject(allSectionDetails);
			ktapResponse.setSuccess(true);
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("Section List is Empty");
			log.info("Section List is Empty");
		}
		return ktapResponse;
	}

	/**
	 * @author VIKASH : 3 Sep, 2020
	 * 
	 * This API is Used to Get the Existing Section Details By Section Id
	 * 
	 * @param id
	 * @param ktapResponse
	 * @return
	 */
	@GetMapping(path = "/getSectionDetails/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse getSectionDetailsById(@PathVariable int id, KtapResponse ktapResponse) {
		SectionBean section = (SectionBean) sectionService.getById("SectionBean", id);
		if (section != null) {
			ktapResponse.setObject(section);
			ktapResponse.setSuccess(true);
		} else {
			ktapResponse.setSuccess(false);
			log.info("This Section id: " + id + " Not Exist");
			ktapResponse.setMessage("Section Not Exist");
		}
		return ktapResponse;
	}

	/**
	 * @author VIKASH : 3 Sep, 2020
	 *
	 * This API is Used to Update the Existing Section Details By Section Id
	 *
	 * @param section
	 * @param ktapResponse
	 * @return
	 */
	@PutMapping(path = "/updateSectionDetails", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse updateSectionDetails(@RequestBody SectionBean section,
			KtapResponse ktapResponse) {
		SectionBean soundSection = (SectionBean) sectionService
				.getById("SectionBean", section.getSectionId());

		if (soundSection != null) {
			soundSection.setDeletionFlag(0);
			soundSection.setUpdatedDate(AppUtil.currentDateWithTime());
			soundSection.setSectionName(section.getSectionName());
			if (sectionService.update(soundSection)) {

				ktapResponse.setSuccess(true);
				ktapResponse.setMessage("Updated Successfully");
				log.info("This Section Id: " + section.getSectionId() + " Updated Sucessfully");
			} else {
				ktapResponse.setSuccess(false);
				ktapResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("This Section Not Exist");
			log.info("This Section Id: " + section.getSectionId() + " Not Exist");
		}

		return ktapResponse;
	}

	/**
	 * @author VIKASH : 3 Sep, 2020
	 *
	 * This API is Used to Delete the Existing Section Details By Section Id
	 *
	 * @param sectionId
	 * @param ktapResponse
	 * @return
	 */
	@PutMapping(path = "/deleteSectionDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse deleteSectionDetails(@RequestParam("sectionId") int sectionId, KtapResponse ktapResponse) {

		SectionBean section = (SectionBean) sectionService
				.getById("SectionBean", sectionId);
		if (section != null) {
			section.setDeletionFlag(1);
			section.setUpdatedDate(AppUtil.currentDateWithTime());
			if (sectionService.update(section)) {
				ktapResponse.setSuccess(true);
				ktapResponse.setMessage("Deleted Successfully");
				log.info("This Section Id: " + sectionId + " Deleted Successfully");
			} else {
				ktapResponse.setSuccess(false);
				ktapResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed");
			}

		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("This Section Not Exist");
			log.info("This Section Id: " + sectionId + " Not Exist");
		}
		return ktapResponse;

	}
}

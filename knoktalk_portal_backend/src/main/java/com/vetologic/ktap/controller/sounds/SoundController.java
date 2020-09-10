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
import org.springframework.web.multipart.MultipartFile;

import com.vetologic.ktap.beans.response.KtapResponse;
import com.vetologic.ktap.beans.sounds.SoundBean;
import com.vetologic.ktap.model.service.sounds.SoundService;
import com.vetologic.ktap.utils.AppUtil;
import com.vetologic.ktap.utils.FileUploader;

/**
 * @author VIKASH : 7 Sep, 2020
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("sound")
public class SoundController {
	
	private static Logger log = LoggerFactory.getLogger(SoundController.class);
	
	@Autowired
	private SoundService soundService;
	
	/**
	 * @author VIKASH : 8 Sep, 2020
	 *
	 * This API is Used to Get the Existing All Sound Details
	 *
	 * @param ktapResponse
	 * @return
	 */
	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllSoundDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse getAllSoundDetails(KtapResponse ktapResponse) {
		List<SoundBean> allSoundDetails = (List<SoundBean>) soundService
				.getAll("SoundBean");
		if (allSoundDetails.size() > 0) {
			ktapResponse.setListObject(allSoundDetails);
			ktapResponse.setSuccess(true);
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("Sound List is Empty");
			log.info("Sound List is Empty");
		}
		return ktapResponse;
	}
	
	/**
	 * @author VIKASH : 8 Sep, 2020
	 *
	 * This API is Used to Upload the Sound into DB
	 * 
	 * @param ktapResponse
	 * @param soundFile
	 * @param fileUploader
	 * @return
	 */
	@PostMapping(path = "/soundUpload", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse soundUpload(KtapResponse ktapResponse, MultipartFile soundFile,
			FileUploader fileUploader) {
		
		SoundBean soundBean = new SoundBean();
		try {
			if (soundFile != null) {
				if (!soundFile.getOriginalFilename()
						.equalsIgnoreCase(soundBean.getSound())) {
					if (fileUploader.uploadSound(soundFile, "Sound_File")) {
						String soundFileName = soundFile.getOriginalFilename();
						soundBean.setDeletionFlag(0);
						soundBean.setCreatedDate(AppUtil.currentDateWithTime());
						soundBean.setSound(soundFileName);
						System.err.println(soundFileName);
						soundService.save(soundBean);
						log.info("Sound: " + soundFileName
								+ " is Uploaded Successfully.");
						ktapResponse.setSuccess(true);
					} else {
						log.info("Fails to Upload Sound");
						ktapResponse.setSuccess(false);
					}
				} else {
					ktapResponse.setSuccess(true);
					ktapResponse.setMessage("Already Uploaded");
					log.info("This Sound Already Uploaded");
				}
			} else {
				ktapResponse.setSuccess(true);
				log.info("Sound File is Empty.");
			}
		} catch (Exception e) {
			ktapResponse.setSuccess(false);
			e.printStackTrace();
		}
		return ktapResponse;
	}
	
	
	/**
	 * @author VIKASH : 8 Sep, 2020
	 *
	 * This API is Used to Publish the Existing Sound Details By Sound Id
	 *
	 * @param sound
	 * @param ktapResponse
	 * @param soundId
	 * @return
	 */
	@PutMapping(path = "/publishSound/{soundId}",consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse publishSound(@RequestBody SoundBean sound, KtapResponse ktapResponse, @PathVariable(value="soundId") int soundId
			) {
		SoundBean soundDetails = (SoundBean) soundService.getById("SoundBean", soundId);
		soundDetails.setUpdatedDate(AppUtil.currentDateWithTime());
		soundDetails.setSoundName(sound.getSoundName());
		soundDetails.setDescription(sound.getDescription());
		soundDetails.setSectionBean(sound.getSectionBean());
		if (soundService.update(soundDetails)) {
			ktapResponse.setSuccess(true);
			ktapResponse.setMessage("Saved Sucessfully");
			//log.info("Saved Sucessfully & Saved Id is: " + id);
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("Saved UnSucessfully");
			log.info("Saved UnSucessfully");
		}
		return ktapResponse;
	}
	
	/**
	 * @author VIKASH : 8 Sep, 2020
	 *
	 * This API is Used to Save the thumbline into Sound Table
	 *
	 * @param ktapResponse
	 * @param soundId
	 * @param thumFile
	 * @param fileUploader
	 * @return
	 */
	@PutMapping(path = "/savethum", consumes = MediaType.MULTIPART_FORM_DATA_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse savethum(KtapResponse ktapResponse,
			int soundId, MultipartFile thumFile,
			FileUploader fileUploader) {
		try {
			SoundBean soundDetails = (SoundBean) soundService.getById("SoundBean",soundId);
			int soundNo = soundDetails.getSoundId();
			if (thumFile != null) {
				if (!thumFile.getOriginalFilename().equalsIgnoreCase(soundDetails.getThum())) {
					if (fileUploader.uploadThum(thumFile,
							soundNo, "Thumblines")) {
						String thumFileName = soundNo + "_"+ thumFile.getOriginalFilename();
						soundDetails.setThum(thumFileName);
						System.err.println(thumFileName);
						soundService.update(soundDetails);
						log.info("Thum Photo: " + thumFileName
								+ " is Uploaded Successfully.");
						ktapResponse.setSuccess(true);
					} else {
						log.info("Fails to Upload Thum Picture");
						ktapResponse.setSuccess(false);
					}
				} else {
					ktapResponse.setSuccess(true);
					ktapResponse.setMessage("Already Uploaded");
					log.info("This Thum Picture Already Uploaded");
				}
			} else {
				ktapResponse.setSuccess(true);
				log.info("Thum Picture File is Empty.");
			}
		} catch (Exception e) {
			ktapResponse.setSuccess(false);
			e.printStackTrace();
		}
		return ktapResponse;
	}
	
	/**
	 * @author VIKASH : 8 Sep, 2020
	 *
	 * This API is Used to Delete the Existing Sound Details By Sound Id
	 *
	 * @param soundId
	 * @param ktapResponse
	 * @return
	 */
	@PutMapping(path = "/deleteSound", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse deleteSound(@RequestParam("soundId") int soundId, KtapResponse ktapResponse) {

		SoundBean sound = (SoundBean) soundService
				.getById("SoundBean", soundId);
		if (sound != null) {
			sound.setDeletionFlag(1);
			sound.setUpdatedDate(AppUtil.currentDateWithTime());
			if (soundService.update(sound)) {
				ktapResponse.setSuccess(true);
				ktapResponse.setMessage("Deleted Successfully");
				log.info("This Sound Id: " + soundId + " Deleted Successfully");
			} else {
				ktapResponse.setSuccess(false);
				ktapResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed");
			}

		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("This Sound Not Exist");
			log.info("This Sound Id: " + soundId + " Not Exist");
		}
		return ktapResponse;

	}

}

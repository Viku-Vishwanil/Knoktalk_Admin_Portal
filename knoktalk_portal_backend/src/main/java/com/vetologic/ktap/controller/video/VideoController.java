/**
 * 
 */
package com.vetologic.ktap.controller.video;

import java.security.Principal;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.MediaType;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.vetologic.ktap.beans.discoversection.DiscoverSectionBean;
import com.vetologic.ktap.beans.response.KtapResponse;
import com.vetologic.ktap.beans.videos.VideosBean;
import com.vetologic.ktap.model.service.video.VideoService;
import com.vetologic.ktap.utils.AppUtil;

/**
 * @author Rambabu : 04-Sep-2020
 *
 */

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("videos")
public class VideoController {

	private static Logger log = LoggerFactory.getLogger(VideosBean.class);

	@Autowired
	private VideoService videoService;

	@SuppressWarnings("unchecked")
	@GetMapping(path = "/getAllVideosDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse getAllVideosDetails(KtapResponse ktapResponse) {
		List<VideosBean> allVideosDetails = (List<VideosBean>) videoService.getAll("VideosBean");
		if (allVideosDetails.size() > 0) {
			ktapResponse.setListObject(allVideosDetails);
			ktapResponse.setSuccess(true);
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("Videos List is Empty");
			log.info("Videos List is Empty");
		}
		return ktapResponse;
	}

	@PutMapping(path = "/deleteVideoDetails", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse deleteVideoDetails(@RequestParam("videoId") int videoId, KtapResponse ktapResponse, Principal principal) {
		VideosBean video = (VideosBean) videoService.getById("VideosBean", videoId);
		if (video != null) {
			video.setDeletionFlag(1);
			video.setUpdatedDate(AppUtil.currentDateWithTime());
			if (videoService.update(video)) {
				ktapResponse.setSuccess(true);
				ktapResponse.setMessage("Deleted Successfully");
				log.info("This Video Id: " + videoId + " Deleted Successfully");
			} else {
				ktapResponse.setSuccess(false);
				ktapResponse.setMessage("Deletion Failed");
				log.info("Deletion Failed");
			}

		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("This Video Not Exist");
			log.info("This Video Id: " + videoId + " Not Exist");
		}
		return ktapResponse;

	}
	
		@GetMapping(path = "/getVideoDetails/{videoId}", produces = MediaType.APPLICATION_JSON_VALUE)
		public KtapResponse getDiscoverSectionDetailsById(@PathVariable int videoId, KtapResponse ktapResponse) {
			VideosBean video = (VideosBean) videoService.getById("DiscoverSectionBean", videoId);
			if (video != null) {
				ktapResponse.setObject(video);
				ktapResponse.setSuccess(true);
			} else {
				ktapResponse.setSuccess(false);
				log.info("This Video id: " + videoId + " Not Exist");
				ktapResponse.setMessage("Video Not Exist");
			}
			return ktapResponse;
		}

	@PutMapping(path = "/updateVideoDetails/{videoId}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse updateDiscoverSectionDetails(@RequestBody DiscoverSectionBean discover,@PathVariable(value = "videoId") int videoId ,
			KtapResponse ktapResponse, Principal principal) {
		
		VideosBean videoDetails = (VideosBean) videoService
				.getById("VideosBean", videoId);

		if (videoDetails != null) {
//			videoDetails.setDeletionFlag(0);
//			videoDetails.setUpdatedDate(AppUtil.currentDateWithTime());
//			videoDetails.setUserBean(video.getUserBean());
//			videoDetails.setDescription(video.getDescription());
//			videoDetails.setVideo(video.getVideo());
//			videoDetails.setThum(video.getThum());
//			videoDetails.setGif(video.getGif());
//			videoDetails.setView(video.getView());
			videoDetails.setDiscoverSectionBean(discover);
//			videoDetails.setSoundBean(video.getSoundBean());
//			videoDetails.setPrivacyType(video.getPrivacyType());
//			videoDetails.setAllowComments(video.getAllowComments());
//			videoDetails.setCreatedDate(video.getCreatedDate());
//			videoDetails.setUpdatedDate(AppUtil.currentDateWithTime());
//			videoDetails.setDeletionFlag(video.getDeletionFlag());
			if (videoService.update(videoDetails)) {

				ktapResponse.setSuccess(true);
				ktapResponse.setMessage("Updated Successfully");
				log.info("This Video Updated Id: " + videoId + " Updated Sucessfully");
			} else {
				ktapResponse.setSuccess(false);
				ktapResponse.setMessage("Fails to Update");
				log.info("Fails to Update");
			}
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("This Video Section Not Exist");
			log.info("This Video Section Id: " +  videoId + " Not Exist");
		}

		return ktapResponse;
	}
	
}

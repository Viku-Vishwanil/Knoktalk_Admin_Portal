package com.vetologic.ktap.beans.videos;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vetologic.ktap.beans.discoversection.DiscoverSectionBean;
import com.vetologic.ktap.beans.sounds.SoundBean;
import com.vetologic.ktap.beans.users.UsersBean;

import lombok.Data;

@Entity
@Data
@Table(name = "videos")
public class VideosBean {
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "video_id")
	private int videoId;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UsersBean userBean;

	@Column(name = "description")
	private String description;

	@Column(name = "video")
	private String video;

	@Column(name = "thum")
	private String thum;

	@Column(name = "gif")
	private String gif;

	@Column(name = "view")
	private String view;

	@ManyToOne
	@JoinColumn(name = "discover_section_id")
	private DiscoverSectionBean discoverSectionBean;

	@ManyToOne
	@JoinColumn(name = "sound_id")
	private SoundBean soundBean;
	
	
	@Column(name = "privacy_type")
	private String privacyType;

	@Column(name = "allow_comments")
	private String allowComments;

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "updated_date")
	private String updatedDate;

	@Column(name = "deletion_flag")
	private int deletionFlag;
}

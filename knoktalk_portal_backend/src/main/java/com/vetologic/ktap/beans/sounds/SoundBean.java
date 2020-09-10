/**
 * 
 */
package com.vetologic.ktap.beans.sounds;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;


import lombok.Data;


/**
 * @author VIKASH : 7 Sep, 2020
 *
 */
@Entity
@Data
@Table(name = "sounds")
public class SoundBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sound_id")
	private int soundId;

	
	@ManyToOne
	@JoinColumn(name = "sound_section_id")
	private SectionBean sectionBean;
	
	@Column(name = "sound_name")
	private String soundName;

	@Column(name = "description")
	private String description;
	
	@Column(name = "sound")
	private String sound;
	
	@Column(name = "thum")
	private String thum;
	
	@Column(name = "created_date")
	private String createdDate;
	
	@Column(name = "updated_date")
	private String updatedDate;
	
	@Column(name = "deletion_flag")
	private int deletionFlag;

}

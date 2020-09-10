/**
 * 
 */
package com.vetologic.ktap.beans.sounds;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;


import lombok.Data;

/**
 * @author VIKASH : 3 Sep, 2020
 *
 */
@Entity
@Data
@Table(name = "sound_section")
public class SectionBean {
	
	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "sound_section_id")
	private int sectionId;
	
	@Column(name = "section_name")
	private String sectionName;

	@Column(name = "deletion_flag")
	private int deletionFlag;
	
	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "updated_date")
	private String updatedDate;

}

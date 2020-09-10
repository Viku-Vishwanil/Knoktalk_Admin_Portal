package com.vetologic.ktap.beans.discoversection;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import lombok.Data;

@Entity
@Data
@Table(name = "discover_section")
public class DiscoverSectionBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "discover_section_id")
	private int discoverSectionId;

	@Column(name = "section_name")
	private String sectionName;

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "updated_date")
	private String updatedDate;

	@Column(name = "deletion_flag")
	private int deletionFlag;
}

package com.vetologic.ktap.beans.users;

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
 * @author Faiser : 07-Sep-2020
 *
 */
@Entity
@Data
@Table(name = "verification_request")
public class VerificationRequestBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;
	
	@ManyToOne
	@JoinColumn(name = "user_id")
	private UsersBean user;

	@Column(name = "attachment ")
	private String attachment;

	@Column(name = "created_date")
	private String createdDate;
}

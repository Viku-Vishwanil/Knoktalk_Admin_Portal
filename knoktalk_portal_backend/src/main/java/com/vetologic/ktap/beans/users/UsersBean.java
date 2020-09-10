package com.vetologic.ktap.beans.users;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.Table;
import javax.persistence.UniqueConstraint;

import lombok.Data;

/**
 * @author Faiser : 02-Sep-2020
 *
 */
@Entity
@Data
@Table(name = "users", uniqueConstraints = @UniqueConstraint(columnNames = { "knoktalk_id", "email_id" }))
public class UsersBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "user_id")
	private int userId;

	@Column(name = "active")
	private int active;

	@Column(name = "block")
	private int block;

	@Column(name = "deletion_flag")
	private int deletionFlag;

	@Column(name = "verified")
	private int verified;

	@Column(name = "first_name")
	private String firstName;

	@Column(name = "last_name")
	private String lastName;

	@Column(name = "knoktalk_id")
	private String knoktalkId;

	@Column(name = "email_id")
	private String emailId;

	@Column(name = "password")
	private String password;

	@Column(name = "gender")
	private String gender;

	@Column(name = "profile_photo")
	private String profilePhoto;

	@Column(name = "signup_email_type")
	private String signupEmailType;

	@Column(name = "signup_type")
	private String signupType;

	@Column(name = "nationality")
	private String nationality;

	@Column(name = "bearer_token")
	private String bearerToken;

	@Column(name = "current_device_token")
	private String currentDeviceToken;

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "updated_date")
	private String updatedDate;

}

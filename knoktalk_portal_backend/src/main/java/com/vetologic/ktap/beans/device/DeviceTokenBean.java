/**
 * 
 */
package com.vetologic.ktap.beans.device;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.JoinColumn;
import javax.persistence.ManyToOne;
import javax.persistence.Table;

import com.vetologic.ktap.beans.users.UsersBean;

import lombok.Data;

/**
 * @author Faiser : 08-Sep-2020
 *
 */

@Entity
@Data
@Table(name = "device_token")
public class DeviceTokenBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "id")
	private int id;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UsersBean userBean;

	@Column(name = "device_type")
	private String deviceType;

	@Column(name = "version")
	private String version;

	@Column(name = "device_token")
	private String deviceToken;

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "updated_date")
	private String updatedDate;
}

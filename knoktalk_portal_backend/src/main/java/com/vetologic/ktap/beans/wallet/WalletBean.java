/**
 * 
 */
package com.vetologic.ktap.beans.wallet;

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
 * @author Faiser : 07-Sep-2020
 *
 */
@Entity
@Data
@Table(name = "wallet")
public class WalletBean {

	@Id
	@GeneratedValue(strategy = GenerationType.IDENTITY)
	@Column(name = "wallet_id")
	private int walletId;

	@Column(name = "coins")
	private int coins;

	@Column(name = "gifts")
	private int gifts;

	@ManyToOne
	@JoinColumn(name = "user_id")
	private UsersBean user;

	@Column(name = "deletion_flag")
	private int deletionFlag;

	@Column(name = "created_date")
	private String createdDate;

	@Column(name = "updated_date")
	private String updatedDate;
}

/**
 * 
 */
package com.vetologic.ktap.controller.wallet;

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
import org.springframework.web.bind.annotation.RestController;

import com.vetologic.ktap.beans.response.KtapResponse;
import com.vetologic.ktap.beans.wallet.WalletBean;
import com.vetologic.ktap.model.service.wallet.WalletService;
import com.vetologic.ktap.utils.AppUtil;

/**
 * @author Faiser : 07-Sep-2020
 *
 */
@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
@RequestMapping("admin/wallet")
public class WalletController {

	private static Logger log = LoggerFactory.getLogger(WalletController.class);

	@Autowired
	private WalletService walletService;

	/**
	 * @author Faiser : 07-Sep-2020
	 *
	 *         This API(updateWalletCoin) is used to update coins
	 *
	 * @param wallet
	 * @param id
	 * @param ktapResponse
	 * @return
	 */
	@PutMapping(path = "/updateWalletCoin/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse updateWalletCoin(@RequestBody WalletBean wallet, @PathVariable int id,
			KtapResponse ktapResponse) {

		WalletBean walletDetails = (WalletBean) walletService.getByUserId("WalletBean", id);
		if (walletDetails != null) {
			walletDetails.setCoins(wallet.getCoins());
			walletDetails.setUpdatedDate(AppUtil.currentDateWithTime());
			if (walletService.update(walletDetails)) {
				ktapResponse.setSuccess(true);
				ktapResponse.setObject(walletDetails);
				ktapResponse.setMessage("Updated Sucessfully");
				log.info("This Wallet Id: " + walletDetails.getWalletId() + " Coin Updated Sucessfully");
			} else {
				ktapResponse.setSuccess(false);
				ktapResponse.setMessage("Fails to Update Coin");
				log.info("Fails to Update");
			}
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("This Wallet Not Exist");
			log.info("This wallet Id: " + wallet.getWalletId() + " Not Exist");
		}
		return ktapResponse;
	}

	/**
	 * @author Faiser : 07-Sep-2020
	 *
	 *         This API(updateWalletGifts) is used to update gifts
	 *
	 * @param wallet
	 * @param id
	 * @param ktapResponse
	 * @return
	 */
	@PutMapping(path = "/updateWalletGifts/{id}", consumes = MediaType.APPLICATION_JSON_VALUE, produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse updateWalletGifts(@RequestBody WalletBean wallet, @PathVariable int id,
			KtapResponse ktapResponse) {

		WalletBean walletDetails = (WalletBean) walletService.getByUserId("WalletBean", id);
		if (walletDetails != null) {
			walletDetails.setGifts(wallet.getGifts());
			walletDetails.setUpdatedDate(AppUtil.currentDateWithTime());

			if (walletService.update(walletDetails)) {
				ktapResponse.setSuccess(true);
				ktapResponse.setObject(walletDetails);
				ktapResponse.setMessage("Updated Sucessfully");
				log.info("This Wallet Id: " + walletDetails.getWalletId() + "Gift Updated Sucessfully");
			} else {
				ktapResponse.setSuccess(false);
				ktapResponse.setMessage("Fails to Update Gift");
				log.info("Fails to Update");
			}
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("This Wallet Not Exist");
			log.info("This wallet Id: " + wallet.getWalletId() + " Not Exist");
		}
		return ktapResponse;
	}

	/**
	 * @author Faiser : 07-Sep-2020
	 *
	 *         This API(getWalletDetailsByUserId) is used to get wallet details
	 *
	 * @param id
	 * @param ktapResponse
	 * @return
	 */
	@GetMapping(path = "/getWalletDetailsByUserId/{id}", produces = MediaType.APPLICATION_JSON_VALUE)
	public KtapResponse getWalletDetailsByUserId(@PathVariable int id, KtapResponse ktapResponse) {
		WalletBean wallet = (WalletBean) walletService.getByUserId("WalletBean", id);
		if (wallet != null) {
			ktapResponse.setObject(wallet);
			ktapResponse.setSuccess(true);
		} else {
			ktapResponse.setSuccess(false);
			ktapResponse.setMessage("wallet Not Exist");
			log.info("This wallet Id: " + id + " Not Exist");
		}
		return ktapResponse;
	}
}

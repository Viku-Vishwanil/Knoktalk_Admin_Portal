/**
 * 
 */
package com.vetologic.ktap.model.service.wallet;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.ktap.model.repository.wallet.WalletRepository;

/**
 * @author Faiser : 07-Sep-2020
 *
 */
@Service
public class WalletServiceImpl implements WalletService{

	@Autowired
	private WalletRepository walletRepository;

	@Override
	public List<?> getAll(String beanClassName) {
		return walletRepository.getAll(beanClassName);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return walletRepository.update(object);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		return walletRepository.getById(beanClassName, id);
	}
	
	@Override
	public Object getByUserId(String beanClassName, int id) {
		return walletRepository.getByUserId(beanClassName, id);
	}
}

package com.vetologic.ktap.model.service.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.ktap.model.repository.users.VerificationRequestRepository;

/**
 * @author Faiser : 07-Sep-2020
 *
 */
@Service
public class VerificationRequestServiceImpl implements VerificationRequestService {

	@Autowired
	private VerificationRequestRepository verificationRequestRepository;

	@Override
	public List<?> getAll(String beanClassName) {
		return verificationRequestRepository.getAll(beanClassName);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return verificationRequestRepository.update(object);
	}

	@Override
	public Object getById(String beanClassName, String id) {
		return verificationRequestRepository.getById(beanClassName, id);
	}

	@Override
	public List<?> getVerificationAndUserList() {
		return verificationRequestRepository.getVerificationAndUserList();
	}

}

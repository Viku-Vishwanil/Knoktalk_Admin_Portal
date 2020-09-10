package com.vetologic.ktap.model.service.users;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.ktap.model.repository.users.UsersRepository;

/**
 * @author Faiser : 02-Sep-2020
 *
 */
@Service
public class UserServiceImpl implements UsersService {

	@Autowired
	private UsersRepository usersRepository;

	@Override
	public List<?> getAll(String beanClassName) {
		return usersRepository.getAll(beanClassName);
	}

	@Transactional
	@Override
	public boolean update(Object object) {
		return usersRepository.update(object);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		return usersRepository.getById(beanClassName, id);
	}
	

}

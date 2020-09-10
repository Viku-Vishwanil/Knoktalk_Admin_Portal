package com.vetologic.ktap.model.service.discoversection;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.ktap.model.repository.discoversection.DiscoverSectionRepository;

@Service
public class DiscoverSectionServiceImpl implements DiscoverSectionService{

	@Autowired
	private DiscoverSectionRepository discoverSectionRepo;
	
	@Transactional
	@Override
	public int save(Object object) {
		
		return discoverSectionRepo.save(object);
	}

	@Override
	public List<?> getAll(String beanClassName) {
		
		return discoverSectionRepo.getAll(beanClassName);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		
		return discoverSectionRepo.getById(beanClassName, id);
	}
	@Transactional
	@Override
	public boolean update(Object object) {
		
		return discoverSectionRepo.update(object);
	}

}

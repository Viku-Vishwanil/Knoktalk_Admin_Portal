/**
 * 
 */
package com.vetologic.ktap.model.service.sounds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;
import com.vetologic.ktap.model.repository.sounds.SectionRepository;

/**
 * @author VIKASH : 3 Sep, 2020
 *
 */
@Service
public class SectionServiceImpl implements SectionService{
	
	@Autowired
	private SectionRepository SectionRepo;
	
	@Transactional
	@Override
	public int save(Object object) {
		
		return SectionRepo.save(object);
	}

	@Override
	public List<?> getAll(String beanClassName) {
		
		return SectionRepo.getAll(beanClassName);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		
		return SectionRepo.getById(beanClassName, id);
	}
	@Transactional
	@Override
	public boolean update(Object object) {
		
		return SectionRepo.update(object);
	}

}

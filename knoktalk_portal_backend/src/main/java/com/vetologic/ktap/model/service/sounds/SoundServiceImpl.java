/**
 * 
 */
package com.vetologic.ktap.model.service.sounds;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.ktap.model.repository.sounds.SoundRepository;

/**
 * @author VIKASH : 7 Sep, 2020
 *
 */
@Service
public class SoundServiceImpl implements SoundService {
	
	@Autowired
	private SoundRepository soundRepo;
	
	@Transactional
	@Override
	public int save(Object object) {
		
		return soundRepo.save(object);
	}

	@Override
	public List<?> getAll(String beanClassName) {
		
		return soundRepo.getAll(beanClassName);
	}

	@Override
	public Object getById(String beanClassName, int id) {
		
		return soundRepo.getById(beanClassName, id);
	}
	@Transactional
	@Override
	public boolean update(Object object) {
		
		return soundRepo.update(object);
	}

}

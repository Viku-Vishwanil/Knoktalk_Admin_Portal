/**
 * 
 */
package com.vetologic.ktap.model.service.video;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.vetologic.ktap.model.repository.videos.VideoRepository;

/**
 * @author Rambabu : 04-Sep-2020
 *
 */
@Service
public class VideoServiceImpl implements VideoService {

	@Autowired
	private VideoRepository videoRepository;

	@Transactional
	@Override
	public int save(Object object) {

		return videoRepository.save(object);
	}

	@Override
	public List<?> getAll(String beanClassName) {

		return videoRepository.getAll(beanClassName);
	}

	@Override
	public Object getById(String beanClassName, int id) {

		return videoRepository.getById(beanClassName, id);
	}

	@Transactional
	@Override
	public boolean update(Object object) {

		return videoRepository.update(object);
	}

}

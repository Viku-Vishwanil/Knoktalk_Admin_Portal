package com.vetologic.ktap.model.service.sounds;

import java.util.List;

/**
 * @author VIKASH : 7 Sep, 2020
 *
 */
public interface SoundService {
	
	int save(Object object);

	List<?> getAll(String beanClassName);

	Object getById(String beanClassName, int id);

	boolean update(Object object);

}

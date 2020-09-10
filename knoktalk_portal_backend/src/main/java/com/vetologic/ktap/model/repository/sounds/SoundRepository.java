package com.vetologic.ktap.model.repository.sounds;

import java.util.List;

/**
 * @author VIKASH : 7 Sep, 2020
 *
 */
public interface SoundRepository {
	
	int save(Object object);

	List<?> getAll(String beanClassName);

	Object getById(String beanClassName, int id);

	boolean update(Object object);

}

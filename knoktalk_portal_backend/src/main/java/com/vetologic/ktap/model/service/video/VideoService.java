/**
 * 
 */
package com.vetologic.ktap.model.service.video;

import java.util.List;

/**
 * @author Rambabu : 04-Sep-2020
 *
 */
public interface VideoService {

	int save(Object object);

	List<?> getAll(String beanClassName);

	Object getById(String beanClassName, int id);

	boolean update(Object object);
}

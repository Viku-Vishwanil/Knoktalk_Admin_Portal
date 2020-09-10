package com.vetologic.ktap.model.service.discoversection;

import java.util.List;

public interface DiscoverSectionService {

	int save(Object object);

	List<?> getAll(String beanClassName);

	Object getById(String beanClassName, int id);

	boolean update(Object object);
}

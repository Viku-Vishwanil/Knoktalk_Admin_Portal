package com.vetologic.ktap.model.repository.discoversection;

import java.util.List;

public interface DiscoverSectionRepository {

	int save(Object object);

	List<?> getAll(String beanClassName);

	Object getById(String beanClassName, int id);

	boolean update(Object object);
}

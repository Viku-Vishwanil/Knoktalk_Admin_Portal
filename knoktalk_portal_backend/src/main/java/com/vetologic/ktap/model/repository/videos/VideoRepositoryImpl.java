/**
 * 
 */
package com.vetologic.ktap.model.repository.videos;

import java.io.Serializable;
import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Rambabu : 04-Sep-2020
 *
 */
@Repository
public class VideoRepositoryImpl implements VideoRepository {

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public int save(Object object) {
		Serializable serializable = 0;
		Session session = getSession();
		try {
			serializable = session.save(object);
		} catch (Exception e) {
			e.printStackTrace();
		}
		return (int) serializable;
	}

	@Override
	public List<?> getAll(String beanClassName) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery("FROM " + beanClassName + " WHERE deletionFlag=?0");
			query.setParameter(0, 0);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}

	@Override
	public Object getById(String beanClassName, int id) {
		Session session = getSession();
		Object object = null;
		try {
			Query<?> query = session.createQuery("FROM " + beanClassName + " WHERE deletionFlag=?0 AND videoId=?1");
			query.setParameter(0, 0);
			query.setParameter(1, id);
			object = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public boolean update(Object object) {
		Session session = getSession();
		try {
			session.update(object);
			return true;
		} catch (Exception e) {
			e.printStackTrace();
			return false;
		}
	}

}

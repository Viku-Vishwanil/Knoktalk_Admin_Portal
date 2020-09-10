package com.vetologic.ktap.model.repository.users;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Faiser : 07-Sep-2020
 *
 */
@Repository
public class VerificationRequestRepositoryImpl implements VerificationRequestRepository{

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

	@Override
	public List<?> getAll(String beanClassName) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery("FROM " + beanClassName);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
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

	@Override
	public Object getById(String beanClassName, String id) {
		Session session = getSession();
		Object object = null;
		try {
			Query<?> query = session
					.createQuery("FROM " + beanClassName + " WHERE  knoktalkId = ?0");
			query.setParameter(0, id);
			object = query.uniqueResult();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return object;
	}

	@Override
	public List<?> getVerificationAndUserList() {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery("SELECT t1.knoktalkId, t1.createdDate, t1.attachment, t2.firstName,t2.username\r\n" + 
					"	FROM VerificationRequestBean t1\r\n" + 
					"	INNER JOIN UsersBean t2 ON t2.knoktalkId = t1.knoktalkId");
			listOfObjects = query.getResultList();
			System.err.println("INside resp"+listOfObjects.toString());
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
		
	}
}

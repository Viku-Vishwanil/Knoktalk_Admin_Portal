/**
 * 
 */
package com.vetologic.ktap.model.repository.device;

import java.util.List;

import javax.persistence.EntityManager;

import org.hibernate.Session;
import org.hibernate.query.Query;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Repository;

/**
 * @author Faiser : 08-Sep-2020
 *
 */
@Repository
public class DeviceTokenRepositoryImpl implements DeviceTokenRepository {

	@Autowired
	private EntityManager entityManager;

	private Session getSession() {
		return entityManager.unwrap(Session.class);
	}

//	@Override
//	public Object getByUserId(String beanClassName, int id) {
//		Session session = getSession();
//		Object object = null;
//		try {
//			Query<?> query = session.createQuery("FROM " + beanClassName + " userBean.userId = ?0");
//			query.setParameter(0, id);
//			object = query.uniqueResult();
//		} catch (Exception e) {
//			e.printStackTrace();
//		}
//		return object;
//	}
	
	@Override
	public List<?> getByUserId(String beanClassName, int id) {
		Session session = getSession();
		List<?> listOfObjects = null;
		try {
			Query<?> query = session.createQuery("FROM " + beanClassName + "  WHERE userBean.userId = ?0");
			query.setParameter(0, id);
			listOfObjects = query.getResultList();
		} catch (Exception e) {
			e.printStackTrace();
		}
		return listOfObjects;
	}
}

package com.capgemini.capstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;

import org.springframework.stereotype.Repository;

import com.capgemini.capstore.bean.MerchantTemporaryBean;
import com.capgemini.capstore.exception.CapstoreException;
@Repository
public class AdminDaoImpl implements AdminDao{
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	EntityManager entityManager = null;
	EntityTransaction transaction = null;
	
	@Override
	public boolean registrationForThirdPartyMerchant(MerchantTemporaryBean merchantTemporaryBean) {
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		boolean isAdded = false;

		try {
			transaction.begin();
			entityManager.persist(merchantTemporaryBean);
			transaction.commit();
			isAdded = true;
		} catch (Exception e) {
			throw new CapstoreException("Email already Exists Try with different Email");
					}
		entityManager.close();
		return isAdded;
	}

}

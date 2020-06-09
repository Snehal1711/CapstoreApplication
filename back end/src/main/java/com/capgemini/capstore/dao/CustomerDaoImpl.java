package com.capgemini.capstore.dao;

import javax.persistence.EntityManager;
import javax.persistence.EntityManagerFactory;
import javax.persistence.EntityTransaction;
import javax.persistence.PersistenceUnit;
import javax.persistence.Query;

import org.springframework.stereotype.Repository;

import com.capgemini.capstore.bean.CustomerTemporaryBean;
import com.capgemini.capstore.bean.LoginBean;
import com.capgemini.capstore.exception.CapstoreException;
import com.capgemini.capstore.utility.PasswordEncryptor;

@Repository
public class CustomerDaoImpl implements CustomerDao {
	@PersistenceUnit
	private EntityManagerFactory entityManagerFactory;
	EntityManager entityManager = null;
	EntityTransaction transaction = null;
	@Override
	public boolean register(CustomerTemporaryBean customerTemporaryBean) {
		entityManager = entityManagerFactory.createEntityManager();
		transaction = entityManager.getTransaction();
		boolean isAdded = false;

		try {
			transaction.begin();
			entityManager.persist(customerTemporaryBean);
			transaction.commit();
			isAdded = true;
		} catch (Exception e) {
			throw new CapstoreException("Email already Exists Try with different Email");
					}
		entityManager.close();
		return isAdded;
	}
	@Override
	public LoginBean login(String email, String password) {
		
		entityManager = entityManagerFactory.createEntityManager();
		LoginBean loginBean = null;
		LoginBean loginBean1 = null;
		try {

			String jpql = "from LoginBean where email=:email";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("email", email);
			loginBean1 = (LoginBean) query.getSingleResult();
			String encryptedPassword = loginBean1.getPassword();
			if (PasswordEncryptor.checkPassword(password, encryptedPassword)) {
				try {
					loginBean = (LoginBean) query.getSingleResult();
				} catch (Exception e) {
					throw new CapstoreException("Something went wrong ...user cannot be login!!!");
				}
			}

		} catch (Exception e) {
			throw new CapstoreException("Email not exists");
		}
		return loginBean;
	}
	@Override
	public boolean changePassword(LoginBean loginBean, String newPassword) {
		
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();
		boolean isUpdate = false;
		boolean isTrue = false;
		String password1;
		try {
			entityTransaction.begin();
			newPassword = PasswordEncryptor.encryptPassword(newPassword);
			int id = loginBean.getId();
			String email = loginBean.getEmail();
			String password = loginBean.getPassword();
			System.out.println(password);
			String pass1 = PasswordEncryptor.encryptPassword(password);
			if (PasswordEncryptor.checkPassword(password, pass1)) {
				isTrue = PasswordEncryptor.checkPassword(password, pass1);
				String jpql1 = "select u from LoginBean u where id=:id";
				Query query1 = entityManager.createQuery(jpql1);
				query1.setParameter("id", id);
				loginBean = (LoginBean) query1.getSingleResult();
				System.out.println(loginBean);
				password1 = loginBean.getPassword();
				System.out.println(password1);
				System.out.println(pass1);
				if (PasswordEncryptor.checkPassword(password, password1)) {
					String jpql = "update LoginBean set password=:newPassword where id=:id";
					Query query = entityManager.createQuery(jpql);
					query.setParameter("newPassword", newPassword);
					query.setParameter("id", id);
					int queryResult = query.executeUpdate();
					entityTransaction.commit();
					isUpdate = true;

				}
			}
		} catch (Exception e) {
			entityTransaction.rollback();
			throw new CapstoreException("Please Enter Correct Old Password");

		}
		
		return isUpdate;
	}
	@Override
	public boolean forgetPassword(String email, String newPassword) {
		System.out.println(email);
		System.out.println(newPassword);
		boolean isUpdated = false;
		EntityManager entityManager = entityManagerFactory.createEntityManager();
		EntityTransaction entityTransaction = entityManager.getTransaction();

		try {
			entityTransaction.begin();
			newPassword =PasswordEncryptor.encryptPassword(newPassword);
            System.out.println(newPassword);
			String jpql = "update LoginBean set password=:newPassword where email =:email";
			Query query = entityManager.createQuery(jpql);
			query.setParameter("newPassword", newPassword);
			query.setParameter("email", email);
			int queryResult = query.executeUpdate();
			System.out.println(queryResult);
			if(queryResult >0) {
				isUpdated = true;
			}
			entityTransaction.commit();
			
		} catch (Exception e) {
			entityTransaction.rollback();
			e.printStackTrace();
			throw new CapstoreException("Email not Exsist");
			
		}
		return isUpdated;
	}

}

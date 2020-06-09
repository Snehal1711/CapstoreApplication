package com.capgemini.capstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.capstore.bean.CustomerTemporaryBean;
import com.capgemini.capstore.bean.LoginBean;
import com.capgemini.capstore.dao.CustomerDao;
import com.capgemini.capstore.exception.CapstoreException;
import com.capgemini.capstore.utility.PasswordEncryptor;
import com.capgemini.capstore.validation.CapstoreValidation;

@Service
public class CustomerSeviceImpl implements CustomerService{
	@Autowired
	private CustomerDao customerDao;
	@Autowired
	private CapstoreValidation capstoreValidation;

	@Override
	public boolean register(CustomerTemporaryBean customerTemporaryBean) {
		String email = customerTemporaryBean.getEmail();
		String password = customerTemporaryBean.getPassword();
		String phoneNo = customerTemporaryBean.getPhoneNumber();

		if (capstoreValidation.emailValidation(email)) {
			if (capstoreValidation.passwordValidation(password)) {
				if (capstoreValidation.mobileNoValidation(phoneNo)) {
					customerTemporaryBean.setPassword(PasswordEncryptor.encryptPassword(customerTemporaryBean.getPassword()));
					return customerDao.register(customerTemporaryBean);
				} else {
					throw new CapstoreException("The Number Starts From 6-9 And It Contains 10 Digit(Number) Only");
				}
			} else {
				throw new CapstoreException("Password Must have atleast 1 captial letter,special charcter,digit");
			}
		} else {
			throw new CapstoreException("Please Enter Valid Email");
		}

	}

	@Override
	public LoginBean login(String email, String password) {
		return customerDao.login(email, password);
	}

	@Override
	public boolean changePassword(LoginBean loginBean, String newPassword) {
		
		return customerDao.changePassword(loginBean, newPassword);
	}

	@Override
	public boolean forgetPassword(String email, String newPassword) {
		
		return customerDao.forgetPassword(email, newPassword);
	}

}

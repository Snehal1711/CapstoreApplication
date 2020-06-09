package com.capgemini.capstore.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.capgemini.capstore.bean.MerchantTemporaryBean;
import com.capgemini.capstore.dao.MerchantDao;
import com.capgemini.capstore.exception.CapstoreException;
import com.capgemini.capstore.utility.PasswordEncryptor;
import com.capgemini.capstore.validation.CapstoreValidation;

@Service
public class AdminServiceImpl implements AdminService{
	@Autowired
	MerchantDao merchantDao;
	@Autowired
	CapstoreValidation capstoreValidation;

	@Override
	public boolean registrationForThirdPartyMerchant(MerchantTemporaryBean merchantTemporaryBean) {
		String email = merchantTemporaryBean.getEmail();
		String password = merchantTemporaryBean.getPassword();
		String phoneNo = merchantTemporaryBean.getPhoneNumber();

		if (capstoreValidation.emailValidation(email)) {
			if (capstoreValidation.passwordValidation(password)) {
				if (capstoreValidation.mobileNoValidation(phoneNo)) {
					merchantTemporaryBean.setPassword(PasswordEncryptor.encryptPassword(merchantTemporaryBean.getPassword()));
					return merchantDao.registerAsMerchant(merchantTemporaryBean);
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

}

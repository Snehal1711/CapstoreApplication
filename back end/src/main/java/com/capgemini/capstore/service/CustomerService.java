package com.capgemini.capstore.service;

import com.capgemini.capstore.bean.CustomerTemporaryBean;
import com.capgemini.capstore.bean.LoginBean;

public interface CustomerService {
	public boolean register(CustomerTemporaryBean customerTemporaryBean);
	public LoginBean login(String email,String password);
	public boolean changePassword(LoginBean loginBean,String newPassword);
	public boolean forgetPassword(String email,String newPassword);

}

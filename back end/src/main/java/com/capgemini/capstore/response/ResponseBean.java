package com.capgemini.capstore.response;

import com.capgemini.capstore.bean.CustomerTemporaryBean;
import com.capgemini.capstore.bean.LoginBean;

public class ResponseBean {

	private int statusCode;
	private String message;
	private String description;
	private CustomerTemporaryBean customerBean;
	private String role;
	private LoginBean loginBean;

	public LoginBean getLoginBean() {
		return loginBean;
	}

	public void setLoginBean(LoginBean loginBean) {
		this.loginBean = loginBean;
	}

	public String getRole() {
		return role;
	}

	public void setRole(String role) {
		this.role = role;
	}

	public int getStatusCode() {
		return statusCode;
	}

	public void setStatusCode(int statusCode) {
		this.statusCode = statusCode;
	}

	public String getMessage() {
		return message;
	}

	public void setMessage(String message) {
		this.message = message;
	}

	public String getDescription() {
		return description;
	}

	public void setDescription(String description) {
		this.description = description;
	}

	public CustomerTemporaryBean getCustomerBean() {
		return customerBean;
	}

	public void setCustomerBean(CustomerTemporaryBean customerBean) {
		this.customerBean = customerBean;
	}
}

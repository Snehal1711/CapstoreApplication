package com.capgemini.capstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capstore.bean.CustomerTemporaryBean;
import com.capgemini.capstore.bean.LoginBean;
import com.capgemini.capstore.response.ResponseBean;
import com.capgemini.capstore.exception.CapstoreException;
import com.capgemini.capstore.service.CustomerService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public class CustomerController {
	@Autowired
	CustomerService customerService;
	String success = "success";
	int userId = 0;

	@PostMapping("/customerRegistration")
	public ResponseBean registerUser(@RequestBody CustomerTemporaryBean customerBean) {
		ResponseBean responseBean = new ResponseBean();
		customerBean.setRole("customer");
		customerService.register(customerBean);
		responseBean.setStatusCode(200);
		responseBean.setMessage(success);
		responseBean.setDescription("Customer Register Successfully");
		return responseBean;
	}// end of add User

	@PostMapping("/login")
	public ResponseBean login(@RequestBody LoginBean loginBean) {
		String email = loginBean.getEmail();
		String password = loginBean.getPassword();
		ResponseBean response = new ResponseBean();
		LoginBean loginBean2 = customerService.login(email, password);
		if (loginBean2 != null) {
			userId = loginBean2.getId();
			String type = loginBean2.getRole();
			response.setStatusCode(222);
			response.setMessage(success);
			response.setDescription("Login successfully...");
			response.setRole(type);
			response.setLoginBean(loginBean2);

		} else {
			response.setDescription("Entered Password is Wrong ");
		}
		return response;

	}

	@PostMapping("/changePassword")
	public ResponseBean changePassword(@RequestBody LoginBean loginBean, @RequestParam String newPassword) {
		boolean IsTrue = customerService.changePassword(loginBean, newPassword);
		System.out.println(IsTrue);
		if (IsTrue == true) {
			ResponseBean response = new ResponseBean();
			response.setStatusCode(200);
			response.setMessage(success);
			response.setDescription("Password Changed Successfully");
			return response;
		} else {
			throw new CapstoreException("Old Password is Wrong");
		}
	}

	@PostMapping("/forgetPassword")
	public ResponseBean forgetPassword(@RequestParam String email, @RequestParam String newPassword) {
		boolean isTrue = customerService.forgetPassword(email, newPassword);
		System.out.println(isTrue);
		if(isTrue) {
		ResponseBean response = new ResponseBean();
		response.setStatusCode(200);
		response.setMessage(success);
		response.setDescription("Password Changed Successfully");
		return response;
		}else {
			throw new CapstoreException("Email not Exist");
		}
	}

}

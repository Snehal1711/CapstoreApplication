package com.capgemini.capstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capstore.bean.MerchantTemporaryBean;
import com.capgemini.capstore.response.ResponseBean;
import com.capgemini.capstore.service.AdminService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*")
public class AdminController {

	@Autowired
	AdminService adminService;
	   String success = "success";
	@PostMapping("/thirdPartyMerchantRegistration")
	public ResponseBean registerUser(@RequestBody MerchantTemporaryBean merchantTemporaryBean) {
		ResponseBean responseBean = new ResponseBean();
		merchantTemporaryBean.setRole("thirdPartyMerchant");
		adminService.registrationForThirdPartyMerchant(merchantTemporaryBean);
		responseBean.setStatusCode(200);
		responseBean.setMessage(success);
		responseBean.setDescription("Third Party Merchant Register Successfully");
		return responseBean;
	}// end of add User
}

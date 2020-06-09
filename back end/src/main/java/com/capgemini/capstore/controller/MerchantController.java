package com.capgemini.capstore.controller;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.capgemini.capstore.bean.MerchantTemporaryBean;
import com.capgemini.capstore.response.ResponseBean;
import com.capgemini.capstore.service.MerchantService;

@RestController
@CrossOrigin(origins = "*", allowedHeaders = "*", allowCredentials = "true")
public class MerchantController {
    @Autowired
	MerchantService merchantService;
    String success = "success";
	@PostMapping("/merchantRegistration")
	public ResponseBean registerUser(@RequestBody MerchantTemporaryBean merchantTemporaryBean) {
		ResponseBean responseBean = new ResponseBean();
		merchantTemporaryBean.setRole("direct");
		merchantService.registerAsMerchant(merchantTemporaryBean);
		responseBean.setStatusCode(200);
		responseBean.setMessage(success);
		responseBean.setDescription("Merchant Register Successfully Wait For Validate");
		return responseBean;
	}// end of add User

}

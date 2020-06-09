package com.capgemini.capstore.controller;

import org.springframework.web.bind.annotation.ExceptionHandler;
import org.springframework.web.bind.annotation.RestControllerAdvice;

import com.capgemini.capstore.response.ResponseBean;
import com.capgemini.capstore.exception.CapstoreException;

@RestControllerAdvice
public class UserAdviseController {

	@ExceptionHandler()
	public ResponseBean capstoreException(CapstoreException e) {
		ResponseBean response = new ResponseBean();
		response.setStatusCode(401);
		response.setMessage("Failer");
		response.setDescription(e.getMessage());
		return response;
	}
}

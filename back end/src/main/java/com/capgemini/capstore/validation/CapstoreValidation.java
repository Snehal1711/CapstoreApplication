package com.capgemini.capstore.validation;

public interface CapstoreValidation {
	public boolean emailValidation(String email);

	public boolean passwordValidation(String password);
	
	public boolean mobileNoValidation(String phoneNo);
	
	public boolean userNameValidation(String userName);

}

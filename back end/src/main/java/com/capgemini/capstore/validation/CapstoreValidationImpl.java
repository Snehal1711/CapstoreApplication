package com.capgemini.capstore.validation;

import java.util.regex.Matcher;
import java.util.regex.Pattern;

import org.springframework.stereotype.Service;
@Service
public class CapstoreValidationImpl implements CapstoreValidation {
	Pattern pattern=null;
    Matcher matcher=null;

	@Override
	public boolean emailValidation(String email) {
		pattern=Pattern.compile("\\w+\\@\\w+\\.\\w+");
		matcher=pattern.matcher(email);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean passwordValidation(String password) {
		pattern=Pattern.compile("(?=.*[a-z])(?=.*[A-Z])(?=.*[0-9]).{8,15}");
		matcher=pattern.matcher(password);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean mobileNoValidation(String phoneNo) {
		pattern=Pattern.compile("[6,7,8,9][0-9]{9}");
		matcher=pattern.matcher(phoneNo);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}

	@Override
	public boolean userNameValidation(String userName) {
		pattern=Pattern.compile("[a-zA-Zs]+\\s[a-zA-Z]+$");
		matcher=pattern.matcher(userName);
		if(matcher.matches()) {
			return true;
		}
		return false;
	}

}

package com.navinfo.atmapi.service;

import javax.security.auth.login.AccountNotFoundException;

import com.navinfo.atmapi.model.Account;

public interface AuthenticationService {
	
	String generateOTP(Account account) throws AccountNotFoundException;

    boolean verifyOTP(long accountNumber, String otp);
}

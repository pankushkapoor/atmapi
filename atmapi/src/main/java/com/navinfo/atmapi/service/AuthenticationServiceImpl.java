package com.navinfo.atmapi.service;

import java.util.HashMap;
import java.util.Map;
import java.util.Random;

import javax.security.auth.login.AccountNotFoundException;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navinfo.atmapi.model.Account;
import com.navinfo.atmapi.repository.AccountRepository;

@Service
public class AuthenticationServiceImpl implements AuthenticationService{

	@Autowired
    private AccountRepository accountRepository;
	
	Map<Long, String> authenticationDetails = new HashMap<Long, String>();
	
	public String generateOTP(Account account) throws AccountNotFoundException{
		boolean result = accountRepository.verifyAccount(account);
		
		if (result == true) {
			Random rand = new Random();
            String otp = Integer.toString(rand.nextInt(1000000)); //10 million users
            authenticationDetails.put(new Long(account.getAccountNumber()), otp);
            return otp;
        } 
		else {
			return "Invalid Account Number or PIN";
        }
	}

	public boolean verifyOTP(long accountNumber, String otp) {
		
		String existingOTP = authenticationDetails.get(new Long(accountNumber));

        if (existingOTP != null && existingOTP.equals(otp)) {
            authenticationDetails.remove(new Long(accountNumber));
            return true;
        } 
        else {
            return false;
        }
	}
}
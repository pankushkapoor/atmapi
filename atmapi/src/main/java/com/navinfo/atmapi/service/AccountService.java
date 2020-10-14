package com.navinfo.atmapi.service;

import com.navinfo.atmapi.exceptions.InsufficientBalanceException;
import com.navinfo.atmapi.model.Account;

public interface AccountService {
	
	String createAccount(Account account);
	
	public boolean verifyAccountDetails(Account account);
	
	double checkBalance(long accountNumber);

	boolean withdraw(long accountNumber, int amount) throws InsufficientBalanceException;

	boolean deposit(long accountNumber, int amount);
	
}

package com.navinfo.atmapi.service;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.navinfo.atmapi.exceptions.InsufficientBalanceException;
import com.navinfo.atmapi.model.Account;
import com.navinfo.atmapi.repository.AccountRepository;

@Service
public class AccountServiceImpl implements AccountService{

	@Autowired
	AccountRepository accountRepository;
	
	public double checkBalance(long accountNumber) {

		return accountRepository.getBalance(accountNumber);
	}

	public boolean withdraw(long accountNumber, int amount) throws InsufficientBalanceException{
		
		double balance = accountRepository.getBalance(accountNumber);
        if (balance >= amount) {
            boolean result = accountRepository.withdraw(accountNumber, amount);
            if (result) {
                result = accountRepository.addTransaction(accountNumber, -amount);
                return result;
            } else {
                return false;
            }
        } else {
            throw new InsufficientBalanceException("There is not enough balance in your account.");
        }
	}

	public boolean deposit(long accountNumber, int amount) {

		if (amount > 0) {
            boolean result = accountRepository.deposit(accountNumber, amount);
            if (result) {
                result = accountRepository.addTransaction(accountNumber, amount);
                return result;
            } else {
                return false;
            }
        } else {
            return false;
        }
	}
	
	public boolean verifyAccountDetails(Account account) {
		long accountNumber = account.getAccountNumber();
		String accountName = account.getAccountName();
		int pin = account.getPin();
		double initialBalance = account.getBalance(); //should be min 1000
		if(accountNumber <= 0) return false;
		if(accountName == null) return false;
		if(pin < 1000 || pin > 9999) return false;
		if(initialBalance < 1000) return false;
		return true;
	}
	
	public String createAccount(Account account) {
		
		boolean result =  verifyAccountDetails(account);
		if(result) {
			return accountRepository.createAccount(account) ? 
					"Account Successfully created with a/c no,: " + account.getAccountNumber() 
					: "Failed. Please try again.";
		}
		else {
			return "Failed. Please try again.";
		}
	}
}
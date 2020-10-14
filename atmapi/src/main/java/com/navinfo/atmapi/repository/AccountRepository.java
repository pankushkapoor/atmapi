package com.navinfo.atmapi.repository;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.repository.CrudRepository;

import com.navinfo.atmapi.model.Account;

public interface AccountRepository {
	
	boolean createAccount(Account account);
	
	double getBalance(long accountNumber);

	boolean deposit(long accountNumber, int amount);

	boolean withdraw(long accountNumber, int amount);

	boolean addTransaction(long accountNumber, int amount);

    boolean verifyAccount(Account account);
}
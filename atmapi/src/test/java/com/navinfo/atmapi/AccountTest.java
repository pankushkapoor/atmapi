package com.navinfo.atmapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.*;
import org.junit.Assert.*;

import com.navinfo.atmapi.model.Account;
import com.navinfo.atmapi.service.AccountService;

public class AccountTest {
	
	private Account account;
	private AccountService accountService;
	
	@Before
	public void setUp() {
		account = new Account("Pank4", 104, 1235, 1200);
	}
	@After
	public void tearDown() {
		account = null;
	}
	
	@Test
	void TestAccountCreation() {
		long accountNumber = 104;
		String accountName = "sbi";
		int pin = 1234;
		double balance = 1200;
		Account acc = new Account(accountName, accountNumber, pin, balance);
		assertEquals(accountService.createAccount(acc), 
				"Account Successfully created with a/c no,: " + accountNumber);
	}
	
	@Test
	void TestWithdrawals() {
	}
	
	@Test
	void TestDeposit() {
	}
	
	@Test
	void TestCheckBalance() {
	}
}

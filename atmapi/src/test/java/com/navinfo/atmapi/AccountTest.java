package com.navinfo.atmapi;

import org.junit.jupiter.api.Test;

import static org.junit.jupiter.api.Assertions.assertEquals;

import org.junit.*;
import org.junit.Assert.*;

import com.navinfo.atmapi.exceptions.InsufficientBalanceException;
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
	void TestAccountCreationNormal() {
		long accountNumber = 105;
		String accountName = "sbi";
		int pin = 1234;
		double balance = 1200;
		Account acc = new Account(accountName, accountNumber, pin, balance);
		assertEquals("Account Successfully created with a/c no,: " + accountNumber, 
				accountService.createAccount(acc));
	}
	
	@Test
	void TestAccountCreationDuplicateAccountNumber() { //i.e. 1000
		long accountNumber = 104;
		String accountName = "sbi";
		int pin = 1234;
		double initialBalance = 1500;
		Account acc = new Account(accountName, accountNumber, pin, initialBalance);
		assertEquals("Failed. Please try again.", 
				accountService.createAccount(acc));
	}
	
	@Test
	void TestAccountCreationInvalidPin() {
		long accountNumber = 106;
		String accountName = "sbi";
		int pin = 12345;
		double balance = 1200;
		Account acc = new Account(accountName, accountNumber, pin, balance);
		assertEquals("Failed. Please try again.", 
				accountService.createAccount(acc));
	}
	
	@Test
	void TestAccountCreationAmountLessThanMinAmount() { //i.e. 1000
		long accountNumber = 107;
		String accountName = "sbi";
		int pin = 1234;
		double initialBalance = 900;
		Account acc = new Account(accountName, accountNumber, pin, initialBalance);
		assertEquals("Failed. Please try again.", 
				accountService.createAccount(acc));
	}
	
	
	@Test
	void TestWithdrawalNormal() throws InsufficientBalanceException {
		
		int amountToBeWithdrawn = 200;
		assertEquals(true, accountService.withdraw(account.getAccountNumber(), 
				amountToBeWithdrawn));
	}
	
	@Test
	void TestWithdrawalMoreThanBalance() throws InsufficientBalanceException {
		
		int amountToBeWithdrawn = 20200;
		assertEquals(false, accountService.withdraw(account.getAccountNumber(), 
				amountToBeWithdrawn));
	}
	
	@Test
	void TestDepositNormal() {
		
		int amountToBeDeposited = 200;
		assertEquals(true, accountService.deposit(account.getAccountNumber(), 
				amountToBeDeposited));
	}
	
	@Test
	void TestDepositZeroAmount() {
		
		int amountToBeDeposited = 0;
		assertEquals(false, accountService.deposit(account.getAccountNumber(), 
				amountToBeDeposited));
	}
	
	@Test
	void TestCheckBalance() {
		
		assertEquals("Account Successfully created with a/c no,: " + account.getAccountNumber()	, 
				accountService.createAccount(account));
	}
	
	//bank denominations can also be added
}

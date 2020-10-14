package com.navinfo.atmapi.repository;

import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.Date;

import org.springframework.jdbc.core.JdbcTemplate;
import org.springframework.jdbc.core.RowMapper;
import org.springframework.stereotype.Repository;

import com.navinfo.atmapi.model.Account;
import com.navinfo.atmapi.util.DBUtil;

@Repository
public class AccountRepositoryImpl implements AccountRepository{

	private JdbcTemplate jdbcTemplate;
	
	public AccountRepositoryImpl() {
        this.jdbcTemplate = DBUtil.getJdbcTemplate();
    }
	
	public double getBalance(long accountNumber) {
		
		return jdbcTemplate.queryForObject("SELECT BALANCE FROM BANK.ACCOUNT where ACCOUNT_NUMBER=?", 
				new Object[] { accountNumber }, 
				new RowMapper<Double>() 
				{
					public Double mapRow(ResultSet rowSet, int arg1) throws SQLException 
					{
						return rowSet.getDouble("BALANCE");
					}
				});
	}

	public boolean deposit(long accountNumber, int amount) {

		return jdbcTemplate.update("UPDATE BANK.ACCOUNT SET BALANCE=BALANCE+? WHERE ACCOUNT_NUMBER=?", 
				new Object[] { amount, accountNumber }) == 1;
	}

	public boolean withdraw(long accountNumber, int amount) {

		return jdbcTemplate.update("UPDATE BANK.ACCOUNT SET BALANCE=BALANCE-? WHERE ACCOUNT_NUMBER=?", 
				new Object[] { amount, accountNumber }) == 1;
	}

	public boolean addTransaction(long accountNumber, int amount) {
		
		return jdbcTemplate.update("insert into BANK.TRANSACTION_DETAILS"
				+ "(ACCOUNT_NUMBER,TRANSACTION_TIME,TRANSACTION_AMOUNT) values(?,?,?)", 
				new Object[] { accountNumber, new Date(), amount }) == 1;
	}

	public boolean verifyAccount(Account account) {
		return jdbcTemplate.queryForObject("SELECT count(*) AS ACCOUNT_COUNT FROM BANK.ACCOUNT "
				+ "WHERE ACCOUNT_NUMBER=? AND ACCOUNT_NAME=? AND PIN=?", 
				new Object[] 
						{ account.getAccountNumber(), account.getAccountName(),account.getPin() }, 
				new RowMapper<Integer>() 
				{
					public Integer mapRow(ResultSet rowSet, int arg1) throws SQLException 
					{
						return rowSet.getInt("ACCOUNT_COUNT");
					}
				}) == 1;
	}

	public boolean createAccount(Account account) {
		
		boolean result = jdbcTemplate.update("INSERT INTO BANK.ACCOUNT"
				+ "(ACCOUNT_NUMBER, ACCOUNT_NAME, BALANCE, PIN) values(?,?,?,?)", 
				new Object[] { account.getAccountNumber(), account.getAccountName(), 
							   account.getBalance(), account.getPin() }) == 1;
		
		return result;
	}
}
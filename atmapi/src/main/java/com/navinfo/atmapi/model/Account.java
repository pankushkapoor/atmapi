package com.navinfo.atmapi.model;

import java.util.List;

import javax.persistence.CascadeType;
import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.Table;

@Entity
@Table
public class Account {
	@Column
	private String accountName;    
    @Id
	private long accountNumber;
    @Column
    private int pin;    
    @Column
    private double balance;
	
//    @Column
////    @OneToMany //(mappedBy="account",cascade=CascadeType.ALL,orphanRemoval=true)
//    private List<Transaction> transactionIds;
    
    
    
	public String getAccountName() {
        return accountName;
    }

    public Account(String accountName, long accountNumber, int pin, double balance) {
	super();
	this.accountName = accountName;
	this.accountNumber = accountNumber;
	this.pin = pin;
	this.balance = balance;
}

	public Account() {
	super();
}

	public long getAccountNumber() {
        return accountNumber;
    }

    public int getPin() {
        return pin;
    }

    public double getBalance() {
        return balance;
    }

//	public List<Integer> getTransactions() {
//		return transactionIds;
//	}
}
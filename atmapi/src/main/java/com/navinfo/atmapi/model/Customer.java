package com.navinfo.atmapi.model;

import java.util.List;

import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import javax.persistence.SequenceGenerator;

@Entity
public class Customer {
	
	@Id
//	@SequenceGenerator(name="cust_seq",sequenceName="cust_seq",initialValue=1,allocationSize=1)
//	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="cust_seq")
	private int customerId;
	private String customerName;
	private String address;
	private String email;
	
	@OneToMany
	private List<Account> accounts;

	public Customer(int customerId, String customerName, String address, String email) {
		super();
		this.customerId = customerId;
		this.customerName = customerName;
		this.address = address;
		this.email = email;
	}

	public int getCustomerId() {
		return customerId;
	}

	public String getCustomerName() {
		return customerName;
	}

	public String getAddress() {
		return address;
	}

	public String getEmail() {
		return email;
	}

	public List<Account> getAccounts() {
		return accounts;
	}
}

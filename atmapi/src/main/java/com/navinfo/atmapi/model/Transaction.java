package com.navinfo.atmapi.model;

import java.sql.Time;

import javax.persistence.Column;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.GenerationType;
import javax.persistence.Id;
import javax.persistence.SequenceGenerator;

@Entity
public class Transaction {
	
	@Id
//	@SequenceGenerator(name="trans_seq",sequenceName="trans_seq",initialValue=1,allocationSize=1)
//	@GeneratedValue(strategy= GenerationType.SEQUENCE,generator="trans_seq")
	private int transactionId; //a/c no + time 
	
	@Column
	private Time transactionTime;
	
	@Column
	private double transactionAmount;

	public Time getTransactionTime() {
		return transactionTime;
	}

	public double getTransactionAmount() {
		return transactionAmount;
	}
	
}

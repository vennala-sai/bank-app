package com.fdmgroup.apiPack.model;

import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "CHECKING_ACCOUNT")
public class CheckingAccount extends Account{
	
	@NotNull(message = "Next check number must not be null")
	private int nextCheckNumber;
	
	
	public CheckingAccount(long accountId, double balance, Customer customer, int nextCheckNumber) {
		super(accountId, balance, customer);
		this.nextCheckNumber = nextCheckNumber;
	}


	public CheckingAccount(double balance, Customer customer, int nextCheckNumber) {
		super(balance, customer);
		this.nextCheckNumber = nextCheckNumber;
	}


	public int getNextCheckNumber() {
		return nextCheckNumber;
	}


	public void setNextCheckNumber(int nextCheckNumber) {
		this.nextCheckNumber = nextCheckNumber;
	}

	
	
}

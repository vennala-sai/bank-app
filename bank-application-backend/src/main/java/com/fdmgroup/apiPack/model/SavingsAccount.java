package com.fdmgroup.apiPack.model;

import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "SAVINGS_ACCOUNT")
public class SavingsAccount extends Account {
	@NotNull(message = "Interest rate must not be null")
	@Column(name = "INTEREST_RATE")
	private double interestRate;

	public SavingsAccount(long accountId, double balance, Customer customer, double interestRate) {
		super(accountId, balance, customer);
		this.interestRate = interestRate;
	}

	public SavingsAccount(double balance, Customer customer, double interestRate) {
		super(balance, customer);
		this.interestRate = interestRate;
	}

	public double getInterestRate() {
		return interestRate;
	}

	public void setInterestRate(double interestRate) {
		this.interestRate = interestRate;
	}
	
	
	
}

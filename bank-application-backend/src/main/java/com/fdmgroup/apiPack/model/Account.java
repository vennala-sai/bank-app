package com.fdmgroup.apiPack.model;




import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.ManyToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotNull;

@Entity
@Table(name = "ACCOUNT")
@Inheritance(strategy = InheritanceType.JOINED)
//@JsonDeserialize(using = AccountDeserializer.class)
public abstract class Account {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "ACCOUNT_ID_GEN")
    @SequenceGenerator(name="ACCOUNT_ID_GEN", sequenceName = "ACCOUNT_ID_SEQ")
	@Column(name = "ACCOUNT_ID")
	private long accountId;
	
	@NotNull(message = "Balance must not be null")
	@Column(name = "BALANCE")
	private double balance;
	
	@ManyToOne
	@JoinColumn(name = "FK_CUST_ID")
	private Customer customer;
	public Account(long accountId, double balance, Customer customer) {
		super();
		this.accountId = accountId;
		this.balance = balance;
		this.customer = customer;
	}
	public Account(double balance, Customer customer) {
		super();
		this.balance = balance;
		this.customer = customer;
	}
	public long getAccountId() {
		return accountId;
	}
	public void setAccountId(long accountId) {
		this.accountId = accountId;
	}
	public double getBalance() {
		return balance;
	}
	public void setBalance(double balance) {
		this.balance = balance;
	}
	public Customer getCustomer() {
		return customer;
	}
	public void setCustomer(Customer customer) {
		this.customer = customer;
	}
	
	
	
	
}

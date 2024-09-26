package com.fdmgroup.apiPack.model;

import java.util.ArrayList;
import java.util.List;

import com.fasterxml.jackson.databind.annotation.JsonDeserialize;
import com.fdmgroup.apiPack.config.CustomerDeserializer;

import jakarta.persistence.CascadeType;
import jakarta.persistence.Column;
import jakarta.persistence.Entity;
import jakarta.persistence.GeneratedValue;
import jakarta.persistence.GenerationType;
import jakarta.persistence.Id;
import jakarta.persistence.Inheritance;
import jakarta.persistence.InheritanceType;
import jakarta.persistence.JoinColumn;
import jakarta.persistence.OneToMany;
import jakarta.persistence.OneToOne;
import jakarta.persistence.SequenceGenerator;
import jakarta.persistence.Table;
import jakarta.validation.constraints.NotBlank;
@JsonDeserialize(using = CustomerDeserializer.class) //adding this cuz default json parser used by spring (jackson) is trying to create an instance of customer class which is abstract - so we defined customerdeserializer to override spring's
@Inheritance(strategy = InheritanceType.JOINED) //need it so that each sub class table would be linked to this customer table, via customer Id (u dont need to create a foreign key relationship in sub class tables - it's automatic)
@Entity
@Table(name="CUSTOMER")
public abstract class Customer {
	@Id
    @GeneratedValue(strategy = GenerationType.SEQUENCE, generator = "CUSTOMER_ID_GEN")
    @SequenceGenerator(name="CUSTOMER_ID_GEN", sequenceName = "CUSTOMER_ID_SEQ")
	@Column(name = "CUSTOMER_ID")
	private long customerId;
	
	@NotBlank(message = "Name must not be blank")
	@Column(name = "CUSTOMER_NAME", nullable = false)
	private String name;

//	@NotBlank(message = "Street Number must not be blank")
	@OneToOne(cascade=CascadeType.ALL)
	@JoinColumn(name = "FK_ADDRESS_ID", nullable = false)
	private Address address;
	
	@NotBlank(message = "Customer type must not be blank")
	@Column(name = "CUSTOMER_TYPE", nullable = false)
	private String customerType;
	
	@OneToMany(mappedBy= "customer", cascade = CascadeType.ALL)
	private List<Account> accounts;


	public Customer() {
		super();
		this.accounts = new ArrayList<Account>();
	}

	public Customer(long customerId, @NotBlank(message = "Name must not be blank") String name, String customerType, Address address) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.customerType = customerType;
		this.accounts = new ArrayList<Account>();
	}
	
	public Customer(@NotBlank(message = "Name must not be blank") String name, String customerType, Address address) {
		super();
		this.name = name;
		this.address = address;
		this.customerType = customerType;
		this.accounts = new ArrayList<Account>();
	}

	public Customer(long customerId, @NotBlank(message = "Name must not be blank") String name, Address address,
			String customerType, List<Account> accounts) {
		super();
		this.customerId = customerId;
		this.name = name;
		this.address = address;
		this.customerType = customerType;
		this.accounts = accounts;
	}

	public Customer(@NotBlank(message = "Name must not be blank") String name, Address address, String customerType,
			List<Account> accounts) {
		super();
		this.name = name;
		this.address = address;
		this.customerType = customerType;
		this.accounts = accounts;
	}

	public long getCustomerId() {
		return customerId;
	}

	public void setCustomerId(long customerId) {
		this.customerId = customerId;
	}

	public String getName() {
		return name;
	}

	public void setName(String name) {
		this.name = name;
	}

	public Address getAddress() {
		return address;
	}

	public void setAddress(Address address) {
		this.address = address;
	}

	public String getCustomerType() {
		return customerType;
	}

	public void setCustomerType(String customerType) {
		this.customerType = customerType;
	}

	public List<Account> getAccounts() {
		return accounts;
	}

	public void setAccounts(List<Account> accounts) {
		this.accounts = accounts;
	}
	
	
	
}

package com.fdmgroup.apiPack.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Company extends Customer {
	public Company() {
		super();
	}

	public Company(@NotBlank(message = "Name must not be blank") String name, Address address) {
		super(name,"company",address);
	}

	public Company(@NotBlank(message = "Name must not be blank") String name, Address address,
			List<Account> accounts) {
		super(name, address, "company", accounts);
	}
	
	
	
}

package com.fdmgroup.apiPack.model;

import java.util.List;

import jakarta.persistence.Entity;
import jakarta.validation.constraints.NotBlank;


@Entity
public class Person extends Customer {


	public Person() {
		super();
	}

	public Person(@NotBlank(message = "Name must not be blank") String name, Address address) {
		super(name,"person",address);
	}

	public Person(@NotBlank(message = "Name must not be blank") String name, Address address,
			List<Account> accounts) {
		super(name, address, "person", accounts);
	}
	
}

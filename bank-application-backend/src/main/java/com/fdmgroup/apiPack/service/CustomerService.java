package com.fdmgroup.apiPack.service;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.json.JSONObject;
import org.springframework.stereotype.Service;
import org.springframework.web.reactive.function.client.WebClient;

import com.fasterxml.jackson.databind.JsonNode;
import com.fdmgroup.apiPack.customexceptions.CustomerNotFoundException;
import com.fdmgroup.apiPack.model.Account;
import com.fdmgroup.apiPack.model.Address;
import com.fdmgroup.apiPack.model.Company;
import com.fdmgroup.apiPack.model.Customer;
import com.fdmgroup.apiPack.model.Person;
import com.fdmgroup.apiPack.repository.CustomerRepository;

@Service
public class CustomerService {
	private CustomerRepository customerRepo;
	private WebClient webClient;

	public CustomerService(CustomerRepository customerRepo, WebClient webClient) {
		this.customerRepo = customerRepo;
		this.webClient = webClient;
	}

	public List<Customer> getAllCustomers() {
		return customerRepo.findAll();
	}

	public Optional<Customer> getCustomerById(long id) {

		return Optional.ofNullable(customerRepo.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer with id " + id + " could not be found")));
	}
	// -------------------- story 2------------------
	public Customer createCustomer(Customer customer) {
		//story 3 - when creating an object customer.getAddress() in the if below becomes null - to handle that:
//		System.out.println(customer.getName());
//		System.out.println(customer.getAddress());
//		System.out.println(customer.getAccounts());
//		System.out.println(customer.getAccounts().get(0)); //not even being populated
		if (customer.getAddress() == null) {
            customer.setAddress(new Address());
        }
//		System.out.println(customer.getAddress());
		//case for story 2
		if (customer.getAddress().getCity() == null || customer.getAddress().getProvince() == null || customer.getAddress().getCity() == "" || customer.getAddress().getProvince() == "") {
			String url = "https://geocoder.ca/?locate=" + customer.getAddress().getPostalCode() + "&geoit=XML&json=1";

			String responseBody = this.webClient.get()
			        .uri(url)
			        .retrieve()
			        .bodyToMono(String.class)
			        .block();

			//parsing string response body in json format to json object to access values in json object - otherwise u have to do string splits.
			JSONObject response = new JSONObject(responseBody);
			
			String province = response.getJSONObject("standard").getString("prov");
			String city = response.getJSONObject("standard").getString("city");
			
			customer.getAddress().setProvince(province);
			customer.getAddress().setCity(city);;
		}
		//-------- story 5------------
//		System.out.println(customer.getAccounts());
		for (Account account: customer.getAccounts()) {
			account.setCustomer(customer);
//			System.out.println(account);
		}
		//----- story 4 addition (after populating customer object correctly) ------
		if ((customer.getCustomerType()).toLowerCase().equals("person")) {
			return customerRepo.save(new Person(customer.getName(), customer.getAddress(), customer.getAccounts())); 
		}
		else if ((customer.getCustomerType()).toLowerCase().equals("company")) {
			return customerRepo.save(new Company(customer.getName(), customer.getAddress(), customer.getAccounts())); 
		}
		else {
			throw new IllegalArgumentException("Incorrect Customer Type");
		}
}
	public Customer updateCustomer(Customer customer, long id) {
		Customer foundCustomer = customerRepo.findById(id).orElseThrow(()-> new CustomerNotFoundException("Customer with id " + id + " could not be found"));
		foundCustomer.setName(customer.getName());
		foundCustomer.getAddress().setPostalCode(customer.getAddress().getPostalCode());
		foundCustomer.getAddress().setCity(customer.getAddress().getCity());
		foundCustomer.getAddress().setProvince(customer.getAddress().getProvince());
		return customerRepo.save(foundCustomer);
	}

	public void deleteCustomerById(long id) {
		customerRepo.deleteById(id);
	}
	//--------------story 6-------------------------
	public List<Account> getAllAccountsWhereCustomerCityIsToronto() {
		 List<Customer> customersWhoseCityIsToronto = customerRepo.getCustomersByCityNameToronto("Toronto");
		 customersWhoseCityIsToronto.forEach(customer-> System.out.println(customer.getName()));
		 List<Account> allAccountsOfCustomersWhoseCityIsToronto = new ArrayList<Account>();
		 customersWhoseCityIsToronto.forEach(customer -> allAccountsOfCustomersWhoseCityIsToronto.addAll(customer.getAccounts()));
		 return allAccountsOfCustomersWhoseCityIsToronto;
		
	}
	

	
}

package com.fdmgroup.apiPack.controller;


import java.net.URI;
import java.util.List;
import java.util.Optional;

import org.springframework.http.MediaType;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;
import org.springframework.web.servlet.support.ServletUriComponentsBuilder;

import com.fdmgroup.apiPack.customexceptions.CustomerNotFoundException;
import com.fdmgroup.apiPack.model.Account;
import com.fdmgroup.apiPack.model.Customer;
import com.fdmgroup.apiPack.service.CustomerService;

import io.swagger.v3.oas.annotations.Operation;
import io.swagger.v3.oas.annotations.responses.ApiResponse;
import io.swagger.v3.oas.annotations.responses.ApiResponses;
import io.swagger.v3.oas.annotations.media.Content;

@RestController
@RequestMapping("/api/v1/customers")
public class CustomerController {
	private CustomerService customerService;

	public CustomerController(CustomerService customerService) {
		this.customerService = customerService;
	}

	@Operation(summary= "Getting all customers from the database")
	@ApiResponse(description="Returns a list of customers", responseCode = "200")
	@GetMapping("/allcustomers")
	public List<Customer> getAllCustomers(){
		return customerService.getAllCustomers();
	}
	
	@Operation(summary= "Getting a customer by id from the database")
	@ApiResponses(value = {
		    @ApiResponse(description = "Returns a customer of specified ID", responseCode = "200"),
		    @ApiResponse(description = "When customer of a specified ID doesn't exist", responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
		})
	@GetMapping("/{id}")
	public Optional<Customer> getCustomerById(@PathVariable long id){
		Optional<Customer> foundCustomer =  customerService.getCustomerById(id);
		if (!foundCustomer.isEmpty()) {
			return foundCustomer;
		}
		else {
			throw new CustomerNotFoundException("Customer with id " + id + " could not be found!"); 
		}
	}
	//--------- story 1 create ----------
	@Operation(summary= "Creates a new customer and adds them to the database")
	@ApiResponses(value = {
			@ApiResponse(description = "Customer created successfully", responseCode = "201", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Invalid input", responseCode = "500", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
	@PostMapping("/create")
	public ResponseEntity<Customer> createCustomer(@RequestBody Customer customer) {
		Customer createdCustomer =  customerService.createCustomer(customer);
		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(createdCustomer.getCustomerId()).toUri();
		return ResponseEntity.created(location).body(createdCustomer); //returns ResponseEntity with the location in header (also returns 201 Created). we can use .body() instead of .build() to return the createdCustomer obj. 
	}
	@Operation(summary= "Updates customer details in the database, if available")
	@ApiResponses(value = {
			@ApiResponse(description = "Customer updated successfully", responseCode = "200", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE)),
            @ApiResponse(description = "Customer not found", responseCode = "404", content = @Content(mediaType = MediaType.APPLICATION_JSON_VALUE))
    })
	@PutMapping("/update/{id}")
	public Customer updateCustomer(@RequestBody Customer customer, @PathVariable long id) {
		Customer updatedCustomer =  customerService.updateCustomer(customer, id);
//		URI location = ServletUriComponentsBuilder.fromCurrentRequest().path("/{id}").buildAndExpand(updatedCustomer.getCustomerId()).toUri(); //don't need it for update apparently
		return updatedCustomer;
	}
	//If the id exists, it deletes otherwise it will be ignored
	@Operation(summary= "Deletes an existing customer from the database")
	@ApiResponses(value = {
			@ApiResponse(description = "Customer deleted successfully", responseCode = "200")
    })
	@DeleteMapping("/delete/{id}")
	public void deleteCustomerById(@PathVariable long id){
		customerService.deleteCustomerById(id);
	}
	
	//-------story 6-----------
	@Operation(summary= "Retrieves all accounts where the city name is Toronto")
	@ApiResponses(value = {
			@ApiResponse(description = "Retrieves list of accounts where the city name is Toronto", responseCode = "200")
    })
	@GetMapping("/allAccountsWhereCustomerCityIsToronto")
	public List<Account> getAllAccountsWhereCustomerCityIsToronto(){
		return customerService.getAllAccountsWhereCustomerCityIsToronto();	
	}
	//works but returns 200 OK Request - need to return 201 OK for POST and PUT - use ResponseEntity Wrapper gives control over what we return
//	@PostMapping("/create")
//	public Customer createCustomer(@RequestBody Customer customer) {
//		return customerService.createCustomer(customer);
//	}
	
}

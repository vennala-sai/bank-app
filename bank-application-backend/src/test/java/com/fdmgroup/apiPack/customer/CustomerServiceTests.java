package com.fdmgroup.apiPack.customer;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.ArgumentMatchers.any;
import static org.mockito.ArgumentMatchers.anyLong;
import static org.mockito.Mockito.times;
import static org.mockito.Mockito.verify;
import static org.mockito.Mockito.when;

import java.util.Optional;

import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.springframework.web.reactive.function.client.WebClient;

import com.fdmgroup.apiPack.customexceptions.CustomerNotFoundException;
import com.fdmgroup.apiPack.model.Address;
import com.fdmgroup.apiPack.model.Customer;
import com.fdmgroup.apiPack.model.Person;
import com.fdmgroup.apiPack.repository.CustomerRepository;
import com.fdmgroup.apiPack.service.CustomerService;

@ExtendWith(MockitoExtension.class)
class CustomerServiceTests {
	
	@Mock
	CustomerRepository mockCustomerRepo;  //when customerService calls repo, it uses this mock
	
	@Mock
    WebClient webClient;
	
	CustomerService customerService; //class under test
	
	@BeforeEach
	public void setUp(){
		customerService = new CustomerService(mockCustomerRepo, webClient);
		
	}
	
	@Test
    void testGetAllCustomers() {
		//Act
        customerService.getAllCustomers();
        //Assert
        verify(mockCustomerRepo, times(1)).findAll();
    }
	
	@Test
    void testGetCustomerById() {
		//Arrange
		Address address = new Address("22", "Toronto", "ON", "A2B 37N");
		Customer customer = new Person("John Doe", address);
		customer.setCustomerId(252L);
		when(mockCustomerRepo.findById(252L)).thenReturn(Optional.of(customer));
        
		//Act
        Optional<Customer> actualResult = customerService.getCustomerById(252L);
        
        //Assert
        verify(mockCustomerRepo, times(1)).findById(252L);
        assertEquals("John Doe", actualResult.get().getName());
    }
	
	@Test
	void testDeleteById() {
		//Arrange
		Address address = new Address("22", "Toronto", "ON", "A2B 37N");
		Customer customer = new Person("John Doe", address);
		customer.setCustomerId(252L);
		//Act
        customerService.deleteCustomerById(252L);
        //Assert
        verify(mockCustomerRepo, times(1)).deleteById(252L);
	}

	@Test
	void testCreateCustomer() {
		//Arrange
		Address address = new Address("22", "Toronto", "ON", "A2B 37N");
		Customer customer = new Person("John Doe", address);

		//Act
		Customer createdOne = customerService.createCustomer(customer);
		
		//Assert
		verify(mockCustomerRepo, times(1)).save(any(Customer.class)); //any because customer is modified inside the service class - seems to have different reference than the created customer
		
	}
	
	@Test
	void testUpdateCustomer() {
		//Arrange
		Address address = new Address("22", "Toronto", "ON", "A2B 37N");
		Optional<Customer> customer = Optional.of(new Person("John Doe", address));
		customer.get().setCustomerId(252L);
		
		when(mockCustomerRepo.findById(252L)).thenReturn(customer);
		
		Address newAddress = new Address("22", "NYC", "NY", "B3A 47A");
		Customer customer2 = new Person("John Doe", newAddress); //data to be passed in
		
		//Act
		Customer updatedCustomer = customerService.updateCustomer(customer2, 252L);
		
		//Assert
//		assertEquals(updatedCustomer.getAddress().getCity(), "NYC");
		verify(mockCustomerRepo, times(1)).save(any(Customer.class));
			
	}
}

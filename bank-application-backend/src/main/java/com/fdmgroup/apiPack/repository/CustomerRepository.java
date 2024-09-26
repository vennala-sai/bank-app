package com.fdmgroup.apiPack.repository;

import java.util.List;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import com.fdmgroup.apiPack.model.Customer;

@Repository //maybe only needed for classes that implement repository functionalities?
public interface CustomerRepository extends JpaRepository<Customer, Long> {
//	@Query("SELECT c FROM Customer c WHERE c.address.city = :cityName")
	@Query("SELECT c FROM Customer c INNER JOIN c.address a WHERE a.city LIKE :cityName")
	List<Customer> getCustomersByCityNameToronto(@Param("cityName") String cityName);
}

package org.formation.repository;

import java.util.List;

import org.formation.model.Customer;
import org.springframework.data.mongodb.repository.MongoRepository;

public interface CustomerRepository extends MongoRepository<Customer, String>{

	public List<Customer> findByFirstName(String firstName);
	
	public List<Customer> findByLastName(String firstName);
}

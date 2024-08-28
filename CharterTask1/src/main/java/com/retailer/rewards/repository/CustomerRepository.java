package com.retailer.rewards.repository;

import org.springframework.data.repository.CrudRepository;

import com.retailer.rewards.entity.Customer;

/**
 * Represents Repository interface
 * 
 * @author nikhilkumarkonda
 */
public interface CustomerRepository extends CrudRepository<Customer, Long> {
	/**
	 * @param customerId
	 * @return
	 */
	public Customer findByCustomerId(Long customerId);
}
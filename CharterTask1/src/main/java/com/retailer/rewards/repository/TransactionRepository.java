package com.retailer.rewards.repository;

import java.sql.Timestamp;
import java.util.List;

import org.springframework.data.repository.CrudRepository;
import org.springframework.stereotype.Repository;

import com.retailer.rewards.entity.Transaction;

import jakarta.transaction.Transactional;

/**
 * Represents Repository interface
 * 
 * @author nikhilkumarkonda
 */
@Repository
@Transactional
public interface TransactionRepository extends CrudRepository<Transaction, Long> {

	/**
	 * @param customerId
	 * @return
	 */
	public List<Transaction> findAllByCustomerId(Long customerId);

	/**
	 * @param customerId
	 * @param from
	 * @param to
	 * @return
	 */
	public List<Transaction> findAllByCustomerIdAndTransactionDateBetween(Long customerId, Timestamp from,
			Timestamp to);
}
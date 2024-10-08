package com.retailer.rewards.service;

import java.sql.Timestamp;
import java.time.Instant;
import java.time.LocalDateTime;
import java.util.List;
import java.util.stream.Collectors;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.retailer.rewards.constants.Constants;
import com.retailer.rewards.entity.Transaction;
import com.retailer.rewards.model.Rewards;
import com.retailer.rewards.repository.TransactionRepository;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

/**
 * Represents service class
 * 
 * @author nikhilkumarkonda
 */
@Service
public class RewardsServiceImpl implements RewardsService {

	// creating a logger
	private static final Logger logger = LoggerFactory.getLogger(RewardsServiceImpl.class);

	@Autowired
	TransactionRepository transactionRepository;

	@Override
	public Rewards getRewardsByCustomerId(Long customerId) {

		logger.info("Performing an action to getRewardsByCustomerId...");

		Timestamp lastMonthTimestamp = getDateBasedOnOffSetDays(Constants.daysInMonths);
		Timestamp lastSecondMonthTimestamp = getDateBasedOnOffSetDays(2 * Constants.daysInMonths);
		Timestamp lastThirdMonthTimestamp = getDateBasedOnOffSetDays(3 * Constants.daysInMonths);

		List<Transaction> lastMonthTransactions = transactionRepository.findAllByCustomerIdAndTransactionDateBetween(
				customerId, lastMonthTimestamp, Timestamp.from(Instant.now()));
		List<Transaction> lastSecondMonthTransactions = transactionRepository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, lastSecondMonthTimestamp, lastMonthTimestamp);
		List<Transaction> lastThirdMonthTransactions = transactionRepository
				.findAllByCustomerIdAndTransactionDateBetween(customerId, lastThirdMonthTimestamp,
						lastSecondMonthTimestamp);

		Long lastMonthRewardPoints = getRewardsPerMonth(lastMonthTransactions);
		Long lastSecondMonthRewardPoints = getRewardsPerMonth(lastSecondMonthTransactions);
		Long lastThirdMonthRewardPoints = getRewardsPerMonth(lastThirdMonthTransactions);

		Rewards customerRewards = new Rewards();
		customerRewards.setCustomerId(customerId);
		customerRewards.setLastMonthRewardPoints(lastMonthRewardPoints);
		customerRewards.setLastSecondMonthRewardPoints(lastSecondMonthRewardPoints);
		customerRewards.setLastThirdMonthRewardPoints(lastThirdMonthRewardPoints);
		customerRewards
				.setTotalRewards(lastMonthRewardPoints + lastSecondMonthRewardPoints + lastThirdMonthRewardPoints);

		return customerRewards;

	}

	/**
	 * @param transactions
	 * @return
	 */
	public Long getRewardsPerMonth(List<Transaction> transactions) {
		return transactions.stream().map(transaction -> calculateRewards(transaction))
				.collect(Collectors.summingLong(r -> r.longValue()));
	}

	/**
	 * @param t
	 * @return
         * @Constants used daysInMonths = 30, firstRewardLimit = 50, secondRewardLimit = 100
	 */
	public Long calculateRewards(Transaction t) {
		if (t.getTransactionAmount() > Constants.firstRewardLimit
				&& t.getTransactionAmount() <= Constants.secondRewardLimit) {
			return Math.round(t.getTransactionAmount() - Constants.firstRewardLimit);
		} else if (t.getTransactionAmount() > Constants.secondRewardLimit) {
			return Math.round(t.getTransactionAmount() - Constants.secondRewardLimit) * 2
					+ (Constants.secondRewardLimit - Constants.firstRewardLimit);
		} else
			return 0l;

	}

	/**
	 * @param days
	 * @return
	 */
	public Timestamp getDateBasedOnOffSetDays(int days) {
		return Timestamp.valueOf(LocalDateTime.now().minusDays(days));
	}

}

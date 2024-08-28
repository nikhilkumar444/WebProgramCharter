package com.demo.CharterTask1;

import com.retailer.rewards.constants.Constants;
import com.retailer.rewards.entity.Transaction;
import com.retailer.rewards.model.Rewards;
import com.retailer.rewards.repository.TransactionRepository;
import com.retailer.rewards.service.RewardsServiceImpl;

import org.junit.jupiter.api.Test;
import org.mockito.InjectMocks;
import org.mockito.Mock;
import org.mockito.MockitoAnnotations;
import org.springframework.boot.test.context.SpringBootTest;

import java.sql.Timestamp;
import java.time.LocalDateTime;
import java.util.Arrays;
import java.util.Collections;
import java.util.List;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.mockito.Mockito.when;

/**
 * Represents Test Class
 * 
 * @author nikhilkumarkonda
 */
@SpringBootTest
public class RewardsServiceImplTest {

	@Mock
	private TransactionRepository transactionRepository;

	@InjectMocks
	private RewardsServiceImpl rewardsService;

	public RewardsServiceImplTest() {
		MockitoAnnotations.openMocks(this);
	}

	@Test
	public void testGetRewardsByCustomerId() {
		Long customerId = 1L;

		// Create mock transactions
		Transaction transaction1 = new Transaction(1L, 120.0, Timestamp.valueOf(LocalDateTime.now().minusDays(30)));
		Transaction transaction2 = new Transaction(1L, 80.0, Timestamp.valueOf(LocalDateTime.now().minusDays(60)));
		Transaction transaction3 = new Transaction(1L, 150.0, Timestamp.valueOf(LocalDateTime.now().minusDays(90)));

		// Set up mock repository behavior
		when(transactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId,
				rewardsService.getDateBasedOnOffSetDays(Constants.daysInMonths),
				Timestamp.from(java.time.Instant.now()))).thenReturn(Collections.singletonList(transaction1));

		when(transactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId,
				rewardsService.getDateBasedOnOffSetDays(2 * Constants.daysInMonths),
				rewardsService.getDateBasedOnOffSetDays(Constants.daysInMonths)))
				.thenReturn(Collections.singletonList(transaction2));

		when(transactionRepository.findAllByCustomerIdAndTransactionDateBetween(customerId,
				rewardsService.getDateBasedOnOffSetDays(3 * Constants.daysInMonths),
				rewardsService.getDateBasedOnOffSetDays(2 * Constants.daysInMonths)))
				.thenReturn(Collections.singletonList(transaction3));

		// Calculate rewards
		Rewards rewards = rewardsService.getRewardsByCustomerId(customerId);

		// Verify the results
		assertEquals(40L, rewards.getLastMonthRewardPoints());
		assertEquals(30L, rewards.getLastSecondMonthRewardPoints());
		assertEquals(100L, rewards.getLastThirdMonthRewardPoints());
		assertEquals(170L, rewards.getTotalRewards());
	}

	@Test
	public void testGetRewardsPerMonth() {
		// Create mock transactions
		Transaction transaction1 = new Transaction(1L, 120.0, Timestamp.valueOf(LocalDateTime.now()));
		Transaction transaction2 = new Transaction(1L, 80.0, Timestamp.valueOf(LocalDateTime.now()));

		List<Transaction> transactions = Arrays.asList(transaction1, transaction2);

		// Calculate rewards for the month
		Long totalRewards = rewardsService.getRewardsPerMonth(transactions);

		// Verify the result
		assertEquals(90L, totalRewards); // 20 * 2 + 30 = 90
	}

	@Test
	public void testCalculateRewards() {
		Transaction transaction1 = new Transaction(1L, 120.0, Timestamp.valueOf(LocalDateTime.now()));
		Transaction transaction2 = new Transaction(1L, 80.0, Timestamp.valueOf(LocalDateTime.now()));
		Transaction transaction3 = new Transaction(1L, 45.0, Timestamp.valueOf(LocalDateTime.now()));

		Long rewards1 = rewardsService.calculateRewards(transaction1);
		Long rewards2 = rewardsService.calculateRewards(transaction2);
		Long rewards3 = rewardsService.calculateRewards(transaction3);

		assertEquals(40L, rewards1); // (120 - 100) * 2 = 40
		assertEquals(30L, rewards2); // 80 - 50 = 30
		assertEquals(0L, rewards3); // No rewards as it's below 50
	}
}
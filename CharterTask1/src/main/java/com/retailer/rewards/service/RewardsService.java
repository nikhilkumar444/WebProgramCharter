package com.retailer.rewards.service;

import com.retailer.rewards.model.Rewards;

public interface RewardsService {
	/**
	 * @param customerId
	 * @return
	 */
	public Rewards getRewardsByCustomerId(Long customerId);
}

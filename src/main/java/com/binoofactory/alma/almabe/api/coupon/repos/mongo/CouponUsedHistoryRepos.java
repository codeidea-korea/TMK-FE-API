package com.binoofactory.alma.almabe.api.coupon.repos.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.coupon.model.docs.CouponUsedHistory;

@Repository
public interface CouponUsedHistoryRepos extends MongoRepository<CouponUsedHistory, String> {
}

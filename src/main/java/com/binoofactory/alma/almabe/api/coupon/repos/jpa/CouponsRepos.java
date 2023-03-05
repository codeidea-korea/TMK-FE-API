package com.binoofactory.alma.almabe.api.coupon.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.coupon.model.entity.Coupons;

@Repository
public interface CouponsRepos extends JpaRepository<Coupons, Long> {
}

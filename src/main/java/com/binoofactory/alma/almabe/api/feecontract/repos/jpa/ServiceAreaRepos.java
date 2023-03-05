package com.binoofactory.alma.almabe.api.feecontract.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.feecontract.model.entity.ServiceArea;

@Repository
public interface ServiceAreaRepos extends JpaRepository<ServiceArea, Long> {
}

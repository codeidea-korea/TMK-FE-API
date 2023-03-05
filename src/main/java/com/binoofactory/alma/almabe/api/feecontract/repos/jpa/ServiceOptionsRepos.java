package com.binoofactory.alma.almabe.api.feecontract.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.feecontract.model.entity.ServiceOptions;

@Repository
public interface ServiceOptionsRepos extends JpaRepository<ServiceOptions, Long> {
}

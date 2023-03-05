package com.binoofactory.alma.almabe.api.common.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.common.model.entity.Citys;

@Repository
public interface CitysRepos extends JpaRepository<Citys, Long> {
}

package com.binoofactory.alma.almabe.api.user.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.user.model.entity.TravelCorps;

@Repository
public interface TravelCorpsRepos extends JpaRepository<TravelCorps, Long> {
}

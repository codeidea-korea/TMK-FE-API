package com.binoofactory.alma.almabe.api.reservation.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.reservation.model.entity.Branchs;

@Repository
public interface BranchsRepos extends JpaRepository<Branchs, Long> {
}

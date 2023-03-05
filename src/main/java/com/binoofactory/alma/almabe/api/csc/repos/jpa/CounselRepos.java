package com.binoofactory.alma.almabe.api.csc.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.csc.model.Counsel;

@Repository
public interface CounselRepos extends JpaRepository<Counsel, Long> {
}

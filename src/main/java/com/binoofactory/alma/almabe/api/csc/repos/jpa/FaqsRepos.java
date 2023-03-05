package com.binoofactory.alma.almabe.api.csc.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.csc.model.Faqs;

@Repository
public interface FaqsRepos extends JpaRepository<Faqs, Long> {
}

package com.binoofactory.alma.almabe.api.csc.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.csc.model.OneonOneCounsel;

@Repository
public interface OneonOneCounselRepos extends JpaRepository<OneonOneCounsel, Long> {
}

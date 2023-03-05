package com.binoofactory.alma.almabe.api.cms.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.entity.MainContents;

@Repository
public interface MainContentsRepos extends JpaRepository<MainContents, Long> {
}

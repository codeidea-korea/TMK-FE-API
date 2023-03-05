package com.binoofactory.alma.almabe.api.user.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.user.model.entity.UserAuth;

@Repository
public interface UserAuthRepos extends JpaRepository<UserAuth, Long> {
}

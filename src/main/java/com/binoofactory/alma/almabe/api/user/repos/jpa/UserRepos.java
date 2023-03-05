package com.binoofactory.alma.almabe.api.user.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.user.model.entity.Users;

@Repository
public interface UserRepos extends JpaRepository<Users, Long> {
    Users findByUserId(String userId);
}

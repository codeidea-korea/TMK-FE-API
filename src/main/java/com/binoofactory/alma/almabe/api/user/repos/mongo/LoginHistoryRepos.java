package com.binoofactory.alma.almabe.api.user.repos.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.user.model.docs.LoginHistory;

@Repository
public interface LoginHistoryRepos extends MongoRepository<LoginHistory, String> {
}

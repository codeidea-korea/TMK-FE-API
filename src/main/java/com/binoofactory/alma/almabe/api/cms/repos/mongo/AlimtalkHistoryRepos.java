package com.binoofactory.alma.almabe.api.cms.repos.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.docs.AlimtalkHistory;

@Repository
public interface AlimtalkHistoryRepos extends MongoRepository<AlimtalkHistory, String> {
}

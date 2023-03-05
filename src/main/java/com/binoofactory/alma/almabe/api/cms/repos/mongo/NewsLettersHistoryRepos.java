package com.binoofactory.alma.almabe.api.cms.repos.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.docs.NewsLettersHistory;

@Repository
public interface NewsLettersHistoryRepos extends MongoRepository<NewsLettersHistory, String> {
}

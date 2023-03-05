package com.binoofactory.alma.almabe.api.notification.repos.mongo;

import org.springframework.data.mongodb.repository.MongoRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.notification.model.docs.NotificationHistory;

@Repository
public interface NotificationHistoryRepos extends MongoRepository<NotificationHistory, String> {
}

package com.binoofactory.alma.almabe.api.notification.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.notification.model.entity.Notifications;

@Repository
public interface NotificationsRepos extends JpaRepository<Notifications, Long> {
}

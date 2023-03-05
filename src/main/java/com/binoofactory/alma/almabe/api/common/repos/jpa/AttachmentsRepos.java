package com.binoofactory.alma.almabe.api.common.repos.jpa;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.common.model.entity.Attachments;

@Repository
public interface AttachmentsRepos extends JpaRepository<Attachments, Long> {
}

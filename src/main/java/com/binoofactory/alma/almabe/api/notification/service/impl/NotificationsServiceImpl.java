package com.binoofactory.alma.almabe.api.notification.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.notification.model.entity.Notifications;
import com.binoofactory.alma.almabe.api.notification.repos.jpa.NotificationsRepos;
import com.binoofactory.alma.almabe.api.notification.repos.jpa.dsl.NotificationsDslRepos;
import com.binoofactory.alma.almabe.api.notification.service.NotificationsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class NotificationsServiceImpl implements NotificationsService {

    private final NotificationsRepos repos;

    private final NotificationsDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public NotificationsServiceImpl(NotificationsRepos repos, NotificationsDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Notifications add(Notifications instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Notifications saved = repos.save(generate(instance));
        return saved;
    }

    private Notifications findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Notifications edit(Notifications instance, HttpServletRequest httpServletRequest) throws Exception {
        Notifications saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Notifications instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Notifications find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Notifications instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Notifications> findAll(Notifications instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<Notifications> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public Notifications generate(Notifications instance) {
        instance.setCreatedAt(instance.getCreatedAt() == null ? dateUtil.getThisTime() : instance.getCreatedAt());
        return instance;
    }
}

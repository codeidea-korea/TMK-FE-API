package com.binoofactory.alma.almabe.api.notification.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binoofactory.alma.almabe.api.notification.model.docs.NotificationHistory;
import com.binoofactory.alma.almabe.api.notification.repos.mongo.NotificationHistoryRepos;
import com.binoofactory.alma.almabe.api.notification.service.NotificationHistoryService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class NotificationHistoryServiceImpl implements NotificationHistoryService {

    private final NotificationHistoryRepos repos;

    private final DateUtil dateUtil;

    @Autowired
    public NotificationHistoryServiceImpl(NotificationHistoryRepos repos, DateUtil dateUtil) {
        this.repos = repos;
        this.dateUtil = dateUtil;
    }

    @Override
    public NotificationHistory add(NotificationHistory instance, HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        //        NotificationHistory saved = repos.save(generate(instance));
        //        return saved;
        return instance;
    }

    private NotificationHistory findById(long id) {
        //        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
        return null;
    }

    @Override
    public NotificationHistory edit(NotificationHistory instance, HttpServletRequest httpServletRequest)
        throws Exception {
        //        NotificationHistory saved = repos.save(generate(instance));
        //        return saved;
        return instance;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        NotificationHistory instance = findById(seq);
        //        repos.delete(instance);
    }

    @Override
    public NotificationHistory find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        NotificationHistory instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<NotificationHistory> findAll(NotificationHistory instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NotificationHistory generate(NotificationHistory instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

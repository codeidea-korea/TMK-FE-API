package com.binoofactory.alma.almabe.api.cms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.cms.model.entity.Push;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.PushRepos;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.PushDslRepos;
import com.binoofactory.alma.almabe.api.cms.service.PushService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class PushServiceImpl implements PushService {

    private final PushRepos repos;

    private final PushDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public PushServiceImpl(PushRepos repos, PushDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Push add(Push instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Push saved = repos.save(generate(instance));
        return saved;
    }

    private Push findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Push edit(Push instance, HttpServletRequest httpServletRequest) throws Exception {
        Push saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Push instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Push find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Push instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Push> findAll(Push instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<Push> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public Push generate(Push instance) {
        instance.setCreatedAt(instance.getCreatedAt() == null ? dateUtil.getThisTime() : instance.getCreatedAt());
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        return instance;
    }
}

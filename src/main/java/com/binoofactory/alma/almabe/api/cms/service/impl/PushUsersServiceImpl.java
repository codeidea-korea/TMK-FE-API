package com.binoofactory.alma.almabe.api.cms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.cms.model.entity.PushUsers;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.PushUsersRepos;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.PushUsersDslRepos;
import com.binoofactory.alma.almabe.api.cms.service.PushUsersService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class PushUsersServiceImpl implements PushUsersService {

    private final PushUsersRepos repos;

    private final PushUsersDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public PushUsersServiceImpl(PushUsersRepos repos, PushUsersDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public PushUsers add(PushUsers instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        PushUsers saved = repos.save(generate(instance));
        return saved;
    }

    private PushUsers findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public PushUsers edit(PushUsers instance, HttpServletRequest httpServletRequest) throws Exception {
        PushUsers saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        PushUsers instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public PushUsers find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        PushUsers instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<PushUsers> findAll(PushUsers instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<PushUsers> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public PushUsers generate(PushUsers instance) {
        return instance;
    }
}

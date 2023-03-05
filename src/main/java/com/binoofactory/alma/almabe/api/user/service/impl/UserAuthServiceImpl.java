package com.binoofactory.alma.almabe.api.user.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.cms.model.entity.ErrorBoard;
import com.binoofactory.alma.almabe.api.user.model.entity.UserAuth;
import com.binoofactory.alma.almabe.api.user.repos.jpa.UserAuthRepos;
import com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.UserAuthDslRepos;
import com.binoofactory.alma.almabe.api.user.service.UserAuthService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class UserAuthServiceImpl implements UserAuthService {

    private final UserAuthRepos repos;

    private final UserAuthDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public UserAuthServiceImpl(UserAuthRepos repos, UserAuthDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public UserAuth add(UserAuth instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        UserAuth saved = repos.save(generate(instance));
        return saved;
    }

    private UserAuth findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public UserAuth edit(UserAuth instance, HttpServletRequest httpServletRequest) throws Exception {
        UserAuth saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        UserAuth instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public UserAuth find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        UserAuth instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<UserAuth> findAll(UserAuth instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<UserAuth> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public UserAuth generate(UserAuth instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

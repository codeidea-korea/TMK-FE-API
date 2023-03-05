package com.binoofactory.alma.almabe.api.user.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.user.model.entity.Auth;
import com.binoofactory.alma.almabe.api.user.repos.jpa.AuthRepos;
import com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.AuthDslRepos;
import com.binoofactory.alma.almabe.api.user.service.AuthService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class AuthServiceImpl implements AuthService {

    private final AuthRepos repos;

    private final AuthDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public AuthServiceImpl(AuthRepos repos, AuthDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Auth add(Auth instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Auth saved = repos.save(generate(instance));
        return saved;
    }

    private Auth findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Auth edit(Auth instance, HttpServletRequest httpServletRequest) throws Exception {
        Auth saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Auth instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Auth find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Auth instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Auth> findAll(Auth instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<Auth> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public Auth generate(Auth instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

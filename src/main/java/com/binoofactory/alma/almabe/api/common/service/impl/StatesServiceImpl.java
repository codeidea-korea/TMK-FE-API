package com.binoofactory.alma.almabe.api.common.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.common.model.entity.States;
import com.binoofactory.alma.almabe.api.common.repos.jpa.StatesRepos;
import com.binoofactory.alma.almabe.api.common.service.StatesService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class StatesServiceImpl implements StatesService {

    private final StatesRepos repos;

    private final DateUtil dateUtil;

    @Autowired
    public StatesServiceImpl(StatesRepos repos, DateUtil dateUtil) {
        this.repos = repos;
        this.dateUtil = dateUtil;
    }

    @Override
    public States add(States instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        States saved = repos.save(generate(instance));
        return saved;
    }
    
    private States findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public States edit(States instance, HttpServletRequest httpServletRequest) throws Exception {
        States saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        States instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public States find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        States instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<States> findAll(States instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        return new BfListResponse(repos.findAll(bfPage.generatePageable()).getContent(), repos.count(), bfPage);
    }

    @Override
    public States generate(States instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

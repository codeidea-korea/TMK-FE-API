package com.binoofactory.alma.almabe.api.common.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.common.model.entity.Citys;
import com.binoofactory.alma.almabe.api.common.repos.jpa.CitysRepos;
import com.binoofactory.alma.almabe.api.common.service.CitysService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class CitysServiceImpl implements CitysService {

    private final CitysRepos repos;

    private final DateUtil dateUtil;

    @Autowired
    public CitysServiceImpl(CitysRepos repos, DateUtil dateUtil) {
        this.repos = repos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Citys add(Citys instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Citys saved = repos.save(generate(instance));
        return saved;
    }

    private Citys findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Citys edit(Citys instance, HttpServletRequest httpServletRequest) throws Exception {
        Citys saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Citys instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Citys find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Citys instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Citys> findAll(Citys instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        return new BfListResponse(repos.findAll(bfPage.generatePageable()).getContent(), repos.count(), bfPage);
    }

    @Override
    public Citys generate(Citys instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

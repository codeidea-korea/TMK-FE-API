package com.binoofactory.alma.almabe.api.common.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.common.model.entity.NationalAuthems;
import com.binoofactory.alma.almabe.api.common.repos.jpa.NationalAuthemsRepos;
import com.binoofactory.alma.almabe.api.common.service.NationalAuthemsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class NationalAuthemsServiceImpl implements NationalAuthemsService {

    private final NationalAuthemsRepos repos;

    private final DateUtil dateUtil;

    @Autowired
    public NationalAuthemsServiceImpl(NationalAuthemsRepos repos, DateUtil dateUtil) {
        this.repos = repos;
        this.dateUtil = dateUtil;
    }

    @Override
    public NationalAuthems add(NationalAuthems instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        NationalAuthems saved = repos.save(generate(instance));
        return saved;
    }

    private NationalAuthems findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public NationalAuthems edit(NationalAuthems instance, HttpServletRequest httpServletRequest) throws Exception {
        NationalAuthems saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        NationalAuthems instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public NationalAuthems find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        NationalAuthems instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<NationalAuthems> findAll(NationalAuthems instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        return new BfListResponse(repos.findAll(bfPage.generatePageable()).getContent(), repos.count(), bfPage);
    }

    @Override
    public NationalAuthems generate(NationalAuthems instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

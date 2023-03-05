package com.binoofactory.alma.almabe.api.user.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.user.model.entity.TravelCorps;
import com.binoofactory.alma.almabe.api.user.repos.jpa.TravelCorpsRepos;
import com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.TravelCorpsDslRepos;
import com.binoofactory.alma.almabe.api.user.service.TravelCorpsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class TravelCorpsServiceImpl implements TravelCorpsService {

    private final TravelCorpsRepos repos;

    private final TravelCorpsDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public TravelCorpsServiceImpl(TravelCorpsRepos repos, TravelCorpsDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public TravelCorps add(TravelCorps instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        TravelCorps saved = repos.save(generate(instance));
        return saved;
    }

    private TravelCorps findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public TravelCorps edit(TravelCorps instance, HttpServletRequest httpServletRequest) throws Exception {
        TravelCorps saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        TravelCorps instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public TravelCorps find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        TravelCorps instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<TravelCorps> findAll(TravelCorps instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<TravelCorps> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public TravelCorps generate(TravelCorps instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

package com.binoofactory.alma.almabe.api.user.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.user.model.entity.CorpDetails;
import com.binoofactory.alma.almabe.api.user.repos.jpa.CorpDetailsRepos;
import com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.CorpDetailsDslRepos;
import com.binoofactory.alma.almabe.api.user.service.CorpDetailsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class CorpDetailsServiceImpl implements CorpDetailsService {

    private final CorpDetailsRepos repos;

    private final CorpDetailsDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public CorpDetailsServiceImpl(CorpDetailsRepos repos, CorpDetailsDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public CorpDetails add(CorpDetails instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        CorpDetails saved = repos.save(generate(instance));
        return saved;
    }

    private CorpDetails findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public CorpDetails edit(CorpDetails instance, HttpServletRequest httpServletRequest) throws Exception {
        CorpDetails saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        CorpDetails instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public CorpDetails find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        CorpDetails instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<CorpDetails> findAll(CorpDetails instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<CorpDetails> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public CorpDetails generate(CorpDetails instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

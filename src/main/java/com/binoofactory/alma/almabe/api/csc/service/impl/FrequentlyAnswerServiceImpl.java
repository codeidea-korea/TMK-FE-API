package com.binoofactory.alma.almabe.api.csc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.csc.model.FrequentlyAnswer;
import com.binoofactory.alma.almabe.api.csc.repos.jpa.FrequentlyAnswerRepos;
import com.binoofactory.alma.almabe.api.csc.repos.jpa.dsl.FrequentlyAnswerDslRepos;
import com.binoofactory.alma.almabe.api.csc.service.FrequentlyAnswerService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class FrequentlyAnswerServiceImpl implements FrequentlyAnswerService {

    private final FrequentlyAnswerRepos repos;

    private final FrequentlyAnswerDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public FrequentlyAnswerServiceImpl(FrequentlyAnswerRepos repos, FrequentlyAnswerDslRepos dslRepos,
        DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public FrequentlyAnswer add(FrequentlyAnswer instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        FrequentlyAnswer saved = repos.save(generate(instance));
        return saved;
    }

    private FrequentlyAnswer findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public FrequentlyAnswer edit(FrequentlyAnswer instance, HttpServletRequest httpServletRequest) throws Exception {
        FrequentlyAnswer saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        FrequentlyAnswer instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public FrequentlyAnswer find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        FrequentlyAnswer instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<FrequentlyAnswer> findAll(FrequentlyAnswer instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<FrequentlyAnswer> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public FrequentlyAnswer generate(FrequentlyAnswer instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        return instance;
    }
}

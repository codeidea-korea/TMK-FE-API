package com.binoofactory.alma.almabe.api.cms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.cms.model.entity.Terms;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.TermsRepos;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.TermsDslRepos;
import com.binoofactory.alma.almabe.api.cms.service.TermsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class TermsServiceImpl implements TermsService {

    private final TermsRepos repos;

    private final TermsDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public TermsServiceImpl(TermsRepos repos, TermsDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Terms add(Terms instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Terms saved = repos.save(generate(instance));
        return saved;
    }

    private Terms findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Terms edit(Terms instance, HttpServletRequest httpServletRequest) throws Exception {
        Terms saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Terms instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Terms find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Terms instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Terms> findAll(Terms instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<Terms> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public Terms generate(Terms instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        return instance;
    }
}

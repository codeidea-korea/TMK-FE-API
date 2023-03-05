package com.binoofactory.alma.almabe.api.csc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.csc.model.Counsel;
import com.binoofactory.alma.almabe.api.csc.repos.jpa.CounselRepos;
import com.binoofactory.alma.almabe.api.csc.repos.jpa.dsl.CounselDslRepos;
import com.binoofactory.alma.almabe.api.csc.service.CounselService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class CounselServiceImpl implements CounselService {

    private final CounselRepos repos;

    private final CounselDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public CounselServiceImpl(CounselRepos repos, CounselDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Counsel add(Counsel instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Counsel saved = repos.save(generate(instance));
        return saved;
    }

    private Counsel findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Counsel edit(Counsel instance, HttpServletRequest httpServletRequest) throws Exception {
        Counsel saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Counsel instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Counsel find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Counsel instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Counsel> findAll(Counsel instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<Counsel> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public Counsel generate(Counsel instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        instance.setCreatedAt(instance.getCreatedAt() == null ? dateUtil.getThisTime() : instance.getCreatedAt());
        return instance;
    }
}

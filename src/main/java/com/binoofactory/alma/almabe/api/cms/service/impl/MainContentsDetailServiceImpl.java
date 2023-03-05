package com.binoofactory.alma.almabe.api.cms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.cms.model.entity.MainContentsDetail;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.MainContentsDetailRepos;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.MainContentsDetailDslRepos;
import com.binoofactory.alma.almabe.api.cms.service.MainContentsDetailService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class MainContentsDetailServiceImpl implements MainContentsDetailService {

    private final MainContentsDetailRepos repos;

    private final MainContentsDetailDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public MainContentsDetailServiceImpl(MainContentsDetailRepos repos, MainContentsDetailDslRepos dslRepos,
        DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public MainContentsDetail add(MainContentsDetail instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        MainContentsDetail saved = repos.save(generate(instance));
        return saved;
    }

    private MainContentsDetail findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public MainContentsDetail edit(MainContentsDetail instance, HttpServletRequest httpServletRequest)
        throws Exception {
        MainContentsDetail saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        MainContentsDetail instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public MainContentsDetail find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        MainContentsDetail instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<MainContentsDetail> findAll(MainContentsDetail instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<MainContentsDetail> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public MainContentsDetail generate(MainContentsDetail instance) {
        instance.setEfectiveEndedAt(instance.getEfectiveEndedAt() == null ? dateUtil.getLastTime() : instance.getEfectiveEndedAt());
        instance.setEfectiveStartedAt(instance.getEfectiveStartedAt() == null ? dateUtil.getThisTime() : instance.getEfectiveStartedAt());
        return instance;
    }
}

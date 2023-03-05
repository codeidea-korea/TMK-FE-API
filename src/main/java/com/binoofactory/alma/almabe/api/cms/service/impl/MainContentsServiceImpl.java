package com.binoofactory.alma.almabe.api.cms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.cms.model.entity.MainContents;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.MainContentsRepos;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.MainContentsDslRepos;
import com.binoofactory.alma.almabe.api.cms.service.MainContentsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class MainContentsServiceImpl implements MainContentsService {

    private final MainContentsRepos repos;

    private final MainContentsDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public MainContentsServiceImpl(MainContentsRepos repos, MainContentsDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public MainContents add(MainContents instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        MainContents saved = repos.save(generate(instance));
        return saved;
    }

    private MainContents findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public MainContents edit(MainContents instance, HttpServletRequest httpServletRequest) throws Exception {
        MainContents saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        MainContents instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public MainContents find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        MainContents instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<MainContents> findAll(MainContents instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<MainContents> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public MainContents generate(MainContents instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        return instance;
    }
}

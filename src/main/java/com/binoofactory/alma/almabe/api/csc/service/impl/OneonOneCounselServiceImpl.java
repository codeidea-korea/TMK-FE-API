package com.binoofactory.alma.almabe.api.csc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.csc.model.OneonOneCounsel;
import com.binoofactory.alma.almabe.api.csc.repos.jpa.OneonOneCounselRepos;
import com.binoofactory.alma.almabe.api.csc.repos.jpa.dsl.OneonOneCounselDslRepos;
import com.binoofactory.alma.almabe.api.csc.service.OneonOneCounselService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class OneonOneCounselServiceImpl implements OneonOneCounselService {

    private final OneonOneCounselRepos repos;

    private final OneonOneCounselDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public OneonOneCounselServiceImpl(OneonOneCounselRepos repos, OneonOneCounselDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public OneonOneCounsel add(OneonOneCounsel instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        OneonOneCounsel saved = repos.save(generate(instance));
        return saved;
    }

    private OneonOneCounsel findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public OneonOneCounsel edit(OneonOneCounsel instance, HttpServletRequest httpServletRequest) throws Exception {
        OneonOneCounsel saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        OneonOneCounsel instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public OneonOneCounsel find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        OneonOneCounsel instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<OneonOneCounsel> findAll(OneonOneCounsel instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<OneonOneCounsel> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public OneonOneCounsel generate(OneonOneCounsel instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        instance.setCreatedAt(instance.getCreatedAt() == null ? dateUtil.getThisTime() : instance.getCreatedAt());
        return instance;
    }
}

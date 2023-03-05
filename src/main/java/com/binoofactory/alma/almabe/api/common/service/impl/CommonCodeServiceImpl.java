package com.binoofactory.alma.almabe.api.common.service.impl;

import javax.servlet.http.HttpServletRequest;
import javax.transaction.Transactional;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.common.model.entity.CommonCode;
import com.binoofactory.alma.almabe.api.common.repos.jpa.CommonCodeRepos;
import com.binoofactory.alma.almabe.api.common.service.CommonCodeService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class CommonCodeServiceImpl implements CommonCodeService {

    private final CommonCodeRepos repos;

    private final DateUtil dateUtil;

    @Autowired
    public CommonCodeServiceImpl(CommonCodeRepos repos, DateUtil dateUtil) {
        this.repos = repos;
        this.dateUtil = dateUtil;
    }

    @Transactional
    @Override
    public CommonCode add(CommonCode instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        CommonCode saved = repos.save(generate(instance));
        return saved;
    }

    private CommonCode findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public CommonCode edit(CommonCode instance, HttpServletRequest httpServletRequest) throws Exception {
        CommonCode saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        CommonCode instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public CommonCode find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        CommonCode instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<CommonCode> findAll(CommonCode instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        return new BfListResponse(repos.findAll(bfPage.generatePageable()).getContent(), repos.count(), bfPage);
    }

    @Override
    public CommonCode generate(CommonCode instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        instance.setCreatedAt(instance.getCreatedAt() == null ? dateUtil.getThisTime() : instance.getCreatedAt());
        return instance;
    }
}

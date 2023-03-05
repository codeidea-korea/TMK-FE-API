package com.binoofactory.alma.almabe.api.common.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.common.model.entity.CommonGroupCode;
import com.binoofactory.alma.almabe.api.common.repos.jpa.CommonGroupCodeRepos;
import com.binoofactory.alma.almabe.api.common.service.CommonGroupCodeService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class CommonGroupCodeServiceImpl implements CommonGroupCodeService {

    private final CommonGroupCodeRepos repos;

    private final DateUtil dateUtil;

    @Autowired
    public CommonGroupCodeServiceImpl(CommonGroupCodeRepos repos, DateUtil dateUtil) {
        this.repos = repos;
        this.dateUtil = dateUtil;
    }

    @Override
    public CommonGroupCode add(CommonGroupCode instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        CommonGroupCode saved = repos.save(generate(instance));
        return saved;
    }
    
    private CommonGroupCode findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public CommonGroupCode edit(CommonGroupCode instance, HttpServletRequest httpServletRequest) throws Exception {
        CommonGroupCode saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        CommonGroupCode instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public CommonGroupCode find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        CommonGroupCode instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<CommonGroupCode> findAll(CommonGroupCode instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        return new BfListResponse(repos.findAll(bfPage.generatePageable()).getContent(), repos.count(), bfPage);
    }

    @Override
    public CommonGroupCode generate(CommonGroupCode instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        instance.setCreatedAt(instance.getCreatedAt() == null ? dateUtil.getThisTime() : instance.getCreatedAt());
        return instance;
    }
}

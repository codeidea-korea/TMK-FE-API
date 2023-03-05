package com.binoofactory.alma.almabe.api.cms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.cms.model.entity.ErrorBoard;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.ErrorBoardRepos;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.ErrorBoardDslRepos;
import com.binoofactory.alma.almabe.api.cms.service.ErrorBoardService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class ErrorBoardServiceImpl implements ErrorBoardService {

    private final ErrorBoardDslRepos dslRepos;

    private final ErrorBoardRepos repos;

    private final DateUtil dateUtil;

    @Autowired
    public ErrorBoardServiceImpl(ErrorBoardRepos repos, ErrorBoardDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public ErrorBoard add(ErrorBoard instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        ErrorBoard saved = repos.save(generate(instance));
        return saved;
    }
    
    private ErrorBoard findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public ErrorBoard edit(ErrorBoard instance, HttpServletRequest httpServletRequest) throws Exception {
        ErrorBoard saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        ErrorBoard instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public ErrorBoard find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        ErrorBoard instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<ErrorBoard> findAll(ErrorBoard instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {
        
        List<ErrorBoard> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);
        
        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public ErrorBoard generate(ErrorBoard instance) {
        // TODO Auto-generated method stub
        instance.setCreatedAt(instance.getCreatedAt() == null ? dateUtil.getThisTime() : instance.getCreatedAt());
        return instance;
    }
}

package com.binoofactory.alma.almabe.api.cms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.cms.model.entity.Notice;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.NoticeRepos;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.NoticeDslRepos;
import com.binoofactory.alma.almabe.api.cms.service.NoticeService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class NoticeServiceImpl implements NoticeService {

    private final NoticeRepos repos;
    
    private final NoticeDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public NoticeServiceImpl(NoticeRepos repos, NoticeDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Notice add(Notice instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Notice saved = repos.save(generate(instance));
        return saved;
    }
    
    private Notice findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Notice edit(Notice instance, HttpServletRequest httpServletRequest) throws Exception {
        Notice saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Notice instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Notice find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Notice instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Notice> findAll(Notice instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<Notice> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);
        
        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public Notice generate(Notice instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        return instance;
    }
}

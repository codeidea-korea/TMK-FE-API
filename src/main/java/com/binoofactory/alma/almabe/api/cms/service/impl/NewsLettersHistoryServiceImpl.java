package com.binoofactory.alma.almabe.api.cms.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binoofactory.alma.almabe.api.cms.model.docs.NewsLettersHistory;
import com.binoofactory.alma.almabe.api.cms.repos.mongo.NewsLettersHistoryRepos;
import com.binoofactory.alma.almabe.api.cms.service.NewsLettersHistoryService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class NewsLettersHistoryServiceImpl implements NewsLettersHistoryService {

    private final NewsLettersHistoryRepos repos;

    private final DateUtil dateUtil;

    @Autowired
    public NewsLettersHistoryServiceImpl(NewsLettersHistoryRepos repos, DateUtil dateUtil) {
        this.repos = repos;
        this.dateUtil = dateUtil;
    }

    @Override
    public NewsLettersHistory add(NewsLettersHistory instance, HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        //        NewsLettersHistory saved = repos.save(generate(instance));
        //        return saved;
        return instance;
    }

    private NewsLettersHistory findById(long id) {
        //        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
        return null;
    }

    @Override
    public NewsLettersHistory edit(NewsLettersHistory instance, HttpServletRequest httpServletRequest)
        throws Exception {
        //        NewsLettersHistory saved = repos.save(generate(instance));
        //        return saved;
        return instance;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        NewsLettersHistory instance = findById(seq);
        //        repos.delete(instance);
    }

    @Override
    public NewsLettersHistory find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        NewsLettersHistory instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<NewsLettersHistory> findAll(NewsLettersHistory instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public NewsLettersHistory generate(NewsLettersHistory instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

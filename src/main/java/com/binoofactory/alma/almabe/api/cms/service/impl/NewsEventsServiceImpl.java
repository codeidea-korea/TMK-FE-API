package com.binoofactory.alma.almabe.api.cms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.cms.model.entity.NewsEvents;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.NewsEventsRepos;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.NewsEventsDslRepos;
import com.binoofactory.alma.almabe.api.cms.service.NewsEventsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class NewsEventsServiceImpl implements NewsEventsService {

    private final NewsEventsRepos repos;

    private final NewsEventsDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public NewsEventsServiceImpl(NewsEventsRepos repos, NewsEventsDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public NewsEvents add(NewsEvents instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        NewsEvents saved = repos.save(generate(instance));
        return saved;
    }

    private NewsEvents findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public NewsEvents edit(NewsEvents instance, HttpServletRequest httpServletRequest) throws Exception {
        NewsEvents saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        NewsEvents instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public NewsEvents find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        NewsEvents instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<NewsEvents> findAll(NewsEvents instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<NewsEvents> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public NewsEvents generate(NewsEvents instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        return instance;
    }
}

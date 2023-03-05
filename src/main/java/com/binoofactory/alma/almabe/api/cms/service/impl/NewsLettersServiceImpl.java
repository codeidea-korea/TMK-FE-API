package com.binoofactory.alma.almabe.api.cms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.cms.model.entity.NewsLetters;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.NewsLettersRepos;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.NewsLettersDslRepos;
import com.binoofactory.alma.almabe.api.cms.service.NewsLettersService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class NewsLettersServiceImpl implements NewsLettersService {

    private final NewsLettersRepos repos;

    private final NewsLettersDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public NewsLettersServiceImpl(NewsLettersRepos repos, NewsLettersDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public NewsLetters add(NewsLetters instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        NewsLetters saved = repos.save(generate(instance));
        return saved;
    }

    private NewsLetters findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public NewsLetters edit(NewsLetters instance, HttpServletRequest httpServletRequest) throws Exception {
        NewsLetters saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        NewsLetters instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public NewsLetters find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        NewsLetters instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<NewsLetters> findAll(NewsLetters instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<NewsLetters> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public NewsLetters generate(NewsLetters instance) {
        instance.setCreatedAt(instance.getCreatedAt() == null ? dateUtil.getThisTime() : instance.getCreatedAt());
        return instance;
    }
}

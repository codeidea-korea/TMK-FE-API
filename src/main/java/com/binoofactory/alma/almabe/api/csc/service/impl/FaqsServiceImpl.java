package com.binoofactory.alma.almabe.api.csc.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.csc.model.Faqs;
import com.binoofactory.alma.almabe.api.csc.repos.jpa.FaqsRepos;
import com.binoofactory.alma.almabe.api.csc.repos.jpa.dsl.FaqsDslRepos;
import com.binoofactory.alma.almabe.api.csc.service.FaqsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class FaqsServiceImpl implements FaqsService {

    private final FaqsRepos repos;
    
    private final FaqsDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public FaqsServiceImpl(FaqsRepos repos, FaqsDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Faqs add(Faqs instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Faqs saved = repos.save(generate(instance));
        return saved;
    }
    
    private Faqs findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Faqs edit(Faqs instance, HttpServletRequest httpServletRequest) throws Exception {
        Faqs saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Faqs instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Faqs find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Faqs instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Faqs> findAll(Faqs instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {
        
        List<Faqs> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);
        
        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public Faqs generate(Faqs instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        instance.setCreatedAt(instance.getCreatedAt() == null ? dateUtil.getThisTime() : instance.getCreatedAt());
        return instance;
    }
}

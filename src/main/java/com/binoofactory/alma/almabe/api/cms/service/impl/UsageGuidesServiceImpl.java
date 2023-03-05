package com.binoofactory.alma.almabe.api.cms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.cms.model.entity.UsageGuides;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.UsageGuidesRepos;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.UsageGuidesDslRepos;
import com.binoofactory.alma.almabe.api.cms.service.UsageGuidesService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class UsageGuidesServiceImpl implements UsageGuidesService {

    private final UsageGuidesRepos repos;

    private final UsageGuidesDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public UsageGuidesServiceImpl(UsageGuidesRepos repos, UsageGuidesDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public UsageGuides add(UsageGuides instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        UsageGuides saved = repos.save(generate(instance));
        return saved;
    }

    private UsageGuides findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public UsageGuides edit(UsageGuides instance, HttpServletRequest httpServletRequest) throws Exception {
        UsageGuides saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        UsageGuides instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public UsageGuides find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        UsageGuides instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<UsageGuides> findAll(UsageGuides instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<UsageGuides> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public UsageGuides generate(UsageGuides instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        return instance;
    }
}

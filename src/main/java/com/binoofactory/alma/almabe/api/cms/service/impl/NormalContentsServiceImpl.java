package com.binoofactory.alma.almabe.api.cms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.cms.model.entity.NormalContents;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.NormalContentsRepos;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.NormalContentsDslRepos;
import com.binoofactory.alma.almabe.api.cms.service.NormalContentsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class NormalContentsServiceImpl implements NormalContentsService {

    private final NormalContentsRepos repos;

    private final NormalContentsDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public NormalContentsServiceImpl(NormalContentsRepos repos, NormalContentsDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public NormalContents add(NormalContents instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        NormalContents saved = repos.save(generate(instance));
        return saved;
    }

    private NormalContents findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public NormalContents edit(NormalContents instance, HttpServletRequest httpServletRequest) throws Exception {
        NormalContents saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        NormalContents instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public NormalContents find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        NormalContents instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<NormalContents> findAll(NormalContents instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<NormalContents> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public NormalContents generate(NormalContents instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        return instance;
    }
}

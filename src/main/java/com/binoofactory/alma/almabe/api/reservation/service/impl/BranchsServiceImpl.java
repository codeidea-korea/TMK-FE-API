package com.binoofactory.alma.almabe.api.reservation.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.reservation.model.entity.Branchs;
import com.binoofactory.alma.almabe.api.reservation.repos.jpa.BranchsRepos;
import com.binoofactory.alma.almabe.api.reservation.repos.jpa.dsl.BranchsDslRepos;
import com.binoofactory.alma.almabe.api.reservation.service.BranchsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class BranchsServiceImpl implements BranchsService {

    private final BranchsRepos repos;

    private final BranchsDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public BranchsServiceImpl(BranchsRepos repos, BranchsDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Branchs add(Branchs instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Branchs saved = repos.save(generate(instance));
        return saved;
    }

    private Branchs findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Branchs edit(Branchs instance, HttpServletRequest httpServletRequest) throws Exception {
        Branchs saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Branchs instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Branchs find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Branchs instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Branchs> findAll(Branchs instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<Branchs> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public Branchs generate(Branchs instance) {
        return instance;
    }
}

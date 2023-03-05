package com.binoofactory.alma.almabe.api.feecontract.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.feecontract.model.entity.Services;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.ServicesRepos;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.ServicesDslRepos;
import com.binoofactory.alma.almabe.api.feecontract.service.ServicesService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class ServicesServiceImpl implements ServicesService {

    private final ServicesRepos repos;

    private final ServicesDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public ServicesServiceImpl(ServicesRepos repos, ServicesDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Services add(Services instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Services saved = repos.save(generate(instance));
        return saved;
    }

    private Services findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Services edit(Services instance, HttpServletRequest httpServletRequest) throws Exception {
        Services saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Services instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Services find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Services instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Services> findAll(Services instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<Services> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public Services generate(Services instance) {
        instance.setCreatedAt(instance.getCreatedAt() == null ? dateUtil.getThisTime() : instance.getCreatedAt());
        return instance;
    }
}

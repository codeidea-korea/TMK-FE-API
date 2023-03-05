package com.binoofactory.alma.almabe.api.feecontract.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.feecontract.model.entity.ServiceOptions;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.ServiceOptionsRepos;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.ServiceOptionsDslRepos;
import com.binoofactory.alma.almabe.api.feecontract.service.ServiceOptionsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class ServiceOptionsServiceImpl implements ServiceOptionsService {

    private final ServiceOptionsRepos repos;

    private final ServiceOptionsDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public ServiceOptionsServiceImpl(ServiceOptionsRepos repos, ServiceOptionsDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public ServiceOptions add(ServiceOptions instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        ServiceOptions saved = repos.save(generate(instance));
        return saved;
    }

    private ServiceOptions findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public ServiceOptions edit(ServiceOptions instance, HttpServletRequest httpServletRequest) throws Exception {
        ServiceOptions saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        ServiceOptions instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public ServiceOptions find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        ServiceOptions instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<ServiceOptions> findAll(ServiceOptions instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<ServiceOptions> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public ServiceOptions generate(ServiceOptions instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

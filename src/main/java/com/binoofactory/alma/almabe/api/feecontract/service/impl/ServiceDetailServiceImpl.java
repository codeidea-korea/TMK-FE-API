package com.binoofactory.alma.almabe.api.feecontract.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.feecontract.model.entity.ServiceDetail;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.ServiceDetailRepos;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.ServiceDetailDslRepos;
import com.binoofactory.alma.almabe.api.feecontract.service.ServiceDetailService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class ServiceDetailServiceImpl implements ServiceDetailService {

    private final ServiceDetailRepos repos;

    private final ServiceDetailDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public ServiceDetailServiceImpl(ServiceDetailRepos repos, ServiceDetailDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public ServiceDetail add(ServiceDetail instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        ServiceDetail saved = repos.save(generate(instance));
        return saved;
    }

    private ServiceDetail findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public ServiceDetail edit(ServiceDetail instance, HttpServletRequest httpServletRequest) throws Exception {
        ServiceDetail saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        ServiceDetail instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public ServiceDetail find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        ServiceDetail instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<ServiceDetail> findAll(ServiceDetail instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<ServiceDetail> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public ServiceDetail generate(ServiceDetail instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

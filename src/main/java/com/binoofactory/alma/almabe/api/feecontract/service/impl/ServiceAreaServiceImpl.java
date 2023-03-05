package com.binoofactory.alma.almabe.api.feecontract.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.feecontract.model.entity.ServiceArea;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.ServiceAreaRepos;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.ServiceAreaDslRepos;
import com.binoofactory.alma.almabe.api.feecontract.service.ServiceAreaService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class ServiceAreaServiceImpl implements ServiceAreaService {

    private final ServiceAreaRepos repos;

    private final ServiceAreaDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public ServiceAreaServiceImpl(ServiceAreaRepos repos, ServiceAreaDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public ServiceArea add(ServiceArea instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        ServiceArea saved = repos.save(generate(instance));
        return saved;
    }

    private ServiceArea findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public ServiceArea edit(ServiceArea instance, HttpServletRequest httpServletRequest) throws Exception {
        ServiceArea saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        ServiceArea instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public ServiceArea find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        ServiceArea instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<ServiceArea> findAll(ServiceArea instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<ServiceArea> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public ServiceArea generate(ServiceArea instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

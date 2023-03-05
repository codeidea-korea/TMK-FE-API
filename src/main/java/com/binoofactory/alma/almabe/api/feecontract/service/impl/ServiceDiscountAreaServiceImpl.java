package com.binoofactory.alma.almabe.api.feecontract.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.feecontract.model.entity.ServiceDiscountArea;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.ServiceDiscountAreaRepos;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.ServiceDiscountAreaDslRepos;
import com.binoofactory.alma.almabe.api.feecontract.service.ServiceDiscountAreaService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class ServiceDiscountAreaServiceImpl implements ServiceDiscountAreaService {

    private final ServiceDiscountAreaRepos repos;

    private final ServiceDiscountAreaDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public ServiceDiscountAreaServiceImpl(ServiceDiscountAreaRepos repos, ServiceDiscountAreaDslRepos dslRepos,
        DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public ServiceDiscountArea add(ServiceDiscountArea instance, HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        ServiceDiscountArea saved = repos.save(generate(instance));
        return saved;
    }

    private ServiceDiscountArea findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public ServiceDiscountArea edit(ServiceDiscountArea instance, HttpServletRequest httpServletRequest)
        throws Exception {
        ServiceDiscountArea saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        ServiceDiscountArea instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public ServiceDiscountArea find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        ServiceDiscountArea instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<ServiceDiscountArea> findAll(ServiceDiscountArea instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<ServiceDiscountArea> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public ServiceDiscountArea generate(ServiceDiscountArea instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

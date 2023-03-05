package com.binoofactory.alma.almabe.api.feecontract.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.feecontract.model.entity.ServiceDiscount;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.ServiceDiscountRepos;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.ServiceDiscountDslRepos;
import com.binoofactory.alma.almabe.api.feecontract.service.ServiceDiscountService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class ServiceDiscountServiceImpl implements ServiceDiscountService {

    private final ServiceDiscountRepos repos;

    private final ServiceDiscountDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public ServiceDiscountServiceImpl(ServiceDiscountRepos repos, ServiceDiscountDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public ServiceDiscount add(ServiceDiscount instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        ServiceDiscount saved = repos.save(generate(instance));
        return saved;
    }

    private ServiceDiscount findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public ServiceDiscount edit(ServiceDiscount instance, HttpServletRequest httpServletRequest) throws Exception {
        ServiceDiscount saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        ServiceDiscount instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public ServiceDiscount find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        ServiceDiscount instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<ServiceDiscount> findAll(ServiceDiscount instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<ServiceDiscount> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public ServiceDiscount generate(ServiceDiscount instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

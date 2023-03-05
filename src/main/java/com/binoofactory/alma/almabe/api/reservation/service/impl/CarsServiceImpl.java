package com.binoofactory.alma.almabe.api.reservation.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.reservation.model.entity.Cars;
import com.binoofactory.alma.almabe.api.reservation.repos.jpa.CarsRepos;
import com.binoofactory.alma.almabe.api.reservation.repos.jpa.dsl.CarsDslRepos;
import com.binoofactory.alma.almabe.api.reservation.service.CarsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class CarsServiceImpl implements CarsService {

    private final CarsRepos repos;

    private final CarsDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public CarsServiceImpl(CarsRepos repos, CarsDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Cars add(Cars instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Cars saved = repos.save(generate(instance));
        return saved;
    }

    private Cars findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Cars edit(Cars instance, HttpServletRequest httpServletRequest) throws Exception {
        Cars saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Cars instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Cars find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Cars instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Cars> findAll(Cars instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<Cars> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public Cars generate(Cars instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

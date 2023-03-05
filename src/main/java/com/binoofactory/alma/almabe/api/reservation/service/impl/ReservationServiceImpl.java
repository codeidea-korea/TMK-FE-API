package com.binoofactory.alma.almabe.api.reservation.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.reservation.model.entity.Reservation;
import com.binoofactory.alma.almabe.api.reservation.repos.jpa.ReservationRepos;
import com.binoofactory.alma.almabe.api.reservation.repos.jpa.dsl.ReservationDslRepos;
import com.binoofactory.alma.almabe.api.reservation.service.ReservationService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class ReservationServiceImpl implements ReservationService {

    private final ReservationRepos repos;

    private final ReservationDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public ReservationServiceImpl(ReservationRepos repos, ReservationDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Reservation add(Reservation instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Reservation saved = repos.save(generate(instance));
        return saved;
    }

    private Reservation findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Reservation edit(Reservation instance, HttpServletRequest httpServletRequest) throws Exception {
        Reservation saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Reservation instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Reservation find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Reservation instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Reservation> findAll(Reservation instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<Reservation> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public Reservation generate(Reservation instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

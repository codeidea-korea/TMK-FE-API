package com.binoofactory.alma.almabe.api.coupon.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.coupon.model.entity.Coupons;
import com.binoofactory.alma.almabe.api.coupon.repos.jpa.CouponsRepos;
import com.binoofactory.alma.almabe.api.coupon.repos.jpa.dsl.CouponsDslRepos;
import com.binoofactory.alma.almabe.api.coupon.service.CouponsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class CouponsServiceImpl implements CouponsService {

    private final CouponsRepos repos;

    private final CouponsDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public CouponsServiceImpl(CouponsRepos repos, CouponsDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Coupons add(Coupons instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Coupons saved = repos.save(generate(instance));
        return saved;
    }

    private Coupons findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Coupons edit(Coupons instance, HttpServletRequest httpServletRequest) throws Exception {
        Coupons saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Coupons instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Coupons find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Coupons instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Coupons> findAll(Coupons instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<Coupons> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public Coupons generate(Coupons instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        return instance;
    }
}

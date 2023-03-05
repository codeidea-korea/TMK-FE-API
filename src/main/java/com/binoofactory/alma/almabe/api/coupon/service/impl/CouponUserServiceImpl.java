package com.binoofactory.alma.almabe.api.coupon.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.coupon.model.entity.CouponUser;
import com.binoofactory.alma.almabe.api.coupon.repos.jpa.CouponUserRepos;
import com.binoofactory.alma.almabe.api.coupon.repos.jpa.dsl.CouponUserDslRepos;
import com.binoofactory.alma.almabe.api.coupon.service.CouponUserService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class CouponUserServiceImpl implements CouponUserService {

    private final CouponUserRepos repos;

    private final CouponUserDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public CouponUserServiceImpl(CouponUserRepos repos, CouponUserDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public CouponUser add(CouponUser instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        CouponUser saved = repos.save(generate(instance));
        return saved;
    }

    private CouponUser findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public CouponUser edit(CouponUser instance, HttpServletRequest httpServletRequest) throws Exception {
        CouponUser saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        CouponUser instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public CouponUser find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        CouponUser instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<CouponUser> findAll(CouponUser instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<CouponUser> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public CouponUser generate(CouponUser instance) {
        return instance;
    }
}

package com.binoofactory.alma.almabe.api.coupon.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binoofactory.alma.almabe.api.coupon.model.docs.CouponUsedHistory;
import com.binoofactory.alma.almabe.api.coupon.repos.mongo.CouponUsedHistoryRepos;
import com.binoofactory.alma.almabe.api.coupon.service.CouponUsedHistoryService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class CouponUsedHistoryServiceImpl implements CouponUsedHistoryService {

    private final CouponUsedHistoryRepos repos;

    private final DateUtil dateUtil;

    @Autowired
    public CouponUsedHistoryServiceImpl(CouponUsedHistoryRepos repos, DateUtil dateUtil) {
        this.repos = repos;
        this.dateUtil = dateUtil;
    }

    @Override
    public CouponUsedHistory add(CouponUsedHistory instance, HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        //        CouponUsedHistory saved = repos.save(generate(instance));
        //        return saved;
        return instance;
    }

    private CouponUsedHistory findById(long id) {
        //        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
        return null;
    }

    @Override
    public CouponUsedHistory edit(CouponUsedHistory instance, HttpServletRequest httpServletRequest)
        throws Exception {
        //        CouponUsedHistory saved = repos.save(generate(instance));
        //        return saved;
        return instance;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        CouponUsedHistory instance = findById(seq);
        //        repos.delete(instance);
    }

    @Override
    public CouponUsedHistory find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        CouponUsedHistory instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<CouponUsedHistory> findAll(CouponUsedHistory instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public CouponUsedHistory generate(CouponUsedHistory instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

package com.binoofactory.alma.almabe.api.cms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.cms.model.entity.ReservationGuides;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.ReservationGuidesRepos;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.ReservationGuidesDslRepos;
import com.binoofactory.alma.almabe.api.cms.service.ReservationGuidesService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class ReservationGuidesServiceImpl implements ReservationGuidesService {

    private final ReservationGuidesRepos repos;

    private final ReservationGuidesDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public ReservationGuidesServiceImpl(ReservationGuidesRepos repos, ReservationGuidesDslRepos dslRepos,
        DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public ReservationGuides add(ReservationGuides instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        ReservationGuides saved = repos.save(generate(instance));
        return saved;
    }

    private ReservationGuides findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public ReservationGuides edit(ReservationGuides instance, HttpServletRequest httpServletRequest) throws Exception {
        ReservationGuides saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        ReservationGuides instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public ReservationGuides find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        ReservationGuides instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<ReservationGuides> findAll(ReservationGuides instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<ReservationGuides> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public ReservationGuides generate(ReservationGuides instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        return instance;
    }
}

package com.binoofactory.alma.almabe.api.cms.service.impl;

import java.util.List;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.cms.model.entity.Popups;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.PopupsRepos;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.PopupsDslRepos;
import com.binoofactory.alma.almabe.api.cms.service.PopupsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class PopupsServiceImpl implements PopupsService {

    private final PopupsRepos repos;

    private final PopupsDslRepos dslRepos;

    private final DateUtil dateUtil;

    @Autowired
    public PopupsServiceImpl(PopupsRepos repos, PopupsDslRepos dslRepos, DateUtil dateUtil) {
        this.repos = repos;
        this.dslRepos = dslRepos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Popups add(Popups instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Popups saved = repos.save(generate(instance));
        return saved;
    }

    private Popups findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Popups edit(Popups instance, HttpServletRequest httpServletRequest) throws Exception {
        Popups saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Popups instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Popups find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Popups instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Popups> findAll(Popups instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {

        List<Popups> list = dslRepos.findAll(instance, bfPage);
        long count = dslRepos.countAll(instance);

        return new BfListResponse(list, count, bfPage);
    }

    @Override
    public Popups generate(Popups instance) {
        instance.setModifiedAt(instance.getModifiedAt() == null ? dateUtil.getThisTime() : instance.getModifiedAt());
        return instance;
    }
}

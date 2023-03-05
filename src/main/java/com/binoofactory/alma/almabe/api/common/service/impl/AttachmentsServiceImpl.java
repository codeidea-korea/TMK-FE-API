package com.binoofactory.alma.almabe.api.common.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.stereotype.Service;
import org.springframework.web.client.HttpServerErrorException;

import com.binoofactory.alma.almabe.api.common.model.entity.Attachments;
import com.binoofactory.alma.almabe.api.common.repos.jpa.AttachmentsRepos;
import com.binoofactory.alma.almabe.api.common.service.AttachmentsService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class AttachmentsServiceImpl implements AttachmentsService {

    private final AttachmentsRepos repos;

    private final DateUtil dateUtil;

    @Autowired
    public AttachmentsServiceImpl(AttachmentsRepos repos, DateUtil dateUtil) {
        this.repos = repos;
        this.dateUtil = dateUtil;
    }

    @Override
    public Attachments add(Attachments instance, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Attachments saved = repos.save(generate(instance));
        return saved;
    }
    
    private Attachments findById(long id) {
        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
    }

    @Override
    public Attachments edit(Attachments instance, HttpServletRequest httpServletRequest) throws Exception {
        Attachments saved = repos.save(generate(instance));
        return saved;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        Attachments instance = findById(seq);
        repos.delete(instance);
    }

    @Override
    public Attachments find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        Attachments instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<Attachments> findAll(Attachments instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        return new BfListResponse(repos.findAll(bfPage.generatePageable()).getContent(), repos.count(), bfPage);
    }

    @Override
    public Attachments generate(Attachments instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

package com.binoofactory.alma.almabe.api.cms.service.impl;

import javax.servlet.http.HttpServletRequest;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import com.binoofactory.alma.almabe.api.cms.model.docs.AlimtalkHistory;
import com.binoofactory.alma.almabe.api.cms.repos.mongo.AlimtalkHistoryRepos;
import com.binoofactory.alma.almabe.api.cms.service.AlimtalkHistoryService;
import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.binoofactory.alma.almabe.common.utils.DateUtil;

@Service
public class AlimtalkHistoryServiceImpl implements AlimtalkHistoryService {

    private final AlimtalkHistoryRepos repos;

    private final DateUtil dateUtil;

    @Autowired
    public AlimtalkHistoryServiceImpl(AlimtalkHistoryRepos repos, DateUtil dateUtil) {
        this.repos = repos;
        this.dateUtil = dateUtil;
    }

    @Override
    public AlimtalkHistory add(AlimtalkHistory instance, HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        //        AlimtalkHistory saved = repos.save(generate(instance));
        //        return saved;
        return instance;
    }

    private AlimtalkHistory findById(long id) {
        //        return repos.findById(id).orElseThrow(() -> new HttpServerErrorException(HttpStatus.BAD_REQUEST, "없는 값입니다."));
        return null;
    }

    @Override
    public AlimtalkHistory edit(AlimtalkHistory instance, HttpServletRequest httpServletRequest)
        throws Exception {
        //        AlimtalkHistory saved = repos.save(generate(instance));
        //        return saved;
        return instance;
    }

    @Override
    public void remove(long seq, HttpServletRequest httpServletRequest) throws Exception {
        AlimtalkHistory instance = findById(seq);
        //        repos.delete(instance);
    }

    @Override
    public AlimtalkHistory find(long seq, HttpServletRequest httpServletRequest) throws Exception {
        // TODO Auto-generated method stub
        AlimtalkHistory instance = findById(seq);
        return instance;
    }

    @Override
    public BfListResponse<AlimtalkHistory> findAll(AlimtalkHistory instance, BfPage bfPage,
        HttpServletRequest httpServletRequest)
        throws Exception {
        // TODO Auto-generated method stub
        return null;
    }

    @Override
    public AlimtalkHistory generate(AlimtalkHistory instance) {
        // TODO Auto-generated method stub
        return instance;
    }
}

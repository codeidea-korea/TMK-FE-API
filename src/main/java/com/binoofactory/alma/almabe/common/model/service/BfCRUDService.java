package com.binoofactory.alma.almabe.common.model.service;

import javax.servlet.http.HttpServletRequest;

import com.binoofactory.alma.almabe.common.model.BfListResponse;
import com.binoofactory.alma.almabe.common.model.BfPage;

public interface BfCRUDService<T> {

    T add(T obj, HttpServletRequest httpServletRequest) throws Exception;

    T generate(T obj);

    T edit(T obj, HttpServletRequest httpServletRequest) throws Exception;

    void remove(long seq, HttpServletRequest httpServletRequest) throws Exception;

    T find(long seq, HttpServletRequest httpServletRequest) throws Exception;

    BfListResponse<T> findAll(T obj, BfPage bfPage, HttpServletRequest httpServletRequest) throws Exception;

}
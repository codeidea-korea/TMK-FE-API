package com.binoofactory.alma.almabe.common.model.repos;

import java.util.List;

import com.binoofactory.alma.almabe.common.model.BfPage;

public interface BfIFDslRepos<T> {
    List<T> findAll(T obj, BfPage bfPage);

    long countAll(T obj);
}

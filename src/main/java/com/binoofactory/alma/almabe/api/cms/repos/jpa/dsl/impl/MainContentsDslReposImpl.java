package com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.entity.MainContents;
import com.binoofactory.alma.almabe.api.cms.model.entity.QMainContents;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.MainContentsDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MainContentsDslReposImpl extends QuerydslRepositorySupport implements MainContentsDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public MainContentsDslReposImpl() {
        super(MainContents.class);
    }

    private JPAQuery selectFromWhere(MainContents instance, QMainContents qMainContents) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qMainContents);

        if (StringUtils.isNotEmpty(instance.getMainContentsTypeCode())) {
            query.where(qMainContents.mainContentsTypeCode.eq(instance.getMainContentsTypeCode()));
        }
        if (instance.getId() > 0) {
            query.where(qMainContents.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<MainContents> findAll(MainContents instance, BfPage bfPage) {
        QMainContents qMainContents = QMainContents.mainContents;
        JPAQuery query = selectFromWhere(instance, qMainContents);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QMainContents.class, qMainContents.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(MainContents instance) {
        QMainContents qFile = QMainContents.mainContents;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
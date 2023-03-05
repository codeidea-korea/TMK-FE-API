package com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.entity.PushUsers;
import com.binoofactory.alma.almabe.api.cms.model.entity.QPushUsers;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.PushUsersDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class PushUsersDslReposImpl extends QuerydslRepositorySupport implements PushUsersDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public PushUsersDslReposImpl() {
        super(PushUsers.class);
    }

    private JPAQuery selectFromWhere(PushUsers instance, QPushUsers qPushUsers) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qPushUsers);

        if (StringUtils.isNotEmpty(instance.getBrandName())) {
            query.where(qPushUsers.brandName.contains(instance.getBrandName()));
        }
        if (instance.getBrandId() > 0) {
            query.where(qPushUsers.brandId.eq(instance.getBrandId()));
        }
        if (instance.getId() > 0) {
            query.where(qPushUsers.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<PushUsers> findAll(PushUsers instance, BfPage bfPage) {
        QPushUsers qPushUsers = QPushUsers.pushUsers;
        JPAQuery query = selectFromWhere(instance, qPushUsers);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QPushUsers.class, qPushUsers.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(PushUsers instance) {
        QPushUsers qFile = QPushUsers.pushUsers;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
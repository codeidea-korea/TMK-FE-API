package com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.entity.Push;
import com.binoofactory.alma.almabe.api.cms.model.entity.QPush;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.PushDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class PushDslReposImpl extends QuerydslRepositorySupport implements PushDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public PushDslReposImpl() {
        super(Push.class);
    }

    private JPAQuery selectFromWhere(Push instance, QPush qPush) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qPush);

        if (StringUtils.isNotEmpty(instance.getContents())) {
            query.where(qPush.contents.contains(instance.getContents()));
        }
        if (StringUtils.isNotEmpty(instance.getTitle())) {
            query.where(qPush.title.contains(instance.getTitle()));
        }
        if (StringUtils.isNotEmpty(instance.getOsTypeCode())) {
            query.where(qPush.osTypeCode.eq(instance.getOsTypeCode()));
        }
        if (StringUtils.isNotEmpty(instance.getPushTargetTypeCode())) {
            query.where(qPush.pushTargetTypeCode.eq(instance.getPushTargetTypeCode()));
        }
        if (StringUtils.isNotEmpty(instance.getPushTypeCode())) {
            query.where(qPush.pushTypeCode.eq(instance.getPushTypeCode()));
        }
        if (instance.getBrandId() > 0) {
            query.where(qPush.brandId.eq(instance.getBrandId()));
        }
        if (instance.getId() > 0) {
            query.where(qPush.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<Push> findAll(Push instance, BfPage bfPage) {
        QPush qPush = QPush.push;
        JPAQuery query = selectFromWhere(instance, qPush);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QPush.class, qPush.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(Push instance) {
        QPush qFile = QPush.push;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
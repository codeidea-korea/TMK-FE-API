package com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.user.model.entity.Auth;
import com.binoofactory.alma.almabe.api.user.model.entity.QAuth;
import com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.AuthDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class AuthDslReposImpl extends QuerydslRepositorySupport implements AuthDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public AuthDslReposImpl() {
        super(Auth.class);
    }

    private JPAQuery selectFromWhere(Auth instance, QAuth qAuth) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qAuth);

        if (StringUtils.isNotEmpty(instance.getName())) {
            query.where(qAuth.name.contains(instance.getName()));
        }
        if (StringUtils.isNotEmpty(instance.getGroupCode())) {
            query.where(qAuth.groupCode.eq(instance.getGroupCode()));
        }
        if (instance.getId() > 0) {
            query.where(qAuth.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<Auth> findAll(Auth instance, BfPage bfPage) {
        QAuth qAuth = QAuth.auth;
        JPAQuery query = selectFromWhere(instance, qAuth);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QAuth.class, qAuth.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(Auth instance) {
        QAuth qFile = QAuth.auth;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
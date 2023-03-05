package com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.user.model.entity.QUserAuth;
import com.binoofactory.alma.almabe.api.user.model.entity.UserAuth;
import com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.UserAuthDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UserAuthDslReposImpl extends QuerydslRepositorySupport implements UserAuthDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public UserAuthDslReposImpl() {
        super(UserAuth.class);
    }

    private JPAQuery selectFromWhere(UserAuth instance, QUserAuth qUserAuth) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qUserAuth);

        if (instance.getAuthId() > 0) {
            query.where(qUserAuth.authId.eq(instance.getAuthId()));
        }
        if (instance.getUserId() > 0) {
            query.where(qUserAuth.userId.eq(instance.getUserId()));
        }
        if (instance.getId() > 0) {
            query.where(qUserAuth.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<UserAuth> findAll(UserAuth instance, BfPage bfPage) {
        QUserAuth qUserAuth = QUserAuth.userAuth;
        JPAQuery query = selectFromWhere(instance, qUserAuth);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QUserAuth.class, qUserAuth.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(UserAuth instance) {
        QUserAuth qFile = QUserAuth.userAuth;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
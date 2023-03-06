package com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.user.model.entity.QUsers;
import com.binoofactory.alma.almabe.api.user.model.entity.Users;
import com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.UsersDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

import io.netty.util.internal.StringUtil;

@Repository
public class UsersDslReposImpl extends QuerydslRepositorySupport implements UsersDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public UsersDslReposImpl() {
        super(Users.class);
    }

    private JPAQuery selectFromWhere(Users user, QUsers qUsers) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qUsers);

        query.where(qUsers.deleted.eq(user.getDeleted()));
        if(!StringUtil.isNullOrEmpty(user.getName())) {
            query.where(qUsers.name.contains(user.getName()));
        }
        if(!StringUtil.isNullOrEmpty(user.getUserId())) {
            query.where(qUsers.userId.contains(user.getUserId()));
        }
        if(!StringUtil.isNullOrEmpty(user.getEmail())) {
            query.where(qUsers.email.contains(user.getEmail()));
        }

        return query;
    }

    @Override
    public List<Users> findAll(Users user, BfPage bfPage) {
        QUsers qUsers = QUsers.users;
        JPAQuery query = selectFromWhere(user, qUsers);

        query
        .offset(bfPage.getOffset())
        .limit(bfPage.getPageSize())
        .orderBy(
            new OrderSpecifier(com.querydsl.core.types.Order.DESC,
            new PathBuilder(QUsers.class, qUsers.id.getMetadata()))
        );

        return query.fetch();
    }

    @Override
    public long countAll(Users user) {
        QUsers qUsers = QUsers.users;
        JPAQuery query = selectFromWhere(user, qUsers);

        return query.fetchCount();
    }

}
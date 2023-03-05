package com.binoofactory.alma.almabe.api.csc.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.csc.model.Counsel;
import com.binoofactory.alma.almabe.api.csc.model.QCounsel;
import com.binoofactory.alma.almabe.api.csc.repos.jpa.dsl.CounselDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CounselDslReposImpl extends QuerydslRepositorySupport implements CounselDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public CounselDslReposImpl() {
        super(Counsel.class);
    }

    private JPAQuery selectFromWhere(Counsel instance, QCounsel qCounsel) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qCounsel);

        if (StringUtils.isNotEmpty(instance.getContents())) {
            query.where(qCounsel.contents.contains(instance.getContents()));
        }
        if (StringUtils.isNotEmpty(instance.getCounselStatusCode())) {
            query.where(qCounsel.counselStatusCode.eq(instance.getCounselStatusCode()));
        }
        if (StringUtils.isNotEmpty(instance.getCounselTypeCode())) {
            query.where(qCounsel.counselTypeCode.eq(instance.getCounselTypeCode()));
        }
        if (StringUtils.isNotEmpty(instance.getEmail())) {
            query.where(qCounsel.email.contains(instance.getEmail()));
        }
        if (StringUtils.isNotEmpty(instance.getUserName())) {
            query.where(qCounsel.userName.contains(instance.getUserName()));
        }
        if (StringUtils.isNotEmpty(instance.getUserPhoneNo())) {
            query.where(qCounsel.userPhoneNo.contains(instance.getUserPhoneNo()));
        }
        if (instance.getUserId() > 0) {
            query.where(qCounsel.userId.eq(instance.getUserId()));
        }
        if (instance.getId() > 0) {
            query.where(qCounsel.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<Counsel> findAll(Counsel instance, BfPage bfPage) {
        QCounsel qCounsel = QCounsel.counsel;
        JPAQuery query = selectFromWhere(instance, qCounsel);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QCounsel.class, qCounsel.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(Counsel instance) {
        QCounsel qFile = QCounsel.counsel;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
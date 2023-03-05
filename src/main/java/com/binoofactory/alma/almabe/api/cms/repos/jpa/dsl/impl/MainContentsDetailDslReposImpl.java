package com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.entity.MainContentsDetail;
import com.binoofactory.alma.almabe.api.cms.model.entity.QMainContentsDetail;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.MainContentsDetailDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class MainContentsDetailDslReposImpl extends QuerydslRepositorySupport implements MainContentsDetailDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public MainContentsDetailDslReposImpl() {
        super(MainContentsDetail.class);
    }

    private JPAQuery selectFromWhere(MainContentsDetail instance, QMainContentsDetail qMainContentsDetail) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qMainContentsDetail);

        if (StringUtils.isNotEmpty(instance.getAlt())) {
            query.where(qMainContentsDetail.alt.contains(instance.getAlt()));
        }
        if (StringUtils.isNotEmpty(instance.getMenuName())) {
            query.where(qMainContentsDetail.menuName.contains(instance.getMenuName()));
        }
        if (instance.getEfectiveStartedAt() != null) {
            query.where(qMainContentsDetail.efectiveStartedAt.loe(instance.getEfectiveStartedAt()));
        }
        if (instance.getEfectiveEndedAt() != null) {
            query.where(qMainContentsDetail.efectiveEndedAt.goe(instance.getEfectiveEndedAt()));
        }
        if (instance.getId() > 0) {
            query.where(qMainContentsDetail.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<MainContentsDetail> findAll(MainContentsDetail instance, BfPage bfPage) {
        QMainContentsDetail qMainContentsDetail = QMainContentsDetail.mainContentsDetail;
        JPAQuery query = selectFromWhere(instance, qMainContentsDetail);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QMainContentsDetail.class, qMainContentsDetail.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(MainContentsDetail instance) {
        QMainContentsDetail qFile = QMainContentsDetail.mainContentsDetail;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
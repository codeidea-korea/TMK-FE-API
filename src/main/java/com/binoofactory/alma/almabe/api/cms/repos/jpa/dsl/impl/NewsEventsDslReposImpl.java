package com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.entity.NewsEvents;
import com.binoofactory.alma.almabe.api.cms.model.entity.QNewsEvents;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.NewsEventsDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class NewsEventsDslReposImpl extends QuerydslRepositorySupport implements NewsEventsDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public NewsEventsDslReposImpl() {
        super(NewsEvents.class);
    }

    private JPAQuery selectFromWhere(NewsEvents instance, QNewsEvents qNewsEvents) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qNewsEvents);

        if (StringUtils.isNotEmpty(instance.getContents())) {
            query.where(qNewsEvents.contents.contains(instance.getContents()));
        }
        if (StringUtils.isNotEmpty(instance.getNewsContentsTypeCode())) {
            query.where(qNewsEvents.newsContentsTypeCode.eq(instance.getNewsContentsTypeCode()));
        }
        if (StringUtils.isNotEmpty(instance.getTitle())) {
            query.where(qNewsEvents.title.eq(instance.getTitle()));
        }
        if (instance.getEfectiveStartedAt() != null) {
            query.where(qNewsEvents.efectiveStartedAt.loe(instance.getEfectiveStartedAt()));
        }
        if (instance.getEfectiveEndedAt() != null) {
            query.where(qNewsEvents.efectiveEndedAt.goe(instance.getEfectiveEndedAt()));
        }
        if (instance.getId() > 0) {
            query.where(qNewsEvents.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<NewsEvents> findAll(NewsEvents instance, BfPage bfPage) {
        QNewsEvents qNewsEvents = QNewsEvents.newsEvents;
        JPAQuery query = selectFromWhere(instance, qNewsEvents);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QNewsEvents.class, qNewsEvents.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(NewsEvents instance) {
        QNewsEvents qFile = QNewsEvents.newsEvents;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
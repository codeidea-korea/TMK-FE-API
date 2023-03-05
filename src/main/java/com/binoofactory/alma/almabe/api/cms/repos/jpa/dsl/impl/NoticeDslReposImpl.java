package com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.entity.Notice;
import com.binoofactory.alma.almabe.api.cms.model.entity.QNotice;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.NoticeDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class NoticeDslReposImpl extends QuerydslRepositorySupport implements NoticeDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public NoticeDslReposImpl() {
        super(Notice.class);
    }

    private JPAQuery selectFromWhere(Notice instance, QNotice qNotice) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qNotice);

        if (StringUtils.isNotEmpty(instance.getContents())) {
            query.where(qNotice.contents.contains(instance.getContents()));
        }
        if (StringUtils.isNotEmpty(instance.getTitle())) {
            query.where(qNotice.title.contains(instance.getTitle()));
        }
        if (instance.getBrandId() > 0) {
            query.where(qNotice.brandId.eq(instance.getBrandId()));
        }
        if (instance.getId() > 0) {
            query.where(qNotice.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<Notice> findAll(Notice instance, BfPage bfPage) {
        QNotice qNotice = QNotice.notice;
        JPAQuery query = selectFromWhere(instance, qNotice);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QNotice.class, qNotice.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(Notice instance) {
        QNotice qFile = QNotice.notice;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
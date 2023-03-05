package com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.entity.NewsLetters;
import com.binoofactory.alma.almabe.api.cms.model.entity.QNewsLetters;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.NewsLettersDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class NewsLettersDslReposImpl extends QuerydslRepositorySupport implements NewsLettersDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public NewsLettersDslReposImpl() {
        super(NewsLetters.class);
    }

    private JPAQuery selectFromWhere(NewsLetters instance, QNewsLetters qNewsLetters) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qNewsLetters);

        if (StringUtils.isNotEmpty(instance.getNewsletterStatusCode())) {
            query.where(qNewsLetters.newsletterStatusCode.eq(instance.getNewsletterStatusCode()));
        }
        if (StringUtils.isNotEmpty(instance.getUserEmail())) {
            query.where(qNewsLetters.userEmail.eq(instance.getUserEmail()));
        }
        if (StringUtils.isNotEmpty(instance.getUserName())) {
            query.where(qNewsLetters.userName.eq(instance.getUserName()));
        }
        if (StringUtils.isNotEmpty(instance.getUserPhone())) {
            query.where(qNewsLetters.userPhone.eq(instance.getUserPhone()));
        }
        if (instance.getBrandId() > 0) {
            query.where(qNewsLetters.brandId.eq(instance.getBrandId()));
        }
        if (instance.getId() > 0) {
            query.where(qNewsLetters.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<NewsLetters> findAll(NewsLetters instance, BfPage bfPage) {
        QNewsLetters qNewsLetters = QNewsLetters.newsLetters;
        JPAQuery query = selectFromWhere(instance, qNewsLetters);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QNewsLetters.class, qNewsLetters.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(NewsLetters instance) {
        QNewsLetters qFile = QNewsLetters.newsLetters;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
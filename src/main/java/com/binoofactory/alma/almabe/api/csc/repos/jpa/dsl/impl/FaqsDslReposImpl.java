package com.binoofactory.alma.almabe.api.csc.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.csc.model.Faqs;
import com.binoofactory.alma.almabe.api.csc.model.QFaqs;
import com.binoofactory.alma.almabe.api.csc.repos.jpa.dsl.FaqsDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class FaqsDslReposImpl extends QuerydslRepositorySupport implements FaqsDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public FaqsDslReposImpl() {
        super(Faqs.class);
    }

    private JPAQuery selectFromWhere(Faqs instance, QFaqs qFaqs) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qFaqs);

        if (StringUtils.isNotEmpty(instance.getContents())) {
            query.where(qFaqs.contents.contains(instance.getContents()));
        }
        if (StringUtils.isNotEmpty(instance.getFaqTypeCode())) {
            query.where(qFaqs.faqTypeCode.eq(instance.getFaqTypeCode()));
        }
        if (StringUtils.isNotEmpty(instance.getTitle())) {
            query.where(qFaqs.title.contains(instance.getTitle()));
        }
        if (instance.getId() > 0) {
            query.where(qFaqs.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<Faqs> findAll(Faqs instance, BfPage bfPage) {
        QFaqs qFaqs = QFaqs.faqs;
        JPAQuery query = selectFromWhere(instance, qFaqs);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QFaqs.class, qFaqs.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(Faqs instance) {
        QFaqs qFile = QFaqs.faqs;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
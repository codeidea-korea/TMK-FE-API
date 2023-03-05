package com.binoofactory.alma.almabe.api.csc.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.csc.model.FrequentlyAnswer;
import com.binoofactory.alma.almabe.api.csc.model.QFrequentlyAnswer;
import com.binoofactory.alma.almabe.api.csc.repos.jpa.dsl.FrequentlyAnswerDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class FrequentlyAnswerDslReposImpl extends QuerydslRepositorySupport implements FrequentlyAnswerDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public FrequentlyAnswerDslReposImpl() {
        super(FrequentlyAnswer.class);
    }

    private JPAQuery selectFromWhere(FrequentlyAnswer instance, QFrequentlyAnswer qFrequentlyAnswer) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qFrequentlyAnswer);

        if (StringUtils.isNotEmpty(instance.getContents())) {
            query.where(qFrequentlyAnswer.contents.contains(instance.getContents()));
        }
        if (StringUtils.isNotEmpty(instance.getTitle())) {
            query.where(qFrequentlyAnswer.title.contains(instance.getTitle()));
        }
        if (instance.getId() > 0) {
            query.where(qFrequentlyAnswer.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<FrequentlyAnswer> findAll(FrequentlyAnswer instance, BfPage bfPage) {
        QFrequentlyAnswer qFrequentlyAnswer = QFrequentlyAnswer.frequentlyAnswer;
        JPAQuery query = selectFromWhere(instance, qFrequentlyAnswer);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QFrequentlyAnswer.class, qFrequentlyAnswer.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(FrequentlyAnswer instance) {
        QFrequentlyAnswer qFile = QFrequentlyAnswer.frequentlyAnswer;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
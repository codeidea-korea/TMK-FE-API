package com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.entity.QTerms;
import com.binoofactory.alma.almabe.api.cms.model.entity.Terms;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.TermsDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TermsDslReposImpl extends QuerydslRepositorySupport implements TermsDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public TermsDslReposImpl() {
        super(Terms.class);
    }

    private JPAQuery selectFromWhere(Terms instance, QTerms qTerms) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qTerms);

        if (StringUtils.isNotEmpty(instance.getContents())) {
            query.where(qTerms.contents.contains(instance.getContents()));
        }
        if (StringUtils.isNotEmpty(instance.getTermsCaseCode())) {
            query.where(qTerms.termsCaseCode.eq(instance.getTermsCaseCode()));
        }
        if (StringUtils.isNotEmpty(instance.getTermsTypeCode())) {
            query.where(qTerms.termsTypeCode.eq(instance.getTermsTypeCode()));
        }
        if (instance.getId() > 0) {
            query.where(qTerms.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<Terms> findAll(Terms instance, BfPage bfPage) {
        QTerms qTerms = QTerms.terms;
        JPAQuery query = selectFromWhere(instance, qTerms);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QTerms.class, qTerms.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(Terms instance) {
        QTerms qFile = QTerms.terms;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
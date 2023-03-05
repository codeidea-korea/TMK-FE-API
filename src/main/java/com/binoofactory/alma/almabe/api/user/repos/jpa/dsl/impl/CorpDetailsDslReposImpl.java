package com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.user.model.entity.CorpDetails;
import com.binoofactory.alma.almabe.api.user.model.entity.QCorpDetails;
import com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.CorpDetailsDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CorpDetailsDslReposImpl extends QuerydslRepositorySupport implements CorpDetailsDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public CorpDetailsDslReposImpl() {
        super(CorpDetails.class);
    }

    private JPAQuery selectFromWhere(CorpDetails instance, QCorpDetails qCorpDetails) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qCorpDetails);

        if (StringUtils.isNotEmpty(instance.getDepartmentCode())) {
            query.where(qCorpDetails.departmentCode.contains(instance.getDepartmentCode()));
        }
        if (instance.getId() > 0) {
            query.where(qCorpDetails.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<CorpDetails> findAll(CorpDetails instance, BfPage bfPage) {
        QCorpDetails qCorpDetails = QCorpDetails.corpDetails;
        JPAQuery query = selectFromWhere(instance, qCorpDetails);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QCorpDetails.class, qCorpDetails.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(CorpDetails instance) {
        QCorpDetails qFile = QCorpDetails.corpDetails;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
package com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.entity.QUsageGuides;
import com.binoofactory.alma.almabe.api.cms.model.entity.UsageGuides;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.UsageGuidesDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class UsageGuidesDslReposImpl extends QuerydslRepositorySupport implements UsageGuidesDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public UsageGuidesDslReposImpl() {
        super(UsageGuides.class);
    }

    private JPAQuery selectFromWhere(UsageGuides instance, QUsageGuides qUsageGuides) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qUsageGuides);

        if (StringUtils.isNotEmpty(instance.getContents())) {
            query.where(qUsageGuides.contents.contains(instance.getContents()));
        }
        if (StringUtils.isNotEmpty(instance.getTitle())) {
            query.where(qUsageGuides.title.contains(instance.getTitle()));
        }
        if (StringUtils.isNotEmpty(instance.getUsageGuidesTypeCode())) {
            query.where(qUsageGuides.usageGuidesTypeCode.eq(instance.getUsageGuidesTypeCode()));
        }
        if (instance.getId() > 0) {
            query.where(qUsageGuides.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<UsageGuides> findAll(UsageGuides instance, BfPage bfPage) {
        QUsageGuides qUsageGuides = QUsageGuides.usageGuides;
        JPAQuery query = selectFromWhere(instance, qUsageGuides);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QUsageGuides.class, qUsageGuides.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(UsageGuides instance) {
        QUsageGuides qFile = QUsageGuides.usageGuides;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
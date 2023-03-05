package com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.entity.NormalContents;
import com.binoofactory.alma.almabe.api.cms.model.entity.QNormalContents;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.NormalContentsDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class NormalContentsDslReposImpl extends QuerydslRepositorySupport implements NormalContentsDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public NormalContentsDslReposImpl() {
        super(NormalContents.class);
    }

    private JPAQuery selectFromWhere(NormalContents instance, QNormalContents qNormalContents) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qNormalContents);

        if (StringUtils.isNotEmpty(instance.getContents())) {
            query.where(qNormalContents.contents.contains(instance.getContents()));
        }
        if (StringUtils.isNotEmpty(instance.getNormalContentsExhibitionTypeCode())) {
            query.where(
                qNormalContents.normalContentsExhibitionTypeCode.eq(instance.getNormalContentsExhibitionTypeCode()));
        }
        if (instance.getId() > 0) {
            query.where(qNormalContents.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<NormalContents> findAll(NormalContents instance, BfPage bfPage) {
        QNormalContents qNormalContents = QNormalContents.normalContents;
        JPAQuery query = selectFromWhere(instance, qNormalContents);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QNormalContents.class, qNormalContents.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(NormalContents instance) {
        QNormalContents qFile = QNormalContents.normalContents;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
package com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.entity.ErrorBoard;
import com.binoofactory.alma.almabe.api.cms.model.entity.QErrorBoard;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.ErrorBoardDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ErrorBoardDslReposImpl extends QuerydslRepositorySupport implements ErrorBoardDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public ErrorBoardDslReposImpl() {
        super(ErrorBoard.class);
    }

    private JPAQuery selectFromWhere(ErrorBoard instance, QErrorBoard qErrorBoard) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qErrorBoard);

        if (StringUtils.isNotEmpty(instance.getContents())) {
            query.where(qErrorBoard.contents.contains(instance.getContents()));
        }
        if (StringUtils.isNotEmpty(instance.getLargeCategory())) {
            query.where(qErrorBoard.largeCategory.eq(instance.getLargeCategory()));
        }
        if (StringUtils.isNotEmpty(instance.getMediumCategory())) {
            query.where(qErrorBoard.mediumCategory.eq(instance.getMediumCategory()));
        }
        if (StringUtils.isNotEmpty(instance.getSmallCategory())) {
            query.where(qErrorBoard.smallCategory.eq(instance.getSmallCategory()));
        }
        if (instance.getBrandId() > 0) {
            query.where(qErrorBoard.brandId.eq(instance.getBrandId()));
        }
        if (instance.getCheckerId() > 0) {
            query.where(qErrorBoard.checkerId.eq(instance.getCheckerId()));
        }
        if (instance.getId() > 0) {
            query.where(qErrorBoard.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<ErrorBoard> findAll(ErrorBoard instance, BfPage bfPage) {
        QErrorBoard qErrorBoard = QErrorBoard.errorBoard;
        JPAQuery query = selectFromWhere(instance, qErrorBoard);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QErrorBoard.class, qErrorBoard.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(ErrorBoard instance) {
        QErrorBoard qFile = QErrorBoard.errorBoard;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
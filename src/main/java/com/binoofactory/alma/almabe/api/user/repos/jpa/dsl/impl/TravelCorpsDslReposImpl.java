package com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.user.model.entity.QTravelCorps;
import com.binoofactory.alma.almabe.api.user.model.entity.TravelCorps;
import com.binoofactory.alma.almabe.api.user.repos.jpa.dsl.TravelCorpsDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class TravelCorpsDslReposImpl extends QuerydslRepositorySupport implements TravelCorpsDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public TravelCorpsDslReposImpl() {
        super(TravelCorps.class);
    }

    private JPAQuery selectFromWhere(TravelCorps instance, QTravelCorps qTravelCorps) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qTravelCorps);

        if (StringUtils.isNotEmpty(instance.getBusinessNo())) {
            query.where(qTravelCorps.businessNo.contains(instance.getBusinessNo()));
        }
        if (StringUtils.isNotEmpty(instance.getDirectorName())) {
            query.where(qTravelCorps.directorName.contains(instance.getDirectorName()));
        }
        if (StringUtils.isNotEmpty(instance.getName())) {
            query.where(qTravelCorps.name.contains(instance.getName()));
        }
        if (StringUtils.isNotEmpty(instance.getTravelNo())) {
            query.where(qTravelCorps.travelNo.contains(instance.getTravelNo()));
        }
        if (instance.getId() > 0) {
            query.where(qTravelCorps.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<TravelCorps> findAll(TravelCorps instance, BfPage bfPage) {
        QTravelCorps qTravelCorps = QTravelCorps.travelCorps;
        JPAQuery query = selectFromWhere(instance, qTravelCorps);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QTravelCorps.class, qTravelCorps.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(TravelCorps instance) {
        QTravelCorps qFile = QTravelCorps.travelCorps;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
package com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.entity.QReservationGuides;
import com.binoofactory.alma.almabe.api.cms.model.entity.ReservationGuides;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.ReservationGuidesDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ReservationGuidesDslReposImpl extends QuerydslRepositorySupport implements ReservationGuidesDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public ReservationGuidesDslReposImpl() {
        super(ReservationGuides.class);
    }

    private JPAQuery selectFromWhere(ReservationGuides instance, QReservationGuides qReservationGuides) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qReservationGuides);

        if (StringUtils.isNotEmpty(instance.getContents())) {
            query.where(qReservationGuides.contents.contains(instance.getContents()));
        }
        if (StringUtils.isNotEmpty(instance.getPaymentTypeCode())) {
            query.where(qReservationGuides.paymentTypeCode.eq(instance.getPaymentTypeCode()));
        }
        if (StringUtils.isNotEmpty(instance.getReservationGuideCode())) {
            query.where(qReservationGuides.reservationGuideCode.eq(instance.getReservationGuideCode()));
        }
        if (StringUtils.isNotEmpty(instance.getReservationsExhibitionTypeCode())) {
            query.where(
                qReservationGuides.reservationsExhibitionTypeCode.eq(instance.getReservationsExhibitionTypeCode()));
        }
        if (instance.getId() > 0) {
            query.where(qReservationGuides.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<ReservationGuides> findAll(ReservationGuides instance, BfPage bfPage) {
        QReservationGuides qReservationGuides = QReservationGuides.reservationGuides;
        JPAQuery query = selectFromWhere(instance, qReservationGuides);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QReservationGuides.class, qReservationGuides.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(ReservationGuides instance) {
        QReservationGuides qFile = QReservationGuides.reservationGuides;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
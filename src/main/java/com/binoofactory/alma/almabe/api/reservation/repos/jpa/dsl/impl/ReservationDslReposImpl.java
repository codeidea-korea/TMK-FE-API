package com.binoofactory.alma.almabe.api.reservation.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.reservation.model.entity.QReservation;
import com.binoofactory.alma.almabe.api.reservation.model.entity.Reservation;
import com.binoofactory.alma.almabe.api.reservation.repos.jpa.dsl.ReservationDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ReservationDslReposImpl extends QuerydslRepositorySupport implements ReservationDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public ReservationDslReposImpl() {
        super(Reservation.class);
    }

    private JPAQuery selectFromWhere(Reservation instance, QReservation qReservation) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qReservation);

        if (instance.getId() > 0) {
            query.where(qReservation.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<Reservation> findAll(Reservation instance, BfPage bfPage) {
        QReservation qReservation = QReservation.reservation;
        JPAQuery query = selectFromWhere(instance, qReservation);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QReservation.class, qReservation.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(Reservation instance) {
        QReservation qFile = QReservation.reservation;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
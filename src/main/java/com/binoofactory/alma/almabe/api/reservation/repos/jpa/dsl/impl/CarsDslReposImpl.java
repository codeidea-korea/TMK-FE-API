package com.binoofactory.alma.almabe.api.reservation.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.reservation.model.entity.Cars;
import com.binoofactory.alma.almabe.api.reservation.model.entity.QCars;
import com.binoofactory.alma.almabe.api.reservation.repos.jpa.dsl.CarsDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CarsDslReposImpl extends QuerydslRepositorySupport implements CarsDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public CarsDslReposImpl() {
        super(Cars.class);
    }

    private JPAQuery selectFromWhere(Cars instance, QCars qCars) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qCars);

        if (instance.getId() > 0) {
            query.where(qCars.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<Cars> findAll(Cars instance, BfPage bfPage) {
        QCars qCars = QCars.cars;
        JPAQuery query = selectFromWhere(instance, qCars);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QCars.class, qCars.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(Cars instance) {
        QCars qFile = QCars.cars;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
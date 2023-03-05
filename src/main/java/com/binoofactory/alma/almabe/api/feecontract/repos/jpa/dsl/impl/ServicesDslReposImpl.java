package com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.feecontract.model.entity.QServices;
import com.binoofactory.alma.almabe.api.feecontract.model.entity.Services;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.ServicesDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ServicesDslReposImpl extends QuerydslRepositorySupport implements ServicesDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public ServicesDslReposImpl() {
        super(Services.class);
    }

    private JPAQuery selectFromWhere(Services instance, QServices qServices) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qServices);

        if (StringUtils.isNotEmpty(instance.getName())) {
            query.where(qServices.name.contains(instance.getName()));
        }
        if (StringUtils.isNotEmpty(instance.getProductCode())) {
            query.where(qServices.productCode.eq(instance.getProductCode()));
        }
        if (StringUtils.isNotEmpty(instance.getServiceTypeCode())) {
            query.where(qServices.serviceTypeCode.eq(instance.getServiceTypeCode()));
        }
        if (instance.getBrandId() > 0) {
            query.where(qServices.brandId.eq(instance.getBrandId()));
        }
        if (instance.getId() > 0) {
            query.where(qServices.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<Services> findAll(Services instance, BfPage bfPage) {
        QServices qServices = QServices.services;
        JPAQuery query = selectFromWhere(instance, qServices);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QServices.class, qServices.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(Services instance) {
        QServices qFile = QServices.services;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
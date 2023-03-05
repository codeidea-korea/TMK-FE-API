package com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.feecontract.model.entity.QServiceDiscount;
import com.binoofactory.alma.almabe.api.feecontract.model.entity.ServiceDiscount;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.ServiceDiscountDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ServiceDiscountDslReposImpl extends QuerydslRepositorySupport implements ServiceDiscountDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public ServiceDiscountDslReposImpl() {
        super(ServiceDiscount.class);
    }

    private JPAQuery selectFromWhere(ServiceDiscount instance, QServiceDiscount qServiceDiscount) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qServiceDiscount);

        if (StringUtils.isNotEmpty(instance.getEventName())) {
            query.where(qServiceDiscount.eventName.contains(instance.getEventName()));
        }
        if (StringUtils.isNotEmpty(instance.getSellsTypeCode())) {
            query.where(qServiceDiscount.sellsTypeCode.eq(instance.getSellsTypeCode()));
        }
        if (StringUtils.isNotEmpty(instance.getServiceTypeCode())) {
            query.where(qServiceDiscount.serviceTypeCode.eq(instance.getServiceTypeCode()));
        }
        if (instance.getDiscountType() != null) {
            query.where(qServiceDiscount.discountType.eq(instance.getDiscountType()));
        }
        if (instance.getServiceId() > 0) {
            query.where(qServiceDiscount.serviceId.eq(instance.getServiceId()));
        }
        if (instance.getId() > 0) {
            query.where(qServiceDiscount.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<ServiceDiscount> findAll(ServiceDiscount instance, BfPage bfPage) {
        QServiceDiscount qServiceDiscount = QServiceDiscount.serviceDiscount;
        JPAQuery query = selectFromWhere(instance, qServiceDiscount);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QServiceDiscount.class, qServiceDiscount.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(ServiceDiscount instance) {
        QServiceDiscount qFile = QServiceDiscount.serviceDiscount;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
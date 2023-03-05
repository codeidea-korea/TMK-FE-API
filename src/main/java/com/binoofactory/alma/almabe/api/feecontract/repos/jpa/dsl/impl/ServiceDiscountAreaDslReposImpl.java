package com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.feecontract.model.entity.QServiceDiscountArea;
import com.binoofactory.alma.almabe.api.feecontract.model.entity.ServiceDiscountArea;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.ServiceDiscountAreaDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ServiceDiscountAreaDslReposImpl extends QuerydslRepositorySupport implements ServiceDiscountAreaDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public ServiceDiscountAreaDslReposImpl() {
        super(ServiceDiscountArea.class);
    }

    private JPAQuery selectFromWhere(ServiceDiscountArea instance, QServiceDiscountArea qServiceDiscountArea) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qServiceDiscountArea);

        if (StringUtils.isNotEmpty(instance.getBranchCode())) {
            query.where(qServiceDiscountArea.branchCode.eq(instance.getBranchCode()));
        }
        if (StringUtils.isNotEmpty(instance.getCitysCode())) {
            query.where(qServiceDiscountArea.citysCode.eq(instance.getCitysCode()));
        }
        if (StringUtils.isNotEmpty(instance.getNationalAuthemsCode())) {
            query.where(qServiceDiscountArea.nationalAuthemsCode.eq(instance.getNationalAuthemsCode()));
        }
        if (StringUtils.isNotEmpty(instance.getStatsCode())) {
            query.where(qServiceDiscountArea.statsCode.eq(instance.getStatsCode()));
        }
        if (instance.getServiceDiscountId() > 0) {
            query.where(qServiceDiscountArea.serviceDiscountId.eq(instance.getServiceDiscountId()));
        }
        if (instance.getId() > 0) {
            query.where(qServiceDiscountArea.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<ServiceDiscountArea> findAll(ServiceDiscountArea instance, BfPage bfPage) {
        QServiceDiscountArea qServiceDiscountArea = QServiceDiscountArea.serviceDiscountArea;
        JPAQuery query = selectFromWhere(instance, qServiceDiscountArea);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QServiceDiscountArea.class, qServiceDiscountArea.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(ServiceDiscountArea instance) {
        QServiceDiscountArea qFile = QServiceDiscountArea.serviceDiscountArea;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
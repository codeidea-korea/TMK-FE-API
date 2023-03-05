package com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.feecontract.model.entity.QServiceArea;
import com.binoofactory.alma.almabe.api.feecontract.model.entity.ServiceArea;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.ServiceAreaDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ServiceAreaDslReposImpl extends QuerydslRepositorySupport implements ServiceAreaDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public ServiceAreaDslReposImpl() {
        super(ServiceArea.class);
    }

    private JPAQuery selectFromWhere(ServiceArea instance, QServiceArea qServiceArea) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qServiceArea);

        if (StringUtils.isNotEmpty(instance.getBranchCode())) {
            query.where(qServiceArea.branchCode.eq(instance.getBranchCode()));
        }
        if (StringUtils.isNotEmpty(instance.getCitysCode())) {
            query.where(qServiceArea.citysCode.eq(instance.getCitysCode()));
        }
        if (StringUtils.isNotEmpty(instance.getNationalAuthemsCode())) {
            query.where(qServiceArea.nationalAuthemsCode.eq(instance.getNationalAuthemsCode()));
        }
        if (StringUtils.isNotEmpty(instance.getStatsCode())) {
            query.where(qServiceArea.statsCode.eq(instance.getStatsCode()));
        }
        if (instance.getServiceId() > 0) {
            query.where(qServiceArea.serviceId.eq(instance.getServiceId()));
        }
        if (instance.getId() > 0) {
            query.where(qServiceArea.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<ServiceArea> findAll(ServiceArea instance, BfPage bfPage) {
        QServiceArea qServiceArea = QServiceArea.serviceArea;
        JPAQuery query = selectFromWhere(instance, qServiceArea);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QServiceArea.class, qServiceArea.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(ServiceArea instance) {
        QServiceArea qFile = QServiceArea.serviceArea;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
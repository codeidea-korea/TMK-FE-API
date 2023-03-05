package com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.feecontract.model.entity.QServiceDetail;
import com.binoofactory.alma.almabe.api.feecontract.model.entity.ServiceDetail;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.ServiceDetailDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ServiceDetailDslReposImpl extends QuerydslRepositorySupport implements ServiceDetailDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public ServiceDetailDslReposImpl() {
        super(ServiceDetail.class);
    }

    private JPAQuery selectFromWhere(ServiceDetail instance, QServiceDetail qServiceDetail) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qServiceDetail);

        if (StringUtils.isNotEmpty(instance.getDescription())) {
            query.where(qServiceDetail.description.contains(instance.getDescription()));
        }
        if (StringUtils.isNotEmpty(instance.getName())) {
            query.where(qServiceDetail.name.contains(instance.getName()));
        }
        if (StringUtils.isNotEmpty(instance.getNameEng())) {
            query.where(qServiceDetail.nameEng.contains(instance.getNameEng()));
        }
        if (instance.getBrandId() > 0) {
            query.where(qServiceDetail.brandId.eq(instance.getBrandId()));
        }
        if (instance.getId() > 0) {
            query.where(qServiceDetail.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<ServiceDetail> findAll(ServiceDetail instance, BfPage bfPage) {
        QServiceDetail qServiceDetail = QServiceDetail.serviceDetail;
        JPAQuery query = selectFromWhere(instance, qServiceDetail);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QServiceDetail.class, qServiceDetail.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(ServiceDetail instance) {
        QServiceDetail qFile = QServiceDetail.serviceDetail;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
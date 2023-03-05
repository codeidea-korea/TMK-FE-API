package com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.feecontract.model.entity.QServiceOptions;
import com.binoofactory.alma.almabe.api.feecontract.model.entity.ServiceOptions;
import com.binoofactory.alma.almabe.api.feecontract.repos.jpa.dsl.ServiceOptionsDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class ServiceOptionsDslReposImpl extends QuerydslRepositorySupport implements ServiceOptionsDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public ServiceOptionsDslReposImpl() {
        super(ServiceOptions.class);
    }

    private JPAQuery selectFromWhere(ServiceOptions instance, QServiceOptions qServiceOptions) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qServiceOptions);

        if (StringUtils.isNotEmpty(instance.getOptionCode())) {
            query.where(qServiceOptions.optionCode.eq(instance.getOptionCode()));
        }
        if (instance.getOptionType() != null) {
            query.where(qServiceOptions.optionType.eq(instance.getOptionType()));
        }
        if (instance.getServiceId() > 0) {
            query.where(qServiceOptions.serviceId.eq(instance.getServiceId()));
        }
        if (instance.getId() > 0) {
            query.where(qServiceOptions.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<ServiceOptions> findAll(ServiceOptions instance, BfPage bfPage) {
        QServiceOptions qServiceOptions = QServiceOptions.serviceOptions;
        JPAQuery query = selectFromWhere(instance, qServiceOptions);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QServiceOptions.class, qServiceOptions.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(ServiceOptions instance) {
        QServiceOptions qFile = QServiceOptions.serviceOptions;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
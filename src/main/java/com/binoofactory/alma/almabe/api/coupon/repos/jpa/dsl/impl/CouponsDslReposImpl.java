package com.binoofactory.alma.almabe.api.coupon.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.coupon.model.entity.Coupons;
import com.binoofactory.alma.almabe.api.coupon.model.entity.QCoupons;
import com.binoofactory.alma.almabe.api.coupon.repos.jpa.dsl.CouponsDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CouponsDslReposImpl extends QuerydslRepositorySupport implements CouponsDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public CouponsDslReposImpl() {
        super(Coupons.class);
    }

    private JPAQuery selectFromWhere(Coupons instance, QCoupons qCoupons) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qCoupons);

        if (StringUtils.isNotEmpty(instance.getCouponIssuedConditionCode())) {
            query.where(qCoupons.couponIssuedConditionCode.eq(instance.getCouponIssuedConditionCode()));
        }
        if (StringUtils.isNotEmpty(instance.getCouponIssuedToCode())) {
            query.where(qCoupons.couponIssuedToCode.eq(instance.getCouponIssuedToCode()));
        }
        if (StringUtils.isNotEmpty(instance.getCouponIssuedTypeCode())) {
            query.where(qCoupons.couponIssuedTypeCode.eq(instance.getCouponIssuedTypeCode()));
        }
        if (StringUtils.isNotEmpty(instance.getCouponTypeCode())) {
            query.where(qCoupons.couponTypeCode.eq(instance.getCouponTypeCode()));
        }
        if (StringUtils.isNotEmpty(instance.getHeadOfficeCouponCode())) {
            query.where(qCoupons.headOfficeCouponCode.eq(instance.getHeadOfficeCouponCode()));
        }
        if (StringUtils.isNotEmpty(instance.getName())) {
            query.where(qCoupons.name.contains(instance.getName()));
        }
        if (instance.getBrandId() > 0) {
            query.where(qCoupons.brandId.eq(instance.getBrandId()));
        }
        if (instance.getId() > 0) {
            query.where(qCoupons.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<Coupons> findAll(Coupons instance, BfPage bfPage) {
        QCoupons qCoupons = QCoupons.coupons;
        JPAQuery query = selectFromWhere(instance, qCoupons);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QCoupons.class, qCoupons.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(Coupons instance) {
        QCoupons qFile = QCoupons.coupons;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
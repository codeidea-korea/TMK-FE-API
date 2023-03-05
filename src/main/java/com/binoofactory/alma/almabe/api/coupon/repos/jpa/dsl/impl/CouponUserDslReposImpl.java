package com.binoofactory.alma.almabe.api.coupon.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.coupon.model.entity.CouponUser;
import com.binoofactory.alma.almabe.api.coupon.model.entity.QCouponUser;
import com.binoofactory.alma.almabe.api.coupon.repos.jpa.dsl.CouponUserDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class CouponUserDslReposImpl extends QuerydslRepositorySupport implements CouponUserDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public CouponUserDslReposImpl() {
        super(CouponUser.class);
    }

    private JPAQuery selectFromWhere(CouponUser instance, QCouponUser qCouponUser) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qCouponUser);

        if (StringUtils.isNotEmpty(instance.getUserName())) {
            query.where(qCouponUser.userName.contains(instance.getUserName()));
        }
        if (instance.getUserId() > 0) {
            query.where(qCouponUser.userId.eq(instance.getUserId()));
        }
        if (instance.getCouponId() > 0) {
            query.where(qCouponUser.couponId.eq(instance.getCouponId()));
        }
        if (instance.getId() > 0) {
            query.where(qCouponUser.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<CouponUser> findAll(CouponUser instance, BfPage bfPage) {
        QCouponUser qCouponUser = QCouponUser.couponUser;
        JPAQuery query = selectFromWhere(instance, qCouponUser);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QCouponUser.class, qCouponUser.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(CouponUser instance) {
        QCouponUser qFile = QCouponUser.couponUser;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
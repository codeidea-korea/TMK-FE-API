package com.binoofactory.alma.almabe.api.csc.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.csc.model.OneonOneCounsel;
import com.binoofactory.alma.almabe.api.csc.model.QOneonOneCounsel;
import com.binoofactory.alma.almabe.api.csc.repos.jpa.dsl.OneonOneCounselDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class OneonOneCounselDslReposImpl extends QuerydslRepositorySupport implements OneonOneCounselDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public OneonOneCounselDslReposImpl() {
        super(OneonOneCounsel.class);
    }

    private JPAQuery selectFromWhere(OneonOneCounsel instance, QOneonOneCounsel qOneonOneCounsel) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qOneonOneCounsel);

        if (StringUtils.isNotEmpty(instance.getContents())) {
            query.where(qOneonOneCounsel.contents.contains(instance.getContents()));
        }
        if (StringUtils.isNotEmpty(instance.getAnswer())) {
            query.where(qOneonOneCounsel.answer.contains(instance.getAnswer()));
        }
        if (StringUtils.isNotEmpty(instance.getCounselCaseCode())) {
            query.where(qOneonOneCounsel.counselCaseCode.eq(instance.getCounselCaseCode()));
        }
        if (StringUtils.isNotEmpty(instance.getCounselStatusCode())) {
            query.where(qOneonOneCounsel.counselStatusCode.eq(instance.getCounselStatusCode()));
        }
        if (StringUtils.isNotEmpty(instance.getOneononeCounselTypeCode())) {
            query.where(qOneonOneCounsel.oneononeCounselTypeCode.eq(instance.getOneononeCounselTypeCode()));
        }
        if (StringUtils.isNotEmpty(instance.getTitle())) {
            query.where(qOneonOneCounsel.title.contains(instance.getTitle()));
        }
        if (StringUtils.isNotEmpty(instance.getUserName())) {
            query.where(qOneonOneCounsel.userName.contains(instance.getUserName()));
        }
        if (StringUtils.isNotEmpty(instance.getUserPhoneNo())) {
            query.where(qOneonOneCounsel.userPhoneNo.contains(instance.getUserPhoneNo()));
        }
        if (instance.getUserId() > 0) {
            query.where(qOneonOneCounsel.userId.eq(instance.getUserId()));
        }
        if (instance.getReservationId() > 0) {
            query.where(qOneonOneCounsel.reservationId.eq(instance.getReservationId()));
        }
        if (instance.getId() > 0) {
            query.where(qOneonOneCounsel.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<OneonOneCounsel> findAll(OneonOneCounsel instance, BfPage bfPage) {
        QOneonOneCounsel qOneonOneCounsel = QOneonOneCounsel.oneonOneCounsel;
        JPAQuery query = selectFromWhere(instance, qOneonOneCounsel);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QOneonOneCounsel.class, qOneonOneCounsel.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(OneonOneCounsel instance) {
        QOneonOneCounsel qFile = QOneonOneCounsel.oneonOneCounsel;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
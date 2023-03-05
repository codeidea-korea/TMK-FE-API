package com.binoofactory.alma.almabe.api.reservation.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.reservation.model.entity.Branchs;
import com.binoofactory.alma.almabe.api.reservation.model.entity.QBranchs;
import com.binoofactory.alma.almabe.api.reservation.repos.jpa.dsl.BranchsDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class BranchsDslReposImpl extends QuerydslRepositorySupport implements BranchsDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public BranchsDslReposImpl() {
        super(Branchs.class);
    }

    private JPAQuery selectFromWhere(Branchs instance, QBranchs qBranchs) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qBranchs);

        if (StringUtils.isNotEmpty(instance.getName())) {
            query.where(qBranchs.name.contains(instance.getName()));
        }
        if (StringUtils.isNotEmpty(instance.getNameEng())) {
            query.where(qBranchs.nameEng.contains(instance.getNameEng()));
        }
        if (StringUtils.isNotEmpty(instance.getCitysCode())) {
            query.where(qBranchs.citysCode.eq(instance.getCitysCode()));
        }
        if (StringUtils.isNotEmpty(instance.getNationalAuthemsCode())) {
            query.where(qBranchs.nationalAuthemsCode.eq(instance.getNationalAuthemsCode()));
        }
        if (StringUtils.isNotEmpty(instance.getStatsCode())) {
            query.where(qBranchs.statsCode.eq(instance.getStatsCode()));
        }
        if (instance.getId() > 0) {
            query.where(qBranchs.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<Branchs> findAll(Branchs instance, BfPage bfPage) {
        QBranchs qBranchs = QBranchs.branchs;
        JPAQuery query = selectFromWhere(instance, qBranchs);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QBranchs.class, qBranchs.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(Branchs instance) {
        QBranchs qFile = QBranchs.branchs;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
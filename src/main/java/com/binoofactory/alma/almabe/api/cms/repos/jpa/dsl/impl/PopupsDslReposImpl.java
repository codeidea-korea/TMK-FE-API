package com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.cms.model.entity.Popups;
import com.binoofactory.alma.almabe.api.cms.model.entity.QPopups;
import com.binoofactory.alma.almabe.api.cms.repos.jpa.dsl.PopupsDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class PopupsDslReposImpl extends QuerydslRepositorySupport implements PopupsDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public PopupsDslReposImpl() {
        super(Popups.class);
    }

    private JPAQuery selectFromWhere(Popups instance, QPopups qPopups) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qPopups);

        if (StringUtils.isNotEmpty(instance.getContents())) {
            query.where(qPopups.contents.contains(instance.getContents()));
        }
        if (StringUtils.isNotEmpty(instance.getTitle())) {
            query.where(qPopups.title.contains(instance.getTitle()));
        }
        if (StringUtils.isNotEmpty(instance.getPopupsTypeCode())) {
            query.where(qPopups.popupsTypeCode.eq(instance.getPopupsTypeCode()));
        }
        if (instance.getId() > 0) {
            query.where(qPopups.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<Popups> findAll(Popups instance, BfPage bfPage) {
        QPopups qPopups = QPopups.popups;
        JPAQuery query = selectFromWhere(instance, qPopups);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QPopups.class, qPopups.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(Popups instance) {
        QPopups qFile = QPopups.popups;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
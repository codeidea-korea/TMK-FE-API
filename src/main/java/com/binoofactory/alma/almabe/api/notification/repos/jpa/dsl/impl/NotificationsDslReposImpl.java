package com.binoofactory.alma.almabe.api.notification.repos.jpa.dsl.impl;

import java.util.List;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.apache.commons.lang3.StringUtils;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.data.jpa.repository.support.QuerydslRepositorySupport;
import org.springframework.stereotype.Repository;

import com.binoofactory.alma.almabe.api.notification.model.entity.Notifications;
import com.binoofactory.alma.almabe.api.notification.model.entity.QNotifications;
import com.binoofactory.alma.almabe.api.notification.repos.jpa.dsl.NotificationsDslRepos;
import com.binoofactory.alma.almabe.common.model.BfPage;
import com.querydsl.core.types.OrderSpecifier;
import com.querydsl.core.types.dsl.PathBuilder;
import com.querydsl.jpa.impl.JPAQuery;
import com.querydsl.jpa.impl.JPAQueryFactory;

@Repository
public class NotificationsDslReposImpl extends QuerydslRepositorySupport implements NotificationsDslRepos {

    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Autowired
    public NotificationsDslReposImpl() {
        super(Notifications.class);
    }

    private JPAQuery selectFromWhere(Notifications instance, QNotifications qNotifications) {
        JPAQueryFactory jpaQueryFactory = new JPAQueryFactory(entityManager);
        JPAQuery query = jpaQueryFactory.selectFrom(qNotifications);

        if (StringUtils.isNotEmpty(instance.getTitle())) {
            query.where(qNotifications.title.contains(instance.getTitle()));
        }
        if (StringUtils.isNotEmpty(instance.getContents())) {
            query.where(qNotifications.contents.contains(instance.getContents()));
        }
        if (instance.getId() > 0) {
            query.where(qNotifications.id.eq(instance.getId()));
        }
        return query;
    }

    @Override
    public List<Notifications> findAll(Notifications instance, BfPage bfPage) {
        QNotifications qNotifications = QNotifications.notifications;
        JPAQuery query = selectFromWhere(instance, qNotifications);

        query
            .offset(bfPage.getOffset())
            .limit(bfPage.getPageSize())
            .orderBy(new OrderSpecifier(com.querydsl.core.types.Order.DESC,
                new PathBuilder(QNotifications.class, qNotifications.id.getMetadata())));

        return query.fetch();
    }

    @Override
    public long countAll(Notifications instance) {
        QNotifications qFile = QNotifications.notifications;
        JPAQuery query = selectFromWhere(instance, qFile);
        return query.fetchCount();
    }
}
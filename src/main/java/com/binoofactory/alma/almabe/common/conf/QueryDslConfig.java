package com.binoofactory.alma.almabe.common.conf;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import org.springframework.context.annotation.Bean;
import org.springframework.context.annotation.Configuration;
import org.springframework.context.annotation.DependsOn;

import com.querydsl.jpa.impl.JPAQueryFactory;

@DependsOn(value = {"jasyptStringEncryptor"})
@Configuration
public class QueryDslConfig {
	
    @PersistenceContext(name = "entityManager")
    private EntityManager entityManager;

    @Bean(name = "jpaQueryFactory")
    public JPAQueryFactory jpaQueryFactory() {
        return new JPAQueryFactory(entityManager);
    }
}

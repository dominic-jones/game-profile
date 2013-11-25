package com.dv.game.impl;

import com.google.common.collect.Lists;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

@Component
@Transactional
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @PersistenceContext
    private EntityManager em;

    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Iterable<GrantedAuthority> roles = Lists.<GrantedAuthority>newArrayList(new SimpleGrantedAuthority("ROLE_USER"));
        em.persist(new User("user", "password", roles));
    }
}

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

    /*
     * Temporary data bootstrapping.
     *
     * This needs to be an ApplicationListener<ContextRefreshedEvent> so that all beans are loaded and initialised before
     * this executes, to take advantage of transaction support etc.
     *
     * An @PostConstruct method would be preferred, but it seems at that time it runs, all of Spring is not yet loaded.
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        Iterable<GrantedAuthority> roles = Lists.<GrantedAuthority>newArrayList(new SimpleGrantedAuthority("ROLE_USER"));
        User user = new User("user", "password", roles);
        System.out.println("@@@: " + user.getId());
        em.persist(user);

        roles = Lists.<GrantedAuthority>newArrayList(new SimpleGrantedAuthority("ROLE_USER"), new SimpleGrantedAuthority("ROLE_ADMIN"));
        user = new User("admin", "password", roles);
        System.out.println("@@@: " + user.getId());
        em.persist(user);
    }
}

package com.dv.game.impl;

import com.google.common.base.Function;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static com.google.common.collect.Iterables.transform;
import static java.util.Arrays.asList;

@Component
@Transactional
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @PersistenceContext
    private EntityManager em;

    private static Function<String, GrantedAuthority> toAuthority = new Function<String, GrantedAuthority>() {
        @Nullable
        @Override
        public GrantedAuthority apply(@Nullable String input) {

            return new SimpleGrantedAuthority(input);
        }
    };

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

        user("user", "password", asList("ROLE_USER"));

        user("admin", "password", asList("ROLE_USER", "ROLE_ADMIN"));
    }

    private void user(String userName,
                      String password,
                      Iterable<String> roles) {

        Iterable<GrantedAuthority> authorities = roles(roles);
        User user = new User(userName, password, authorities);
        em.persist(user);
    }

    private static Iterable<GrantedAuthority> roles(Iterable<String> roles) {

        return transform(roles, toAuthority);
    }

}

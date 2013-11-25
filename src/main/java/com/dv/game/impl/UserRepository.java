package com.dv.game.impl;

import com.google.common.base.Optional;
import org.springframework.stereotype.Repository;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import javax.persistence.criteria.CriteriaBuilder;
import javax.persistence.criteria.CriteriaQuery;
import javax.persistence.criteria.Root;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.collect.Iterables.isEmpty;

@Repository
public class UserRepository {

    @PersistenceContext
    private EntityManager em;

    Optional<User> findUserByName(String userName) {

        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<User> query = cb.createQuery(User.class);
        Root<User> rUser = query.from(User.class);
        query.select(rUser)
                .where(cb.equal(rUser.get("userName"), userName));

        Iterable<User> results = em.createQuery(query)
                .getResultList();

        if (isEmpty(results)) {
            return absent();
        } else {
            return of(results.iterator().next());
        }
    }

}

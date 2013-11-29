package com.dv.game.user;

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

    public Optional<User> findUserByName(String username) {

        //Named queries are slightly more optimal than criteria as they are preparsed
        //TODO 2013-11-29 Dom - Consider providing access to Criteria through injection
        CriteriaBuilder cb = em.getCriteriaBuilder();

        CriteriaQuery<User> qUser = cb.createQuery(User.class);
        Root<User> rUser = qUser.from(User.class);

        //TODO 2013-11-29 Dom - Consider applying Metamodel preparsing
        //The predicate is not refactor/type-safe, but this can be fixed through the metamodel plugin
        qUser.select(rUser)
                .where(cb.equal(rUser.get("username"), username));

        //getResultList() might be considered overkill for a single result, however, it is more comfortable to handle
        //our own 'not found' case than the exception thrown by getSingleResult().
        Iterable<User> results = em.createQuery(qUser)
                .getResultList();

        if (isEmpty(results)) {
            return absent();
        } else {
            return of(results.iterator().next());
        }
    }

    public void createUser(User user) {

        em.persist(user);
    }
}

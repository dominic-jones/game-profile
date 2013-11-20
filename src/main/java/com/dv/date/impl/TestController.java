package com.dv.date.impl;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Transactional
public class TestController {

    @PersistenceContext(unitName = "persistenceUnit")
    EntityManager em;

    @RequestMapping(value = "/a", method = GET)
    public String test(Model model) {

        model.addAttribute("model", new Location("Asia/Tokyo"));

        return "test";
    }

    @RequestMapping(value = "/b", method = GET)
    public String b() {

        Location alpha1 = new Location("Alpha");
        em.persist(alpha1);

        return "test";
    }
}

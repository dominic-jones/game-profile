package com.dv.game.impl;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Transactional
public class TestController {

    @PersistenceContext(unitName = "persistenceUnit")
    EntityManager em;

    @RequestMapping(value = "/a", method = GET)
    public String test() {

        return "test";
    }

    @RequestMapping(value = "/b", method = GET)
    public String b() {

        return "test";
    }

    //TODO 2013-11-23 Dom - Temporary
    @RequestMapping(value = "/login", method = GET)
    public String login() {

        return "login";
    }
}

package com.dv.game.impl;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.ui.Model;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.persistence.EntityManager;
import javax.persistence.PersistenceContext;
import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Transactional
public class TestController {

    @PersistenceContext(unitName = "persistenceUnit")
    EntityManager em;

    @RequestMapping(value = "/test", method = GET)
    public String test(Principal principal,
                       Model model) {

        model.addAttribute("username", principal.getName());

        return "test";
    }
}

package com.dv.game.test;

import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import java.security.Principal;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@Transactional
public class TestController {

    @RequestMapping(value = "/test", method = GET)
    public String test() {

        return "test";
    }

    /*
     * For anything more complicated, should use a proper view model.
     */
    @ModelAttribute("username")
    public String username(Principal principal) {

        return principal.getName();
    }
}

package com.dv.game.register;

import com.dv.game.user.User;
import com.dv.game.user.UserRepository;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Transactional
@RequestMapping("/register")
public class RegisterController {

    @Inject
    private UserRepository userRepository;

    @RequestMapping(method = GET)
    public String form() {

        return "register";
    }

    @RequestMapping(method = POST)
    public String register(RegisterEditModel editModel) {

        //TODO 2013-11-26 Dom - build the User better, perhaps through auto-mapper
        userRepository.createUser(new User(editModel.getUsername(), editModel.getPassword()));

        //TODO 2013-11-26 Dom - Make SpringSecurity log in the user after registering

        return "redirect:test";
    }
}

package com.dv.game.register;

import com.dv.game.user.User;
import com.dv.game.user.UserRepository;
import org.springframework.beans.propertyeditors.StringTrimmerEditor;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.WebDataBinder;
import org.springframework.web.bind.annotation.InitBinder;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.inject.Inject;
import javax.validation.Valid;

import static org.springframework.web.bind.annotation.RequestMethod.GET;
import static org.springframework.web.bind.annotation.RequestMethod.POST;

@Controller
@Transactional
@RequestMapping("/register")
public class RegisterController {

    @Inject
    private UserRepository userRepository;

    @Inject
    private PasswordEncoder passwordEncoder;

    @RequestMapping(method = GET)
    public String form() {

        return "register";
    }

    @RequestMapping(method = POST)
    public String register(@ModelAttribute("command") @Valid RegisterEditModel editModel,
                           BindingResult result) {

        if (result.hasErrors()) {
            return "register";
        }

        userRepository.createUser(newUser(editModel));

        //TODO 2013-11-26 Dom - Make SpringSecurity login the user after registering (and check security implications of this)

        return "redirect:test";
    }

    //TODO 2013-11-26 Dom - build the User better, perhaps through auto-mapper
    //TODO 2013-11-29 Dom - PasswordEncoder should not be here, not a controller task
    private User newUser(RegisterEditModel editModel) {

        return new User(editModel.getUsername(), passwordEncoder.encode(editModel.getPassword()));
    }

    @ModelAttribute("command")
    public RegisterEditModel command() {

        return new RegisterEditModel();
    }

    @InitBinder
    public void initBinder(WebDataBinder binder) {

        binder.registerCustomEditor(String.class, new StringTrimmerEditor(false));
    }
}

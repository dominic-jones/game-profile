package com.dv.game.login;

import org.springframework.stereotype.Controller;
import org.springframework.validation.BindingResult;
import org.springframework.validation.ObjectError;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import static org.springframework.web.bind.annotation.RequestMethod.GET;

@Controller
@RequestMapping("/login")
public class LoginController {

    @RequestMapping(method = GET)
    public String login() {

        return "login";
    }

    @RequestMapping(method = GET, params = "error")
    public String error(@ModelAttribute("command") LoginEditModel command,
                        BindingResult result) {

        //TODO 2013-11-29 Dom - i18n properties this message
        result.addError(new ObjectError("command", "Invalid username or credentials"));
        return "login";
    }

    @ModelAttribute("command")
    public LoginEditModel command() {

        return new LoginEditModel();
    }

}

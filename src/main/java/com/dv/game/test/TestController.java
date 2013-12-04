package com.dv.game.test;

import com.dv.game.characters.Character;
import com.dv.game.user.User;
import com.dv.game.user.UserRepository;
import com.google.common.base.Function;
import com.google.common.base.Optional;
import org.springframework.stereotype.Controller;
import org.springframework.transaction.annotation.Transactional;
import org.springframework.web.bind.annotation.ModelAttribute;
import org.springframework.web.bind.annotation.RequestMapping;

import javax.annotation.Nullable;
import javax.inject.Inject;
import java.security.Principal;

import static com.google.common.collect.Iterables.transform;
import static com.google.common.collect.Sets.newHashSet;
import static org.springframework.web.bind.annotation.RequestMethod.GET;

//TODO 2013-12-04 Dom - Refactory name/paths to better match intent
@Controller
@Transactional(readOnly = true)
public class TestController {

    @Inject
    private UserRepository userRepository;

    @RequestMapping(value = "/test", method = GET)
    public String profile() {

        return "test";
    }

    /*
     * For anything more complicated, should use a proper view model.
     */
    @ModelAttribute("model")
    public ProfileViewModel model(Principal principal) {

        ProfileViewModel model = new ProfileViewModel();

        Optional<User> optionalUser = userRepository.findUserByName(principal.getName());

        //TODO 2013-12-04 Dom - Map to the view model with ModelMapper
        User user = optionalUser.get();
        model.setUsername(user.getUsername());
        model.setCharacterNames(newHashSet(transform(user.getCharacters(), new Function<Character, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Character character) {

                assert character != null;
                return character.getCharacterName();
            }
        })));

        return model;
    }

}

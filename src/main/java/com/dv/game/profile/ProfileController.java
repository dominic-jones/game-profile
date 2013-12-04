package com.dv.game.profile;

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

@Controller
@Transactional(readOnly = true)
@RequestMapping("/profile")
public class ProfileController {

    @Inject
    private UserRepository userRepository;

    @RequestMapping(method = GET)
    public String profile() {

        return "profile";
    }

    //TODO 2013-12-04 Dom - Consider more robust error checking for logged-in user access
    /*
     * Since we are logged in, we must have a user principal name, and therefore a user. If for whatever reason,
     * we do not, this is a fatal exception. This waits until the development of global error pages.
     */
    @ModelAttribute("model")
    public ProfileViewModel model(Principal principal) {

        ProfileViewModel model = new ProfileViewModel();

        Optional<User> optionalUser = userRepository.findUserByName(principal.getName());

        User user = optionalUser.get();

        //TODO 2013-12-04 Dom - Map to the view model with ModelMapper
        model.setUsername(user.getUsername());

        //Guava transformers are lazy, so a newHashSet is required to perform the copy while still in-session
        model.setCharacterNames(newHashSet(transform(user.getCharacters(), toName())));

        return model;
    }

    //Will become a lambda
    private static Function<Character, String> toName() {

        return new Function<Character, String>() {
            @Nullable
            @Override
            public String apply(@Nullable Character character) {

                assert character != null;
                return character.getCharacterName();
            }
        };
    }

}

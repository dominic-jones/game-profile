package com.dv.game.bootstrap;

import com.dv.game.user.User;
import com.dv.game.user.UserRepository;
import com.google.common.base.Function;
import org.springframework.context.ApplicationListener;
import org.springframework.context.event.ContextRefreshedEvent;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.crypto.password.PasswordEncoder;
import org.springframework.stereotype.Component;
import org.springframework.transaction.annotation.Transactional;

import javax.annotation.Nullable;
import javax.inject.Inject;

import static com.google.common.collect.Iterables.transform;
import static java.util.Arrays.asList;

@Component
@Transactional
public class DataBootstrap implements ApplicationListener<ContextRefreshedEvent> {

    @Inject
    private PasswordEncoder passwordEncoder;

    @Inject
    private UserRepository userRepository;

    private static final Function<String, GrantedAuthority> toAuthority = new Function<String, GrantedAuthority>() {
        @Nullable
        @Override
        public GrantedAuthority apply(@Nullable String input) {

            return new SimpleGrantedAuthority(input);
        }
    };

    /*
     * Temporary data bootstrapping.
     *
     * This needs to be an ApplicationListener<ContextRefreshedEvent> so that all beans are loaded and initialised before
     * this executes, to take advantage of transaction support etc.
     *
     * An @PostConstruct method would be preferred, but it seems that at that time it runs, all of Spring is not yet loaded.
     */
    @Override
    public void onApplicationEvent(ContextRefreshedEvent event) {

        //Prevent double bootstrap
        String systemUserName = "user";
        if (userRepository.findUserByName(systemUserName).isPresent()) {
            return;
        }

        user(systemUserName, "password", asList("ROLE_USER"));

        user("admin", "password", asList("ROLE_USER", "ROLE_ADMIN"));
    }

    private void user(String username,
                      String password,
                      Iterable<String> roles) {

        Iterable<GrantedAuthority> authorities = transform(roles, toAuthority);
        //TODO 2013-11-29 Dom - This should follow the exact same flow as the RegisterController.
        User user = new User(username, passwordEncoder.encode(password), authorities);
        userRepository.createUser(user);
    }

}

package com.dv.game.impl;

import com.google.common.base.Optional;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.User;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import static com.google.common.base.Optional.absent;
import static com.google.common.base.Optional.of;
import static com.google.common.collect.Lists.newArrayList;

@Service
public class UserDetailsServiceImpl implements UserDetailsService {

    public UserDetails loadUserByUsername(String userName)
            throws UsernameNotFoundException, DataAccessException {

        System.out.println("@@@ " + userName);

        Optional<User> user = getUser(userName); //Load user from database
        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found: " + userName);
        }
        return user.get();
    }

    private Optional<User> getUser(String userName) {

        if ("user".equals(userName)) {
            return of(new User("user", "password", newArrayList(new SimpleGrantedAuthority("ROLE_USER"))));
        }

        if ("admin".equals(userName)) {
            return of(new User("admin", "password", newArrayList(
                    new SimpleGrantedAuthority("ROLE_USER"),
                    new SimpleGrantedAuthority("ROLE_ADMIN")
            )));
        }

        return absent();
    }
}

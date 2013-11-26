package com.dv.game.impl;

import com.google.common.base.Optional;
import org.springframework.dao.DataAccessException;
import org.springframework.security.core.userdetails.UserDetails;
import org.springframework.security.core.userdetails.UserDetailsService;
import org.springframework.security.core.userdetails.UsernameNotFoundException;
import org.springframework.stereotype.Service;

import javax.inject.Inject;
import javax.transaction.Transactional;

@Service
@Transactional
public class UserDetailsServiceImpl implements UserDetailsService {

    @Inject
    private UserRepository userRepository;

    public UserDetails loadUserByUsername(String username)
            throws UsernameNotFoundException, DataAccessException {

        Optional<User> user = getUser(username);

        if (!user.isPresent()) {
            throw new UsernameNotFoundException("User not found: " + username);
        }
        return user.get();
    }

    private Optional<User> getUser(String username) {

        return userRepository.findUserByName(username);
    }
}

package com.dv.game.user;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.Id;
import java.util.Collection;
import java.util.UUID;

import static com.fasterxml.uuid.Generators.timeBasedGenerator;
import static com.google.common.base.Splitter.on;
import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Iterables.transform;

@Entity
public class User implements UserDetails {

    @Id
    private final UUID id;

    private String username;
    private String password;
    //TODO 2013-11-26 Dom - This should be a mapping to some other role entity
    private String tempAuthorities;

    protected User() {

        id = timeBasedGenerator().generate();
    }

    //TODO 2013-11-26 Dom - Replace daisy-chained constructors with builder or auto-mapper
    public User(String username,
                String password) {

        this();
        this.username = username;
        this.password = password;
        this.tempAuthorities = "ROLE_USER";
    }

    public User(String username,
                String password,
                Iterable<GrantedAuthority> authorities) {

        this(username, password);
        this.tempAuthorities = Iterables.toString(authorities);
    }

    public UUID getId() {

        return id;
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        String trimBrackets = tempAuthorities
                .replaceAll("\\[", "")
                .replaceAll("]", "");

        Iterable<String> tokens = on(",").split(trimBrackets);
        return copyOf(transform(tokens, new Function<String, GrantedAuthority>() {
            @Nullable
            @Override
            public GrantedAuthority apply(@Nullable String s) {

                return new SimpleGrantedAuthority(s);
            }
        }));
    }

    @Override
    public String getPassword() {

        return password;
    }

    @Override
    public String getUsername() {

        return username;
    }

    @Override
    public boolean isAccountNonExpired() {

        return true;
    }

    @Override
    public boolean isAccountNonLocked() {

        return true;
    }

    @Override
    public boolean isCredentialsNonExpired() {

        return true;
    }

    @Override
    public boolean isEnabled() {

        return true;
    }
}

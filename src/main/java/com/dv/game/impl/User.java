package com.dv.game.impl;

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
    private UUID id;

    private String userName;
    private String password;
    private String tempAuthorities;

    protected User() {

        id = timeBasedGenerator().generate();
    }

    public User(String userName,
                String password,
                Iterable<GrantedAuthority> authorities) {

        this();
        this.userName = userName;
        this.password = password;
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

        return userName;
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

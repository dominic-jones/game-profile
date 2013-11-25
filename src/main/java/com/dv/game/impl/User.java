package com.dv.game.impl;

import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import org.hibernate.annotations.GenericGenerator;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.GeneratedValue;
import javax.persistence.Id;
import java.util.Collection;

import static com.google.common.base.Splitter.on;
import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Iterables.transform;

@Entity
public class User implements UserDetails {

    @Id
    @GenericGenerator(name = "table-hilo-generator", strategy = "org.hibernate.id.TableHiLoGenerator")
    @GeneratedValue(generator = "table-hilo-generator")
    private Long id;

    private String userName;
    private String password;
    private String tempAuthorities;

    protected User() {

    }

    public User(String userName,
                String password,
                Iterable<GrantedAuthority> authorities) {

        this.userName = userName;
        this.password = password;
        this.tempAuthorities = Iterables.toString(authorities);
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

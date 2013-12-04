package com.dv.game.user;

import com.dv.game.characters.Character;
import com.google.common.base.Function;
import com.google.common.collect.Iterables;
import org.springframework.security.core.GrantedAuthority;
import org.springframework.security.core.authority.SimpleGrantedAuthority;
import org.springframework.security.core.userdetails.UserDetails;

import javax.annotation.Nullable;
import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.OneToMany;
import java.util.Collection;
import java.util.HashSet;
import java.util.Set;
import java.util.UUID;

import static com.fasterxml.uuid.Generators.timeBasedGenerator;
import static com.google.common.base.Splitter.on;
import static com.google.common.collect.ImmutableList.copyOf;
import static com.google.common.collect.Iterables.transform;
import static javax.persistence.CascadeType.ALL;
import static javax.persistence.CascadeType.DETACH;
import static javax.persistence.CascadeType.MERGE;
import static javax.persistence.CascadeType.PERSIST;

@Entity
public class User implements UserDetails {

    @Id
    private final UUID id;

    private String username;
    private String password;
    //TODO 2013-11-26 Dom - This should be a mapping to some other role Entity
    private String tempAuthorities;

    @OneToMany(mappedBy = "user", cascade = {PERSIST, MERGE})
    private Set<Character> characters = new HashSet<>();

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

    public void addCharacter(String characterName) {

        characters.add(new Character(this, characterName));
    }

    @Override
    public Collection<? extends GrantedAuthority> getAuthorities() {

        //TODO 2013-11-30 Dom - Replace with proper role mappings
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

    public Set<Character> getCharacters() {

        return characters;
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

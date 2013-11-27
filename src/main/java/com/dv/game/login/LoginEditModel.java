package com.dv.game.login;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

//TODO 2013-11-27 Dom - Probably do not want to do the same bean validation as we do on registration.
public class LoginEditModel {

    @NotNull
    //TODO 2013-11-27 Dom - Length is temporary, only here to spike bean validation
    @Length(min = 4, max = 10)
    public String username;

    @NotNull
    //TODO 2013-11-27 Dom - Length is temporary, only here to spike bean validation
    @Length(min = 8)
    private String password;

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public String getPassword() {

        return password;
    }

    public void setPassword(String password) {

        this.password = password;
    }
}

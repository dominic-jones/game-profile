package com.dv.game.register;

import org.hibernate.validator.constraints.Length;

import javax.validation.constraints.NotNull;

public class RegisterEditModel {

    @NotNull
    @Length(min = 5)
    private String username;

    @NotNull
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

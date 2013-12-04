package com.dv.game.profile;

public class ProfileViewModel {

    private String username;

    private Iterable<String> characterNames;

    public String getUsername() {

        return username;
    }

    public void setUsername(String username) {

        this.username = username;
    }

    public Iterable<String> getCharacterNames() {

        return characterNames;
    }

    public void setCharacterNames(Iterable<String> characterNames) {

        this.characterNames = characterNames;
    }
}

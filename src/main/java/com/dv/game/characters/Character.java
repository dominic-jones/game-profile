package com.dv.game.characters;

import com.dv.game.user.User;

import javax.persistence.Entity;
import javax.persistence.Id;
import javax.persistence.ManyToOne;
import java.util.UUID;

import static com.fasterxml.uuid.Generators.timeBasedGenerator;

@Entity
public class Character {

    @Id
    private final UUID id;

    @ManyToOne(optional = false)
    private User user;

    private String characterName;

    protected Character() {

        id = timeBasedGenerator().generate();
    }

    public Character(User user,
                     String characterName) {

        this();
        this.user = user;
        this.characterName = characterName;
    }

    public UUID getId() {

        return id;
    }

    public User getUser() {

        return user;
    }

    public String getCharacterName() {

        return characterName;
    }
}

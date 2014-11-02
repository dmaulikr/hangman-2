package xyz.luan.games.hangman.game;

import java.io.Serializable;
import java.util.Set;

import xyz.luan.games.hangman.spells.Spell;

public class Profile implements Serializable {

    private static final long serialVersionUID = -5583798152789871501L;

    private String username;
    private String passwordHash;
    private int avatarCode;

    private int points;
    private Set<Spell> grimoire;

    // stats
    private int gamePlayed;
    private double hoursLoggedIn;
    private double hoursPlayed;

    public String getName() {
        return this.username;
    }

    public int getAvatarCode() {
        return this.avatarCode;
    }
}
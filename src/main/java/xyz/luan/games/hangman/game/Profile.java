package xyz.luan.games.hangman.game;

import java.io.Serializable;
import java.util.HashSet;
import java.util.Set;

import xyz.luan.games.hangman.spells.Spell;

public class Profile implements Serializable {

    private static final long serialVersionUID = -5583798152789871501L;

    private String username;
    private int avatarCode;

    private int points;
    private Set<Spell> grimoire;

    private ProfileStatuses statuses;

    public Profile(String username, int avatarCode) {
        this.username = username;
        this.avatarCode = avatarCode;

        this.grimoire = new HashSet<>();
        this.points = 0;
        this.statuses = new ProfileStatuses();
    }

    public String getName() {
        return this.username;
    }

    public int getAvatarCode() {
        return this.avatarCode;
    }
}
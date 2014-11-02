package xyz.luan.games.hangman.client;

import java.util.List;

import xyz.luan.games.hangman.game.Profile;
import xyz.luan.games.hangman.spells.Spell;

public class ConfiguredPlayer {

    private Profile profile;
    private List<Spell> spells;

    public Profile getProfile() {
        return this.profile;
    }

    public List<Spell> getSpells() {
        return this.spells;
    }
}
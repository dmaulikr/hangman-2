package xyz.luan.games.hangman.duel;

import java.util.List;

import xyz.luan.games.hangman.game.Profile;
import xyz.luan.games.hangman.spells.Spell;

public class Player {

    private Profile profile;
    private Letter[] keyboard;

    private Spell[] spells;
    private Letter.Status[] spellStatus;

    private double mana;
    private int lives;

    public Player(DuelOptions options, Profile profile, List<Spell> spells) {
        if (spells.size() > options.getSpellSize()) {
            throw new IllegalArgumentException("Must have at max " + options.getSpellSize() + " spells");
        }
        this.spells = new Spell[options.getSpellSize()];
        this.spellStatus = new Letter.Status[options.getSpellSize()];
        for (int i = 0; i < spells.size(); i++) {
            this.spells[i] = spells.get(i);
        }

        this.profile = profile;

        this.mana = options.getStartingMana();
        this.lives = options.getStartingLives();

        this.keyboard = Letter.getKeyboard(this);
    }

    public Profile getProfile() {
        return this.profile;
    }

    public int getLives() {
        return this.lives;
    }

    public double getMana() {
        return this.mana;
    }

}
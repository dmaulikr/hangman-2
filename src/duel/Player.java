package duel;

import java.util.*;

import game.Profile;
import spells.Spell;
import drawing.*;

public class Player {

  private Profile profile;
  private Letter[] keyboard;

  private Spell[] spells;
  private Letter.Status[] spellStatus;

  private double mana;
  private int lives;

  private PlayerColor color;

  //synced drawings
  private Screen screen;

  public Player(DuelOptions options, Profile profile, PlayerColor color, List<Spell> spells) {
    if (spells.size() > options.getSpellSize()) {
      throw new IllegalArgumentException("Must have at max " + options.getSpellSize() + " spells");
    }
    this.spells = new Spell[options.getSpellSize()];
    this.spellStatus = new Letter.Status[options.getSpellSize()];
    for (int i = 0; i < spells.size(); i++) {
      this.spells[i] = spells.get(i);
    }

    this.color = color;
    this.profile = profile;

    this.mana = options.getStartingMana();
    this.lives = options.getStartingLives();

    this.keyboard = Letter.getKeyboard(this);
  }

  public void setupDrawings(Duel duel) {
    this.screen = new Screen(duel, this);
  }

  public Profile getProfile() {
    return this.profile;
  }

  public PlayerColor getColor() {
    return color;
  }

  public int getLives() {
    return this.lives;
  }

  public double getMana() {
    return this.mana;
  }

  public Drawing[] getDrawingsForSpells(Player p) {
    Drawing[] results = new Drawing[spells.length];
    for (int i = 0; i < spells.length; i++) {
      if (spells[i] == null) {
        results[i] = null;
      } else {
        results[i] = new SpellDrawing(color, (spellStatus[i] == Letter.Status.REVEALED || p == this) ? spells[i] : null, spellStatus[i]);
      }
    }
    return results;
  }

  public Drawing[] getDrawingsForKeyboard() {
    Drawing[] results = new Drawing[keyboard.length];
    for (int i = 0; i < keyboard.length; i++) {
      results[i] = keyboard[i].getDrawing();
    }
    return results;
  }

}
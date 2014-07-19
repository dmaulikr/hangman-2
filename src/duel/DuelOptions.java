package duel;

import drawing.Color;

public class DuelOptions {
  
  private int startingLives;
  private double startingMana;
  private int spellSize;

  private Color[] colorOrder;

  public Color[] getColorOrder() {
    return this.colorOrder;
  }

  public int getStartingLives() {
    return this.startingLives;
  }

  public double getStartingMana() {
    return this.startingMana;
  }

  public int getSpellSize() {
    return this.spellSize;
  }

}
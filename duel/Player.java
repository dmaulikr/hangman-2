package duel;

import game.Profile;

public class Player {

  private Profile profile;
  private Letter[] keyboard;
  private Spell[] spells;

  private float mana;
  private int lives;

  private drawing.Color color;

  public void renderSpells(Graphics2D g, int x, int y) {
    // TODO
  }

  public void renderKeyboard(Graphics2D g, int x, int y) {
    int nx = x, ny = y;
    for (int i = 0; i < keyboard.length; i++) {
      keyboard[i].render(g, nx, ny);
      nx += 0; // TODO ?
      ny += 0; // TODO ?
    }
  }

}
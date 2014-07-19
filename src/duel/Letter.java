package duel;

import java.awt.*;
import drawing.Drawing;
import drawing.LetterDrawing;

public class Letter {

  public static final int SIZE = 32;
  public static final char[] SPECIAL = {'-', ',', ';', '.', '!', '?', '(', ')', '"'};

  private char letter;
  private Status status;
  private Player owner;

  public Letter(char letter, Status status, Player owner) {
    this.letter = letter;
    this.status = status;
    this.owner = owner;
  }

  public static Letter forWord(char c) {
    return new Letter(c, Status.UNREVEALED, null);
  }

  public static Letter forKeyboard(char c, Player p) {
    return new Letter(c, Status.REVEALED, p);
  }

  public static Letter[] getKeyboard(Player p) {
    final int DIFF = ('Z' - 'A');
    Letter[] letters = new Letter [DIFF + SPECIAL.length];
    for (char c = 'A'; c <= 'Z'; c++) {
      letters[c - 'A'] = forKeyboard(c, p);
    }

    for (int i = 0; i < SPECIAL.length; i++) {
      letters[DIFF + i] = forKeyboard(SPECIAL[i], p);
    }

    return letters;
  }
  
  public enum Status {
    UNREVEALED(0, 'u'), REVEALED(1, 'r'), DESTROYED(-1, 'd');

    private int code;
    private char ref;

    private Status(int code, char ref) {
      this.code = code;
      this.ref = ref;
    }
  }

  public void setStatus(Status newStatus) {
    this.status = newStatus;
  }

  public void setOwner(Player owner) {
    this.owner = owner;
  }

  public boolean is(char c) {
    return String.valueOf(this.letter).equalsIgnoreCase(String.valueOf(c));
  }

  public boolean revealed() {
    return this.status == Status.REVEALED;
  }

  public Drawing getDrawing() {
    return new LetterDrawing(owner.getColor(), revealed() ? new Character(letter) : null, status);
  }
}
package duel;

import java.util.*;

import game.Profile;
import drawing.*;
import hub.ConfiguredPlayer;

public class Duel {

  private DuelOptions options;
  private Word word;
  private Player[] players;

  private int currentTurn;

  public Duel(DuelOptions options, List<ConfiguredPlayer> profiles) {
    this.options = options;
    Color[] colors = this.options.getColorOrder();
    if (profiles.size() > colors.length) {
      throw new IllegalArgumentException("You can't have more players than colors.");
    }

    this.players = new Player[profiles.size()];
    for (int i = 0; i < this.players.length; i++) {
      this.players[i] = new Player(options, profiles.get(i).getProfile(), colors[i], profiles.get(i).getSpells());
    }

    this.word = null; // TODO
    this.currentTurn = 0;
  }

  public int getPlayerCode(Player p) {
    for (int i = 0; i < players.length; i++) {
      if (players[i] == p) {
        return i;
      }
    }
    return -1;
  }
}
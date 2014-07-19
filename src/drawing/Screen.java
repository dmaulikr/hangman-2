package drawing;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.*;

import duel.Duel;
import duel.Player;
import duel.Phase;

public class Screen implements Serializable {

  private int viewPlayer;
  private int currentTurn;
  private int currentPlayer;
  private Phase currentPhase;

  private List<Drawing> word;
  private PlayerPanel[] playerPanels;

  public Screen(Duel duel, Player player) {
    this.viewPlayer = duel.getPlayerCode(player);
  }

  public void render(Graphics2D g) {
    
  }

  public void renderViewPlayer(Graphics2D g, int x, int y, int tileSize) {
    playerPanels[viewPlayer].render(g, x, y, tileSize);
  }

  public void renderWord(Graphics2D g, int x, int y, int w, int tileSize) {
    int nx = x, ny = y;
    int length = word.size();

    if (length*tileSize > w) {
      int rest = w % length*tileSize;
      if (rest != 0) {
        w -= rest;
      }
    }

    int reajust = (w - length*tileSize) / 2;
    if (w < 0) { //not last line
      w = 0;
    }

    for (int i = 0; i < length; i++) {
      word.get(i).render(g, nx + reajust, ny, tileSize);
      nx += tileSize;
      if (nx >= x + w) {
        nx = 0;
        ny += tileSize;

        reajust = (w - (length - i)*tileSize) / 2;
        if (w < 0) { //not last line
          w = 0;
        }
      }
    }
  }

}
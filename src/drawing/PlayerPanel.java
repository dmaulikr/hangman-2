package drawing;

import java.io.Serializable;
import java.util.*;
import java.awt.Graphics2D;

import duel.Player;

public class PlayerPanel implements Serializable {

  private List<Drawing> keyboard;
  private List<Drawing> spells;
  private PlayerChartDrawing chart;

  public PlayerPanel(Player owner, Player viewer) {
    this.keyboard = Arrays.asList(owner.getDrawingsForKeyboard());
    this.spells = Arrays.asList(owner.getDrawingsForSpells(viewer));
    this.chart = new PlayerChartDrawing(owner);
  }

  public void render(Graphics2D g, int x, int y, int tileSize) {
    // TODO
  }
}
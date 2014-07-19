package drawing;

import java.awt.*;

import duel.Player;

public class PlayerChartDrawing extends Drawing {
  
  private String name;

  private int avatarCode;
  private int lives, points;
  private double mana;

  public PlayerChartDrawing(Player p) {
    super(p.getColor());
    this.name = p.getProfile().getName();
    this.avatarCode = p.getProfile().getAvatarCode();
    this.lives = p.getLives();
    this.mana = p.getMana();
  }

  public void render(Graphics2D g, int x, int y, int tileSize) {
    //TODO
  }

}
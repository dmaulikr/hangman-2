package drawing;

import java.io.Serializable;
import java.awt.Graphics2D;

public abstract class Drawing implements Serializable {

  private Color ownerColor;

  public Drawing(Color ownerColor) {
    this.ownerColor = ownerColor;
  }

  public abstract void render(Graphics2D g, int x, int y, int tileSize);
}
package drawing;

import duel.Letter;
import java.awt.*;

public class LetterDrawing extends Drawing {
  
  private Character c;
  private Letter.Status status;

  public LetterDrawing(PlayerColor ownerColor, Character c, Letter.Status status) {
    super(ownerColor);
    this.c = c;
    this.status = status;
  }

  public void render(Graphics2D g, int x, int y, int tileSize) {
    //TODO
  }
}
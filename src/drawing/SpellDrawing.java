package drawing;

import java.awt.*;
import spells.Spell;
import duel.Letter;

public class SpellDrawing extends Drawing {

  private Spell spell;
  private Letter.Status status;

  public SpellDrawing(PlayerColor color, Spell spell, Letter.Status status) {
    super(color);
    this.spell = spell;
    this.status = status;
  }

  public void render(Graphics2D g, int x, int y, int tileSize) {
    //TODO
  }

}
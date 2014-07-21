package game.panels;

import java.awt.event.*;
import java.awt.Graphics2D;

import game.*;

public abstract class Panel {
  
  public abstract void render(Graphics2D g, int width, int height);

  public abstract void handleMouseMove(MouseEvent e);
  public abstract GameStatus handleMouseClick(MouseEvent e);
  public abstract GameStatus handleKeyPressed(KeyEvent e);
}
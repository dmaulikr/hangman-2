package game.panels;

import javax.swing.*;
import java.awt.event.*;
import java.awt.Graphics2D;

import game.*;
import static texture.PackManager.pack;
import texture.TexturePack.Aligment;

public class GeneralConfigPanel extends Panel {

  private JTextField textField;
  private JPanel resourcePanel;

  public GeneralConfigPanel() {
    textField = new JTextField("This is a test");

    resourcePanel = new JPanel();
    resourcePanel.add(textField);
  }

  public GameStatus handleKeyPressed(KeyEvent e) {
    return null; //default, stays on screen
  }

  public void handleMouseMove(MouseEvent e) {
  }

  public GameStatus handleMouseClick(MouseEvent e) {
    return null;
  }

  public void render(Graphics2D g, int width, int height) {
    resourcePanel.setSize(width, height);
    resourcePanel.paint(g);
  }
}
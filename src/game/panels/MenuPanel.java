package game.panels;

import java.awt.event.*;
import java.awt.Graphics2D;
import java.awt.Rectangle;

import game.*;
import static texture.PackManager.pack;
import texture.TexturePack.Aligment;

public class MenuPanel extends Panel {
  private static final String[] ITEMS = { "host", "connect", "options", "exit" };
  private static final GameStatus[] ACTIONS = { GameStatus.SERVER_CONFIG, GameStatus.CONNECT, GameStatus.GENERAL_CONFIG, GameStatus.QUIT };
  
  private int selection;
  private Rectangle[] areas;

  public MenuPanel() {
    this.selection = 0;
    this.areas = new Rectangle[ITEMS.length];
  }

  public void handleMouseMove(MouseEvent e) {
    for (int i = 0; i < this.areas.length; i++) {
      if (areas[i].contains(e.getX(), e.getY())) {
        this.selection = i;
        break;
      }
    }
  }

  public GameStatus handleMouseClick(MouseEvent e) {
    for (int i = 0; i < this.areas.length; i++) {
      if (areas[i].contains(e.getX(), e.getY())) {
        return ACTIONS[i];
      }
    }
    return null; //outside
  }

  public GameStatus handleKeyPressed(KeyEvent e) {
    int keyCode = e.getKeyCode();
    switch (keyCode) {
      case KeyEvent.VK_UP:
        if (selection > 0) {
          selection--;
        }
        break;
      case KeyEvent.VK_DOWN:
        if (selection < ITEMS.length - 1) {
          selection++;
        }
        break;
      case KeyEvent.VK_ENTER:
        return ACTIONS[selection];
    }
    return null; //default, stays on screen
  }

  public void render(Graphics2D g, int width, int height) {
    pack().selectFont(g, texture.Font.GAME_TITLE);
    pack().selectColor(g, "main.title");
    pack().drawString(g, I18n.t("main.title"), 10, 10, Aligment.START, Aligment.START);

    pack().selectFont(g, texture.Font.MENU);
    int lineHeight = g.getFontMetrics().getHeight();
    for (int i = 0; i < ITEMS.length; i++) {
      pack().selectColor(g, i == selection ?  "main.menu.selected" : "main.menu.default");
      this.areas[i] = pack().drawString(g, I18n.t("main.menu." + ITEMS[i]), width - 10, 50 + i*(lineHeight + 5), Aligment.END, Aligment.START);
    }
  }
}
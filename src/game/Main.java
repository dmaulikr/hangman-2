package game;

import java.awt.*;
import java.awt.event.*;
import javax.swing.*;
import java.util.*;
import java.io.*;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.*;
import texture.PackManager;
import texture.TexturePack;

public class Main extends JFrame {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  // 1 - client | 2 - server | 3 - both
  private static int mode;

  private GameStatus status;

  public static void halt(String message) {
    System.err.println(message);
    System.exit(1);
  }

  private static void parseMode(String[] args) {
    if (args.length > 1) {
      halt("Invalid options. Must be run with program [clientOnly | serverOnly].");
    }
    if (args.length == 0) {
      mode = 3;
    } else {
      if (args[0].equalsIgnoreCase("clientOnly")) {
        mode = 1;
      } else if (args[0].equalsIgnoreCase("serverOnly")) {
        mode = 2;
      } else {
        halt("Invalid option " + args[0] + ". Must be clientOnly, serverOnly or no option (both).");
      }
    }
  }

  public static void main(String[] args) {
    parseMode(args);
    ConfigManager.load(mode);
    new Main();
  }

  public Main() {
    super(I18n.t("main.title"));

    this.status = GameStatus.MAIN;

    this.add(new MainPanel());

    this.setSize(ConfigManager.general.config().getScreenWidth(), ConfigManager.general.config().getScreenHeight());
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  public void quit() {
    this.setVisible(false);
    this.dispose();
  }

  public void setStatus(GameStatus newStatus) {
    switch (newStatus) {
      default:
        break;
    }
    this.status = newStatus;
  }

  private class MainPanel extends JPanel {

    private int selection = 0;
    private final String[] menuItems = new String[] {"host", "connect", "options", "exit"};

    public MainPanel() {
      this.addKeyListener(new KeyAdapter() {

        final Map<GameStatus, Consumer<KeyEvent>> handlers = new HashMap<>();
        {
          handlers.put(GameStatus.MAIN, (KeyEvent e) -> {
            int keyCode = e.getKeyCode();
            switch (keyCode) {
              case KeyEvent.VK_UP:
                if (selection > 0) {
                  selection--;
                }
                break;
              case KeyEvent.VK_DOWN:
                if (selection < menuItems.length - 1) {
                  selection++;
                }
                break;
              case KeyEvent.VK_ENTER:
                switch (selection) {
                  case 0:
                    setStatus(GameStatus.SERVER_CONFIG);
                    break;
                  case 1:
                    setStatus(GameStatus.CONNECT);
                    break;
                  case 2:
                    setStatus(GameStatus.GENERAL_CONFIG);
                    break;
                  case 3: //exit
                    quit();
                    break;
                }
                break;
            }
          });
        }

        @Override
        public void keyPressed(KeyEvent e) {
          handlers.get(status).accept(e);
          MainPanel.this.repaint();
        }
      });
      this.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;
      TexturePack pack = PackManager.pack();

      pack.selectFont(g2d, texture.Font.GAME_TITLE);
      pack.selectColor(g2d, "main.title");
      pack.drawString(g2d, I18n.t("main.title"), 10, 10, TexturePack.Aligment.START, TexturePack.Aligment.START);

      pack.selectFont(g2d, texture.Font.MENU);
      int lineHeight = g2d.getFontMetrics().getHeight();
      for (int i = 0; i < menuItems.length; i++) {
        pack.selectColor(g2d, i == selection ?  "main.menu.selected" : "main.menu.default");
        pack.drawString(g2d, I18n.t("main.menu." + menuItems[i]), Main.this.getWidth() - 10, 50 + i*(lineHeight + 5), TexturePack.Aligment.END, TexturePack.Aligment.START);
      }
    }
  }
}
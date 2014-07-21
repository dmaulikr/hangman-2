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
import game.panels.Panel;

public class Main extends JFrame {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  // 1 - client | 2 - server | 3 - both
  private static int mode;

  private GameStatus status;
  private Panel panel;

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

    this.setStatus(GameStatus.MAIN);

    this.add(new MainPanel());

    this.setSize(ConfigManager.general.config().getScreenWidth(), ConfigManager.general.config().getScreenHeight());
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
  }

  public void setStatus(GameStatus newStatus) {
    if (newStatus == GameStatus.QUIT) {
      this.setVisible(false);
      this.dispose();
    } else {
      this.status = newStatus;
      this.panel = this.status.getNewPanel();
    }
  }

  private class MainPanel extends JPanel {

    public MainPanel() {
      this.addMouseListener(new MouseAdapter() {
        @Override
        public void mousePressed(MouseEvent e) {
          GameStatus newStatus = Main.this.panel.handleMouseClick(e);
          if (newStatus != null) {
            setStatus(newStatus);
          }
          MainPanel.this.repaint();
        }
      });

      this.addMouseMotionListener(new MouseMotionAdapter() {
        @Override
        public void mouseMoved(MouseEvent e) {
          Main.this.panel.handleMouseMove(e);
          MainPanel.this.repaint();
        }
      });

      this.addKeyListener(new KeyAdapter() {
        @Override
        public void keyPressed(KeyEvent e) {
          GameStatus newStatus = Main.this.panel.handleKeyPressed(e);
          if (newStatus != null) {
            setStatus(newStatus);
          }
          MainPanel.this.repaint();
        }
      });
      this.setFocusable(true);
    }

    @Override
    public void paintComponent(Graphics g) {
      Graphics2D g2d = (Graphics2D) g;

      //clear
      g2d.setColor(Color.WHITE);
      g2d.fillRect(0, 0, Main.this.getWidth(), Main.this.getHeight());

      //render panel
      Main.this.panel.render(g2d, Main.this.getWidth(), Main.this.getHeight());
    }
  }
}
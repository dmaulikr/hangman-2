package game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.panels.*;

public enum GameStatus {

  MAIN(MenuPanel.class), HOSTING, CONNECT, GENERAL_CONFIG(GeneralConfigPanel.class), CLIENT_CONFIG, SERVER_CONFIG, GAME, QUIT;

  private static final Logger logger = LoggerFactory.getLogger(GameStatus.class);

  private Class<? extends Panel> panelClass;

  private GameStatus() {
    this(null);
  }

  private GameStatus(Class<? extends Panel> panelClass) {
    this.panelClass = panelClass;
  }

  public Panel getNewPanel() {
    try {
      return panelClass.newInstance();
    } catch (IllegalAccessException | InstantiationException ex) {
      logger.error("Panel subclass must have public default constructor.", ex);
      System.exit(1);
      return null;
    }
  }
}
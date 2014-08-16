package game;

import javafx.scene.Scene;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.scenes.*;

public enum GameStatus {

  MAIN(MenuScene.class), HOSTING, CONNECT, GENERAL_CONFIG(GeneralConfigScene.class), CLIENT_CONFIG, SERVER_CONFIG, GAME, QUIT;

  private static final Logger logger = LoggerFactory.getLogger(GameStatus.class);

  private Class<? extends Scene> sceneClass;

  private GameStatus() {
    this(null);
  }

  private GameStatus(Class<? extends Scene> sceneClass) {
    this.sceneClass = sceneClass;
  }

  public Scene getNewScene() {
    try {
      return sceneClass.newInstance();
    } catch (IllegalAccessException | InstantiationException ex) {
      logger.error("Scene subclass must have public default constructor.", ex);
      System.exit(1);
      return null;
    }
  }
}
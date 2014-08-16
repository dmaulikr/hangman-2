package game;

import java.lang.reflect.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import game.scenes.*;

public enum GameStatus {

  MAIN(MenuScene.class), HOSTING, CONNECT, GENERAL_CONFIG(GeneralConfigScene.class), CLIENT_CONFIG, SERVER_CONFIG, GAME, QUIT;

  private static final Logger logger = LoggerFactory.getLogger(GameStatus.class);

  private Class<? extends DefaultScene> sceneClass;

  private GameStatus() {
    this(null);
  }

  private GameStatus(Class<? extends DefaultScene> sceneClass) {
    this.sceneClass = sceneClass;
  }

  public DefaultScene getNewScene(Main mainRef) {
    try {
      DefaultScene scene = sceneClass.newInstance();
      scene.setMain(mainRef);
      return scene;
    } catch (IllegalAccessException | InstantiationException ex) {
      logger.error("Scene subclass must have public default constructor.", ex);
      System.exit(1);
      return null;
    }
  }
}
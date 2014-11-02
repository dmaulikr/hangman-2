package xyz.luan.games.hangman.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.luan.games.hangman.game.scenes.DefaultScene;
import xyz.luan.games.hangman.game.scenes.GeneralConfigScene;
import xyz.luan.games.hangman.game.scenes.HostServerScene;
import xyz.luan.games.hangman.game.scenes.MenuScene;

public enum GameStatus {

    MAIN(MenuScene.class), HOSTING(HostServerScene.class), CONNECT, GENERAL_CONFIG(GeneralConfigScene.class), CLIENT_CONFIG, SERVER_CONFIG, GAME, QUIT;

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
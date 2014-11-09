package xyz.luan.games.hangman.game;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.luan.games.hangman.game.scenes.ClientConfigScene;
import xyz.luan.games.hangman.game.scenes.ClientConnectScene;
import xyz.luan.games.hangman.game.scenes.DefaultScene;
import xyz.luan.games.hangman.game.scenes.GeneralConfigScene;
import xyz.luan.games.hangman.game.scenes.HostServerScene;
import xyz.luan.games.hangman.game.scenes.Lobby;
import xyz.luan.games.hangman.game.scenes.MenuScene;
import xyz.luan.games.hangman.game.scenes.ServerConfigScene;

public enum GameStatus {

    MAIN_MENU(MenuScene.class),
    HOSTING_SERVER(HostServerScene.class),
    CONNECT_TO_SERVER(ClientConnectScene.class),
    GENERAL_CONFIG(GeneralConfigScene.class),
    CLIENT_CONFIG(ClientConfigScene.class),
    SERVER_CONFIG(ServerConfigScene.class),
    CLIENT_LOBBY(Lobby.class),
    GAME,
    QUIT;

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
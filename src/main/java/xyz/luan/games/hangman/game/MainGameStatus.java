package xyz.luan.games.hangman.game;

import xyz.luan.games.hangman.game.scenes.ClientConfigScene;
import xyz.luan.games.hangman.game.scenes.ClientConnectScene;
import xyz.luan.games.hangman.game.scenes.DefaultScene;
import xyz.luan.games.hangman.game.scenes.GeneralConfigScene;
import xyz.luan.games.hangman.game.scenes.HostServerScene;
import xyz.luan.games.hangman.game.scenes.MenuScene;
import xyz.luan.games.hangman.game.scenes.ServerConfigScene;

public enum MainGameStatus implements GameStatus {

    MAIN_MENU(MenuScene.class),
    HOSTING_SERVER(HostServerScene.class),
    CONNECT_TO_SERVER(ClientConnectScene.class),
    GENERAL_CONFIG(GeneralConfigScene.class),
    CLIENT_CONFIG(ClientConfigScene.class),
    SERVER_CONFIG(ServerConfigScene.class),
    GAME,
    QUIT;

    private Class<? extends DefaultScene> sceneClass;

    private MainGameStatus() {
        this(null);
    }

    private MainGameStatus(Class<? extends DefaultScene> sceneClass) {
        this.sceneClass = sceneClass;
    }

    @Override
    public DefaultScene getNewScene(Main mainRef) {
        return GameStatus.getNewScene(sceneClass, mainRef);
    }
}
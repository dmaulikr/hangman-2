package xyz.luan.games.hangman.client;

import xyz.luan.games.hangman.client.scenes.ClientScene;
import xyz.luan.games.hangman.client.scenes.LoginScene;
import xyz.luan.games.hangman.client.scenes.RegisterScene;
import xyz.luan.games.hangman.game.GameStatus;
import xyz.luan.games.hangman.game.Main;
import xyz.luan.games.hangman.game.scenes.DefaultScene;

public enum ClientStatus implements GameStatus {

    LOGIN(LoginScene.class), REGISTER(RegisterScene.class), LOBBY, PROFILE, GAME;

    private Class<? extends ClientScene> sceneClass;

    private ClientStatus() {
        this(null);
    }

    private ClientStatus(Class<? extends ClientScene> sceneClass) {
        this.sceneClass = sceneClass;
    }

    @Override
    public DefaultScene getNewScene(Main mainRef) {
        return GameStatus.getNewScene(sceneClass, mainRef);
    }
}

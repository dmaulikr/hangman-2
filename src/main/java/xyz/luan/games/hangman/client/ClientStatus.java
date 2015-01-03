package xyz.luan.games.hangman.client;

import xyz.luan.games.hangman.client.scenes.ClientScene;
import xyz.luan.games.hangman.client.scenes.Lobby;
import xyz.luan.games.hangman.client.scenes.LoginScene;
import xyz.luan.games.hangman.client.scenes.MyProfile;
import xyz.luan.games.hangman.client.scenes.RegistrationScene;
import xyz.luan.games.hangman.game.GameStatus;
import xyz.luan.games.hangman.game.Main;
import xyz.luan.games.hangman.game.scenes.DefaultScene;

public enum ClientStatus implements GameStatus {

	LOGIN(LoginScene.class), REGISTER(RegistrationScene.class), LOBBY(Lobby.class), PROFILE(MyProfile.class), CREATE_GAME, GAME;

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

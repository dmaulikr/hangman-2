package xyz.luan.games.hangman.client.scenes;

import lombok.Setter;
import xyz.luan.games.hangman.client.Client;
import xyz.luan.games.hangman.game.scenes.DefaultScene;

public abstract class ClientScene extends DefaultScene {

	@Setter
	protected Client client;

	@Override
	public void closed() {
		client.quit();
	}

	public <T extends ClientScene> T as(Class<T> clazz) {
		//TODO assert is actually, proper error if not
		return clazz.cast(this);
	}
}

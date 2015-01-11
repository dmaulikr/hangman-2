package xyz.luan.games.hangman.client.scenes;

import java.util.function.Consumer;

import lombok.Setter;
import xyz.luan.games.hangman.client.Client;
import xyz.luan.games.hangman.game.scenes.DefaultScene;

public abstract class ClientScene extends DefaultScene {

	@Setter
	protected Client client;

	@Override
	public void closed() {
		client.dispose();
	}

	public <T extends ClientScene> void perform(Class<T> clazz, Consumer<T> consumer) {
		if (clazz.isInstance(this)) {
			consumer.accept(clazz.cast(this));
		}
	}
}

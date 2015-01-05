package xyz.luan.games.hangman.messaging.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.luan.games.hangman.client.Client;
import xyz.luan.games.hangman.game.Profile;

@AllArgsConstructor
public class UserLoginNotification implements ServerMessage {

	private static final long serialVersionUID = 5155627622031471945L;

	@Getter
	private Profile profile;

	@Override
	public void handle(Client client) {
		client.getData().notifyLogin(profile);
	}
}

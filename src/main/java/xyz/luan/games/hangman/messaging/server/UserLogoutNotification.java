package xyz.luan.games.hangman.messaging.server;

import java.util.Set;

import lombok.AllArgsConstructor;
import xyz.luan.games.hangman.client.Client;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.client.scenes.Lobby;
import xyz.luan.games.hangman.game.Profile;

@AllArgsConstructor
public class UserLogoutNotification implements ServerMessage {

	private static final long serialVersionUID = 5155627622031471945L;

	private Profile profile;

	@Override
	public void handle(Client client) {
		client.getCurrentScene().as(Lobby.class).getLoggedUsersView().notifyLogout(profile);
	}
}

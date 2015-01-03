package xyz.luan.games.hangman.messaging.client;

import lombok.AllArgsConstructor;
import xyz.luan.games.hangman.client.Client;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.messaging.client.ClientMessage.Requirement;
import xyz.luan.games.hangman.messaging.server.LoginResponse;
import xyz.luan.games.hangman.messaging.server.ServerMessage;
import xyz.luan.games.hangman.server.Server;
import xyz.luan.games.hangman.server.Server.ClientHandler;

@AllArgsConstructor
public class LogoutMessage implements ClientMessage {

	private static final long serialVersionUID = 7467992423968139457L;

	@Override
	public ServerMessage handle(Server server, ClientHandler client) {
		client.logout();
		return new ServerMessage() {

			@Override
			public void handle(Client client) {
				client.setMode(ClientStatus.LOGIN);
			}

		};
	}

	@Override
	public Requirement requirement() {
		return Requirement.MUST_BE_LOGGED_IN;
	}
}

package xyz.luan.games.hangman.messaging.client;

import java.io.Serializable;

import xyz.luan.games.hangman.messaging.server.ServerMessage;
import xyz.luan.games.hangman.server.Server;
import xyz.luan.games.hangman.server.Server.ClientHandler;

public interface ClientMessage extends Serializable {

	ServerMessage handle(Server server, ClientHandler client);

	Requirement requirement();

	public enum Requirement {
		MUST_BE_LOGGED_IN {
			@Override
			public boolean fufill(ClientHandler clientHandler) {
				return clientHandler.isLoggedIn();
			}
		}, MUST_NOT_BE_LOGGED_IN {
			@Override
			public boolean fufill(ClientHandler clientHandler) {
				return !clientHandler.isLoggedIn();
			}
		};

		public abstract boolean fufill(ClientHandler clientHandler);
	}
}

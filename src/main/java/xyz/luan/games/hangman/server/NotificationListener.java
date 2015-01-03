package xyz.luan.games.hangman.server;

import xyz.luan.games.hangman.game.Profile;
import xyz.luan.games.hangman.server.Server.ClientHandler;

public interface NotificationListener {

	public void userConnected(ClientHandler c);

	public void userDisconnected(ClientHandler c);

	public void userLoggedIn(ClientHandler c);

	public void userLoggedOut(ClientHandler c);
}

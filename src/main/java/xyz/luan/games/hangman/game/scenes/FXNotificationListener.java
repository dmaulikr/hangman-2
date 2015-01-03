package xyz.luan.games.hangman.game.scenes;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import xyz.luan.games.hangman.game.Profile;
import xyz.luan.games.hangman.server.NotificationListener;
import xyz.luan.games.hangman.server.Server.ClientHandler;

public class FXNotificationListener implements NotificationListener {

	private ObservableList<ClientHandler> handlers;

	public void bind(ListView<ClientHandler> listView) {
		listView.setItems(handlers);
	}

	public FXNotificationListener() {
		handlers = FXCollections.observableArrayList();
	}

	@Override
	public void userDisconnected(ClientHandler c) {
		Platform.runLater(() -> {
			handlers.remove(c);
		});
	}

	@Override
	public void userConnected(ClientHandler c) {
		Platform.runLater(() -> {
			handlers.add(c);
		});
	}

	@Override
	public void userLoggedIn(ClientHandler c) {
		Platform.runLater(() -> {
			handlers.set(handlers.indexOf(c), c);
		});
	}

	@Override
	public void userLoggedOut(ClientHandler c) {
		handlers.set(handlers.indexOf(c), c);
	}
}

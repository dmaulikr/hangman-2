package xyz.luan.games.hangman.game.scenes;

import javafx.application.Platform;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.scene.control.ListView;
import xyz.luan.games.hangman.server.ConnectionListener;
import xyz.luan.games.hangman.server.Server.ClientHandler;

public class FXConnectionListener implements ConnectionListener {

    private ObservableList<ClientHandler> handlers;

    public FXConnectionListener() {
        handlers = FXCollections.observableArrayList();
    }

    @Override
    public void disconnected(ClientHandler c) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                handlers.remove(c);
            }
        });
    }

    @Override
    public void connected(ClientHandler c) {
        Platform.runLater(new Runnable() {
            @Override
            public void run() {
                handlers.add(c);
            }
        });
    }

    public void bind(ListView<ClientHandler> listView) {
        listView.setItems(handlers);
    }
}

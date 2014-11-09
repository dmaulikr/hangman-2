package xyz.luan.games.hangman.game.scenes;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import xyz.luan.games.hangman.client.ClientHandler;
import xyz.luan.games.hangman.game.forms.FormUtils;

public class Lobby extends DefaultScene {

    private ClientHandler clientHandler;

    @Override
    protected Pane generatePane() {
        GridPane pane = FormUtils.defaultGrid();

        pane.add(new Label("it fucking works, bob!"), 0, 0);

        return pane;
    }

    public void setClientHandler(ClientHandler clientHandler) {
        this.clientHandler = clientHandler;
    }

}

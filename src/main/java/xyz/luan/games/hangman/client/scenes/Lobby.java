package xyz.luan.games.hangman.client.scenes;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import xyz.luan.games.hangman.game.forms.FormUtils;

public class Lobby extends ClientScene {

    @Override
    protected Pane generatePane() {
        GridPane pane = FormUtils.defaultGrid();

        pane.add(new Label("it fucking works, bob!"), 0, 0);

        return pane;
    }

}

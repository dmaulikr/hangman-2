package xyz.luan.games.hangman.game.scenes;

import xyz.luan.games.hangman.game.GameStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;

public class StateChangeButton extends Button {

    public StateChangeButton(String text, GameStatus newState, Main mainRef) {
        this(text, event -> mainRef.setStatus(newState));
    }

    public StateChangeButton(String text, EventHandler<ActionEvent> event) {
        this.setText(I18n.t(text));
        this.setMaxSize(Double.MAX_VALUE, this.getHeight());
        this.setOnAction(event);
    }
}
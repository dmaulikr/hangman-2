package xyz.luan.games.hangman.game.scenes;

import xyz.luan.games.hangman.game.ConfigManager;
import xyz.luan.games.hangman.game.MainGameStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.Main;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;

public abstract class DefaultScene {

    protected Main mainRef;

    public void setMain(Main mainRef) {
        this.mainRef = mainRef;
    }

    protected abstract Pane generatePane();

    public Scene generateScene() {
        return new Scene(generatePane(), ConfigManager.general.config().getScreenWidth(), ConfigManager.general.config().getScreenHeight());
    }

    public class StateChangeButton extends Button {

        public StateChangeButton(String text, MainGameStatus newState) {
            setup(text, event -> mainRef.setStatus(newState));
        }

        public StateChangeButton(String text, EventHandler<ActionEvent> event) {
            setup(text, event);
        }

        private void setup(String text, EventHandler<ActionEvent> event) {
            this.setText(I18n.t(text));
            this.setMaxSize(Double.MAX_VALUE, this.getHeight());
            this.setOnAction(event);
        }
    }

}
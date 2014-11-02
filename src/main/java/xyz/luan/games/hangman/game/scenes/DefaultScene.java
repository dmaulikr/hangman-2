package xyz.luan.games.hangman.game.scenes;

import xyz.luan.games.hangman.game.ConfigManager;
import xyz.luan.games.hangman.game.Main;
import javafx.scene.Scene;
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

}
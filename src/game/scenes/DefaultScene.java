package game.scenes;

import javafx.scene.*;
import javafx.scene.layout.*;
import game.ConfigManager;

import game.Main;

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
package game.scenes;

import javafx.scene.*;
import javafx.scene.layout.*;
import game.ConfigManager;

public abstract class DefaultScene extends Scene {
  
  protected DefaultScene(Pane pane) {
    super(pane, ConfigManager.general.config().getScreenWidth(), ConfigManager.general.config().getScreenHeight());
  }

}
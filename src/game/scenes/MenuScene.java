package game.scenes;

import javafx.scene.*;
import javafx.scene.layout.*;
import javafx.scene.text.*;
import javafx.scene.control.*;

import game.*;
import game.forms.FormUtils;

public class MenuScene extends DefaultScene {
  private static final String[] ITEMS = { "host", "connect", "options", "exit" };
  private static final GameStatus[] ACTIONS = { GameStatus.SERVER_CONFIG, GameStatus.CONNECT, GameStatus.GENERAL_CONFIG, GameStatus.QUIT };

  @Override
  protected Pane generatePane() {
    GridPane grid = FormUtils.defaultGrid();

    Text sceneTitle = new Text(I18n.t("main.title"));
    sceneTitle.getStyleClass().add("title");
    grid.add(sceneTitle, 0, 0);

    assert ITEMS.length == ACTIONS.length;
    for (int i = 0; i < ITEMS.length; i++) {
      grid.add(new StateChangeButton("main.menu." + ITEMS[i], ACTIONS[i], mainRef), 0, i + 1);
    }

    return grid;
  }
}
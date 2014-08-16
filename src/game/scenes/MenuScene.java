package game.scenes;

import javafx.scene.*;
import javafx.scene.layout.*;

import game.*;
import static texture.PackManager.pack;
import texture.TexturePack.Aligment;

public class MenuScene extends DefaultScene {
  private static final String[] ITEMS = { "host", "connect", "options", "exit" };
  private static final GameStatus[] ACTIONS = { GameStatus.SERVER_CONFIG, GameStatus.CONNECT, GameStatus.GENERAL_CONFIG, GameStatus.QUIT };

  public MenuScene() {
    super(getNewPane());
  }

  private static Pane getNewPane() {
    GridPane grid = new GridPane();
    grid.setAlignment(Pos.CENTER);
    grid.setHgap(10);
    grid.setVgap(10);
    grid.setPadding(new Insets(25, 25, 25, 25));

    Text sceneTitle = new Text(I18n.t("main.title"));
    sceneTitle.setFont(Font.font("Tahoma", FontWeight.NORMAL, 20));
    grid.add(sceneTitle, 0, 0);

    assert ITEMS.length == ACTIONS.length;
    for (int i = 0; i < ITEMS.length; i++) {
      Button button = new Button();
      button.setText(I18n.t(ITEMS[i]));
      grid.add(button, 0, i);
    }

    return grid;
  }
}
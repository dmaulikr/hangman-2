package xyz.luan.games.hangman.game.scenes;

import xyz.luan.games.hangman.game.GameStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.forms.FormUtils;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;

public class MenuScene extends DefaultScene {
    private static final String[] ITEMS = { "host", "connect", "options", "exit" };
    private static final GameStatus[] ACTIONS = { GameStatus.SERVER_CONFIG, GameStatus.CONNECT_TO_SERVER, GameStatus.GENERAL_CONFIG, GameStatus.QUIT };

    @Override
    protected Pane generatePane() {
        GridPane grid = FormUtils.defaultGrid();

        Text sceneTitle = new Text(I18n.t("main.title"));
        sceneTitle.getStyleClass().add("title");
        grid.add(sceneTitle, 0, 0);

        assert ITEMS.length == ACTIONS.length;
        for (int i = 0; i < ITEMS.length; i++) {
            grid.add(new StateChangeButton("main.menu." + ITEMS[i], ACTIONS[i]), 0, i + 1);
        }

        return grid;
    }
}
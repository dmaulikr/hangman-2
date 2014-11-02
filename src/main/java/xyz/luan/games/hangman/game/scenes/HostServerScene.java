package xyz.luan.games.hangman.game.scenes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.forms.FormUtils;

public class HostServerScene extends DefaultScene {

    @Override
    protected Pane generatePane() {
        GridPane grid = FormUtils.defaultGrid();

        Text sceneTitle = new Text(I18n.t("main.menu.host"));
        sceneTitle.getStyleClass().add("title");
        grid.add(sceneTitle, 0, 0, 2, 1);

        grid.add(new Label("My ip:"), 0, 1);
        grid.add(new Label("blah"), 1, 1);

        Button button = new Button("hue");
        grid.add(button, 1, 0, 2, 1);

        Label status = new Label("status...");
        grid.add(status, 3, 0, 2, 1);

        return grid;
    }

}

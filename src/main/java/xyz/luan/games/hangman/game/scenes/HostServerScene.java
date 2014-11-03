package xyz.luan.games.hangman.game.scenes;

import java.net.Inet4Address;
import java.net.UnknownHostException;

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

        grid.add(new Label("My IP:"), 0, 1);
        grid.add(new Label(getMyIp()), 1, 1);

        Button hostButton = new Button("Host");
        grid.add(hostButton, 0, 2);
        Button cancelButton = new Button("Cancel");
        grid.add(cancelButton, 1, 2);

        Label status = new Label("status...");
        grid.add(status, 0, 3, 2, 1);

        return grid;
    }

    private String getMyIp() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Invalid hoster?", e);
        }
    }

}

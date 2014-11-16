package xyz.luan.games.hangman.game.scenes;

import java.io.IOException;
import java.net.Inet4Address;
import java.net.UnknownHostException;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import xyz.luan.games.hangman.game.ConfigManager;
import xyz.luan.games.hangman.game.MainGameStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.forms.DialogHelper;
import xyz.luan.games.hangman.game.forms.FormUtils;
import xyz.luan.games.hangman.server.Server;
import xyz.luan.games.hangman.server.Server.ClientHandler;

public class HostServerScene extends DefaultScene {

    private Server handler;

    private TextField portText;
    private Label status;
    private Button hostButton, cancelButton;
    
    private GridPane hostingDataPane;
    private ListView<ClientHandler> listView;
    private FXConnectionListener fxConnectionListener;

    @Override
    protected Pane generatePane() {
        GridPane grid = FormUtils.defaultGrid();

        Text sceneTitle = new Text(I18n.t("main.menu.host"));
        sceneTitle.getStyleClass().add("title");
        grid.add(sceneTitle, 0, 0, 2, 1);

        grid.add(new Label("My IP:"), 0, 1);
        grid.add(new Label(getMyIp()), 1, 1);

        grid.add(new Label("Port:"), 0, 2);
        portText = new TextField(String.valueOf(ConfigManager.general.config().getPort()));
        grid.add(portText, 1, 2);

        hostButton = new Button();
        setupHostButtonForStart();
        grid.add(hostButton, 0, 3);

        cancelButton = new StateChangeButton("common.cancel", MainGameStatus.MAIN_MENU);
        grid.add(cancelButton, 1, 3);

        status = new Label();
        grid.add(status, 0, 4, 2, 1);

        setupHostingDataPane(grid);

        return grid;
    }

    private void setupHostingDataPane(GridPane grid) {
        hostingDataPane = FormUtils.defaultGrid();
        hostingDataPane.setVisible(false);
        hostingDataPane.add(new Label("Clients connectd:"), 0, 0);
        hostingDataPane.add(listView = new ListView<>(), 0, 1);
        grid.add(hostingDataPane, 0, 5, 2, 1);
    }

    private EventHandler<ActionEvent> stopHandler() {
        return e -> {
            cancelButton.setDisable(false);
            status.setText("");
            hostingDataPane.setVisible(false);
            stopServer();
            setupHostButtonForStart();
        };
    }

    private void setupHostButtonForStart() {
        hostButton.setText(I18n.t("host.host"));
        hostButton.setOnAction(startHandler());
    }

    private EventHandler<ActionEvent> startHandler() {
        return e -> {
            try {
                int port = Integer.parseInt(portText.getText());
                startServer(port);
                setupHostButtonForStop();
                hostingDataPane.setVisible(true);
                status.setText("Hosting...");
                cancelButton.setDisable(true);
            } catch (NumberFormatException ex) {
                DialogHelper.show("Error!", "Port must be an integer.");
            } catch (IOException ex) {
                DialogHelper.show("Error!", "IO Exception: " + ex.getMessage());
            }
        };
    }

    private void setupHostButtonForStop() {
        hostButton.setText(I18n.t("host.stop"));
        hostButton.setOnAction(stopHandler());
    }

    private void startServer(int port) throws IOException {
        stopServer();
        fxConnectionListener = new FXConnectionListener();
        fxConnectionListener.bind(listView);
        handler = new Server(port, fxConnectionListener);
        handler.start();
    }

    private void stopServer() {
        if (handler != null) {
            handler.quit();
            fxConnectionListener = null;
        }
    }

    private String getMyIp() {
        try {
            return Inet4Address.getLocalHost().getHostAddress();
        } catch (UnknownHostException e) {
            throw new RuntimeException("Invalid hoster?", e);
        }
    }

}

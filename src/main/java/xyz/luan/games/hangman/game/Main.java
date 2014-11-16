package xyz.luan.games.hangman.game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;
import xyz.luan.games.hangman.client.Client;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.client.scenes.ClientScene;
import xyz.luan.games.hangman.game.scenes.DefaultScene;

public class Main extends Application {

    public enum GameMode {
        CLIENT_ONLY, SERVER_ONLY, BOTH;
    }

    private static GameMode mode;

    private DefaultScene scene;
    private Stage stage;
    private Client clientRef;

    public static void halt(String message) {
        System.err.println(message);
        System.exit(1);
    }

    private static void parseMode(String[] args) {
        if (args.length > 1) {
            halt("Invalid options. Must be run with program [clientOnly | serverOnly].");
        }
        if (args.length == 0) {
            mode = GameMode.BOTH;
        } else {
            if (args[0].equalsIgnoreCase("clientOnly")) {
                mode = GameMode.CLIENT_ONLY;
            } else if (args[0].equalsIgnoreCase("serverOnly")) {
                mode = GameMode.SERVER_ONLY;
            } else {
                halt("Invalid option " + args[0] + ". Must be clientOnly, serverOnly or no option (both).");
            }
        }
    }

    public static void main(String[] args) {
        parseMode(args);
        ConfigManager.load(mode);
        launch();
    }

    @Override
    public void start(Stage stage) {
        this.stage = stage;
        this.stage.setTitle(I18n.t("main.title"));
        this.setStatus(MainGameStatus.MAIN_MENU);
        this.stage.show();
    }

    public void setStatus(GameStatus status) {
        if (status == MainGameStatus.QUIT) {
            Platform.exit();
        } else {
            this.scene = status.getNewScene(this);
            this.stage.setScene(scene.generateScene());
            if (status instanceof ClientStatus) {
                ((ClientScene) scene).setClient(clientRef);
            }
        }
    }

    public void disconnect() {
        this.clientRef = null;
    }

    public void connect(Client clientRef) {
        this.clientRef = clientRef;
        setStatus(ClientStatus.LOGIN);
    }
}
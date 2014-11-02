package xyz.luan.games.hangman.game;

import javafx.application.Application;
import javafx.application.Platform;
import javafx.stage.Stage;

public class Main extends Application {

    private static int mode; // 1 - client | 2 - server | 3 - both
    private Stage stage;

    public static void halt(String message) {
        System.err.println(message);
        System.exit(1);
    }

    private static void parseMode(String[] args) {
        if (args.length > 1) {
            halt("Invalid options. Must be run with program [clientOnly | serverOnly].");
        }
        if (args.length == 0) {
            mode = 3;
        } else {
            if (args[0].equalsIgnoreCase("clientOnly")) {
                mode = 1;
            } else if (args[0].equalsIgnoreCase("serverOnly")) {
                mode = 2;
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
        this.setStatus(GameStatus.MAIN);
        this.stage.show();
    }

    public void setStatus(GameStatus status) {
        if (status == GameStatus.QUIT) {
            Platform.exit();
        } else {
            this.stage.setScene(status.getNewScene(this).generateScene());
        }
    }
}
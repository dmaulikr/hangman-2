package game;

import javafx.stage.Stage;
import javafx.application.Application;
import javafx.application.Platform;
import javafx.scene.*;
import java.util.*;
import java.io.*;
import java.util.function.Consumer;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.*;
import texture.PackManager;
import texture.TexturePack;

public class Main extends Application {

  private static final Logger logger = LoggerFactory.getLogger(Main.class);

  // 1 - client | 2 - server | 3 - both
  private static int mode;

  private GameStatus status;
  private Scene scene;

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
    setStatus(GameStatus.MAIN);

    stage.setTitle(I18n.t("main.title"));
    stage.setScene(scene);
    stage.show();
  }

  public void setStatus(GameStatus newStatus) {
    status = newStatus;
    if (status == GameStatus.QUIT) {
      Platform.exit();
    } else {
      scene = status.getNewScene();
    }
  }
}
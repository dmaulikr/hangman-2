package game.scenes;

import javafx.event.*;
import javafx.scene.control.*;

import game.Main;
import game.GameStatus;
import game.I18n;

public class StateChangeButton extends Button {
  
  public StateChangeButton(String text, GameStatus newState, Main mainRef) {
    this(text, event -> mainRef.setStatus(newState));
  }

  public StateChangeButton(String text, EventHandler<ActionEvent> event) {
    this.setText(I18n.t(text));
    this.setMaxSize(Double.MAX_VALUE, this.getHeight());
    this.setOnAction(event);
  }
}
package xyz.luan.games.hangman.game.scenes;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import xyz.luan.games.hangman.game.ConfigManager;
import xyz.luan.games.hangman.game.GameStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.Main;
import xyz.luan.games.hangman.texture.FxHelper;
import xyz.luan.games.hangman.texture.PackManager;
import xyz.luan.games.hangman.texture.TextType;
import xyz.luan.games.hangman.texture.TextType.TextTypeReference;

public abstract class DefaultScene {

	protected Main mainRef;

	public void setMain(Main mainRef) {
		this.mainRef = mainRef;
	}

	protected abstract Pane generatePane();

	public Scene generateScene() {
		return new Scene(generatePane(), ConfigManager.general.config().getWidth(), ConfigManager.general.config().getHeight());
	}

	public class StateChangeButton extends Button {

		public StateChangeButton(String text, GameStatus newState) {
			setup(text, event -> mainRef.setStatus(newState));
		}

		private StateChangeButton(String text, EventHandler<ActionEvent> event) {
			setup(text, event);
		}

		private void setup(String text, EventHandler<ActionEvent> event) {
			this.setText(I18n.t(text));
			FxHelper.setupButton(this);
			this.setMaxSize(Double.MAX_VALUE, this.getHeight());
			this.setOnAction(event);
		}

	}

	public StateChangeButton stateChangeButtonWithEvent(String text, EventHandler<ActionEvent> event) {
		return new StateChangeButton(text, event);
	}

	public void closed() {
	}
}
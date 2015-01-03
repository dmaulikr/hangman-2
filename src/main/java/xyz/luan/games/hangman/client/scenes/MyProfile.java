package xyz.luan.games.hangman.client.scenes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.forms.FormUtils;
import xyz.luan.games.hangman.messaging.client.LogoutMessage;

public class MyProfile extends ClientScene {

	@Override
	protected Pane generatePane() {
		GridPane pane = FormUtils.defaultGrid();

		pane.add(new Label("my profile"), 0, 0);
		pane.add(new StateChangeButton("client.lobby.title", ClientStatus.LOBBY), 0, 1);

		return pane;
	}
}

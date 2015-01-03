package xyz.luan.games.hangman.client.scenes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.forms.FormUtils;
import xyz.luan.games.hangman.messaging.client.LogoutMessage;

public class Lobby extends ClientScene {

	@Override
	protected Pane generatePane() {
		GridPane pane = FormUtils.defaultGrid();

		pane.add(new Label("it fucking works, bob!"), 0, 0);
		pane.add(logoutButton(), 0, 1);

		return pane;
	}

	private Button logoutButton() {
		Button logout = new Button(I18n.t("client.logout"));
		logout.setOnAction((e) -> {
			client.sendMessage(new LogoutMessage());
		});
		return logout;
	}

}

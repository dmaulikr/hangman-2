package xyz.luan.games.hangman.client.scenes;

import javafx.scene.control.Button;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.drawing.PlayerSet;
import xyz.luan.games.hangman.game.forms.FormUtils;
import xyz.luan.games.hangman.messaging.client.LogoutMessage;
import xyz.luan.games.hangman.messaging.client.UpdateProfileRequest.UpdateProfileNotification;
import xyz.luan.games.hangman.messaging.server.UserLoginNotification;
import xyz.luan.games.hangman.messaging.server.UserLogoutNotification;
import xyz.luan.games.hangman.texture.FxHelper;
import xyz.luan.games.hangman.texture.TextType;

public class Lobby extends ClientScene {

	private PlayerSet loggedUsers;

	@Override
	protected Pane generatePane() {
		GridPane pane = FormUtils.defaultGrid();

		pane.add(FxHelper.createRawLabel(TextType.TEXT, "it fucking works, bob!"), 0, 0);
		pane.add(logoutButton(), 0, 1);
		pane.add(new StateChangeButton("game.profile.title", ClientStatus.PROFILE), 0, 2);
		pane.add(loggedUsers = new PlayerSet(client.getData().getLoggedUsers()), 0, 3);

		return pane;
	}

	private Button logoutButton() {
		Button logout = FxHelper.createButton("client.logout");
		logout.setOnAction((e) -> {
			client.sendMessage(new LogoutMessage());
		});
		return logout;
	}

	@MessageHandler
	public void respondTo(UpdateProfileNotification req) {
		loggedUsers.update(req.getProfile());
	}

	@MessageHandler
	public void notifyLogin(UserLoginNotification not) {
		loggedUsers.add(not.getProfile());
	}

	@MessageHandler
	public void notifyLogout(UserLogoutNotification not) {
		loggedUsers.remove(not.getProfile());
	}
}

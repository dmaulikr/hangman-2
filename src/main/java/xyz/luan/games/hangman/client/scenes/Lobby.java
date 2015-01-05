package xyz.luan.games.hangman.client.scenes;

import java.util.Set;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.ListView;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import lombok.Getter;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.Profile;
import xyz.luan.games.hangman.game.forms.FormUtils;
import xyz.luan.games.hangman.messaging.client.LogoutMessage;
import xyz.luan.games.hangman.texture.FxHelper;
import xyz.luan.games.hangman.texture.TextType;

public class Lobby extends ClientScene {

	@Getter
	private LoggedUsersView loggedUsersView;

	@Override
	protected Pane generatePane() {
		GridPane pane = FormUtils.defaultGrid();

		pane.add(FxHelper.createRawLabel(TextType.TEXT, "it fucking works, bob!"), 0, 0);
		pane.add(logoutButton(), 0, 1);
		pane.add(new StateChangeButton("game.profile.title", ClientStatus.PROFILE), 0, 2);
		pane.add(loggedUsersView(), 0, 3);

		return pane;
	}

	private ListView<Profile> loggedUsersView() {
		loggedUsersView = new LoggedUsersView();
		return loggedUsersView.getList();
	}

	private Button logoutButton() {
		Button logout = FxHelper.createButton("client.logout");
		logout.setOnAction((e) -> {
			client.sendMessage(new LogoutMessage());
		});
		return logout;
	}

	public class LoggedUsersView {

		@Getter
		private ListView<Profile> list;

		public LoggedUsersView() {
			list = new ListView<>();
			list.setItems(FXCollections.observableArrayList(client.getData().getLoggedUsers()));
		}

		public void notifyLogin(Profile profile) {
			list.getItems().add(profile);
		}

		public void notifyLogout(Profile profile) {
			list.getItems().remove(profile);
		}

	}
}

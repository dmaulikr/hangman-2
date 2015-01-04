package xyz.luan.games.hangman.client.scenes;

import javafx.geometry.Orientation;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.ImageView;
import javafx.scene.input.MouseEvent;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.Profile;
import xyz.luan.games.hangman.game.forms.FormUtils;
import xyz.luan.games.hangman.game.forms.Hint;
import xyz.luan.games.hangman.texture.DefaultAvatar;
import xyz.luan.games.hangman.texture.PackManager;

public class MyProfile extends ClientScene {

	private TextField avatarUrl;

	@Override
	protected Pane generatePane() {
		GridPane pane = FormUtils.defaultGrid();

		createTitle(pane);

		int line = 1;
		pane.add(new Label(I18n.t("game.profile.username")), 0, line);
		pane.add(new Label(profile().getUsername()), 1, line++);

		pane.add(new Hint("game.profile.avatar"), 0, line);
		pane.add(avatarUrl = new TextField(), 1, line++);

		pane.add(new Label(I18n.t("game.profile.avatar.select")), 0, line++, 2, 1);
		pane.add(defaultAvatars(), 0, line++, 2, 1);

		pane.add(new Hint("game.profile.points"), 0, line);
		pane.add(new Label(String.valueOf(profile().getPoints())), 1, line++);

		pane.add(new Hint("game.profile.grimoire"), 0, line++, 2, 1);
		pane.add(grimoire(), 0, line++, 2, 1);

		pane.add(saveButton(), 0, line);
		pane.add(new StateChangeButton("common.cancel", ClientStatus.LOBBY), 1, line);

		return pane;
	}

	/* TODO due to eclipse's bug [456481] can't use method inner classes inside methods inside lambdas. This should be inside defaultAvatars() method */
	private class AvatarBox extends Label {

		public AvatarBox(DefaultAvatar avatar) {
			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			setGraphic(new ImageView(PackManager.pack().getAvatar(avatar.getUrl())));
			this.setOnMouseClicked((e) -> avatarUrl.setText(avatar.getUrl()));
		}
	}

	private FlowPane defaultAvatars() {
		FlowPane pane = new FlowPane(Orientation.HORIZONTAL, 4, 0);
		DefaultAvatar.displayables().forEach(avatar -> pane.getChildren().add(new AvatarBox(avatar)));
		return pane;
	}

	private Node grimoire() {
		return new Label("--grimoire here--");
	}

	private Profile profile() {
		return client.getData().getMe();
	}

	private Button saveButton() {
		Button saveButton = new Button(I18n.t("common.save"));
		saveButton.setOnAction(e -> {
			// update my profile
			// send message to server
			// set label to loading
			// response message must except this screen and yields errors or return to lobby
			});
		return saveButton;
	}

	private void createTitle(GridPane pane) {
		Text sceneTitle = new Text(I18n.t("game.profile.title"));
		sceneTitle.getStyleClass().add("title");
		pane.add(sceneTitle, 0, 0, 2, 1);
	}
}

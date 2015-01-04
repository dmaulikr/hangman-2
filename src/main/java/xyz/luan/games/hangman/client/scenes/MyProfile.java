package xyz.luan.games.hangman.client.scenes;

import javafx.geometry.Insets;
import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.Border;
import javafx.scene.layout.BorderStroke;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.Profile;
import xyz.luan.games.hangman.game.forms.FormUtils;
import xyz.luan.games.hangman.game.forms.Hint;
import xyz.luan.games.hangman.messaging.client.UpdateProfileRequest;
import xyz.luan.games.hangman.spells.Spell;
import xyz.luan.games.hangman.texture.DefaultAvatar;
import xyz.luan.games.hangman.texture.PackManager;

public class MyProfile extends ClientScene {

	private Label status;
	private TextField avatarUrl;

	public void setError(String message) {
		this.status.setText(message);
	}

	@Override
	protected Pane generatePane() {
		GridPane pane = FormUtils.defaultGrid();

		createTitle(pane);

		int line = 1;

		pane.add(status = new Label(), 0, line++, 2, 1);

		pane.add(new Label(I18n.t("game.profile.username")), 0, line);
		pane.add(new Label(profile().getUsername()), 1, line++);

		pane.add(new Hint("game.profile.avatar"), 0, line);
		pane.add(avatarUrl(), 1, line++);

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

	private TextField avatarUrl() {
		avatarUrl = new TextField();
		avatarUrl.setText(profile().getAvatar());
		return avatarUrl;
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
		class SpellBox extends Label {

			private Spell spell;

			public SpellBox(Spell spell) {
				this.spell = spell;
				setupDisplay(spell);
				setupAction(spell);
				update();
			}

			private void setupAction(Spell spell) {
				this.setOnMouseClicked((e) -> {
					if (selected()) {
						profile().getSelectedSpells().remove(spell);
					} else {
						profile().getSelectedSpells().add(spell);
					}
					update();
				});
			}

			private void setupDisplay(Spell spell) {
				setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
				setGraphic(new ImageView(PackManager.pack().getSpellIcon(spell)));
				setTooltip(new Tooltip(spell.getName()));
			}

			public boolean selected() {
				return profile().getSelectedSpells().contains(spell);
			}

			public void update() {
				setStyle(selected() ? "-fx-border-color: red;" : "");
			}
		}

		GridPane grimoire = new GridPane();
		grimoire.setAlignment(Pos.CENTER);
		grimoire.setHgap(5);
		grimoire.setVgap(5);
		if (profile().getGrimoire().isEmpty()) {
			grimoire.add(new Label(I18n.t("game.profile.grimoire.empty")), 0, 0);
			return grimoire;
		}

		final int perRow = 8;
		int count = 0;
		for (Spell spell : profile().getGrimoire()) {
			grimoire.add(new SpellBox(spell), count % perRow, count / perRow);
			count++;
		}

		return grimoire;
	}

	private Profile profile() {
		return client.getData().getMe();
	}

	private Button saveButton() {
		Button saveButton = new Button(I18n.t("common.save"));
		saveButton.setOnAction(e -> {
			status.setText("");
			client.sendMessage(new UpdateProfileRequest(avatarUrl.getText(), profile().getSelectedSpells()));
			status.setText(I18n.t("common.loading"));
		});
		return saveButton;
	}

	private void createTitle(GridPane pane) {
		Text sceneTitle = new Text(I18n.t("game.profile.title"));
		sceneTitle.getStyleClass().add("title");
		pane.add(sceneTitle, 0, 0, 2, 1);
	}
}

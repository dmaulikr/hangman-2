package xyz.luan.games.hangman.client.scenes;

import java.util.ArrayList;
import java.util.List;
import java.util.stream.Collectors;

import javafx.geometry.Orientation;
import javafx.geometry.Pos;
import javafx.scene.Node;
import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import javafx.scene.layout.FlowPane;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.drawing.PlayerCard;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.Profile;
import xyz.luan.games.hangman.game.forms.FormUtils;
import xyz.luan.games.hangman.game.forms.Hint;
import xyz.luan.games.hangman.messaging.client.UpdateProfileRequest;
import xyz.luan.games.hangman.spells.Spell;
import xyz.luan.games.hangman.texture.DefaultAvatar;
import xyz.luan.games.hangman.texture.FxHelper;
import xyz.luan.games.hangman.texture.PackManager;
import xyz.luan.games.hangman.texture.TextType;

public class MyProfile extends ClientScene {

	private Label status;
	private TextField avatarUrl;
	private List<SpellBox> boxes;

	public void setError(String message) {
		this.status.setText(message);
	}

	@Override
	protected Pane generatePane() {
		GridPane pane = FormUtils.defaultGrid();

		createTitle(pane);

		int line = 1;

		pane.add(status = new Label(), 0, line++, 2, 1);

		pane.add(new PlayerCard(profile()), 0, line++, 2, 1);

		pane.add(new Hint("game.profile.avatar"), 0, line);
		pane.add(avatarUrl(), 1, line++);

		pane.add(FxHelper.createLabel(TextType.FORM_LABEL, "game.profile.avatar.select"), 0, line++, 2, 1);
		pane.add(defaultAvatars(), 0, line++, 2, 1);

		pane.add(new Hint("game.profile.points"), 0, line);
		pane.add(FxHelper.createRawLabel(TextType.FORM_LABEL, String.valueOf(profile().getPoints())), 1, line++);

		pane.add(new Hint("game.profile.grimoire"), 0, line++, 2, 1);
		pane.add(grimoire(), 0, line++, 2, 1);

		pane.add(saveButton(), 0, line);
		pane.add(new StateChangeButton("common.cancel", ClientStatus.LOBBY), 1, line);

		return pane;
	}

	private TextField avatarUrl() {
		avatarUrl = FxHelper.createTextField();
		avatarUrl.setText(profile().getAvatar());
		return avatarUrl;
	}

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

	private class SpellBox extends Label {

		private Spell spell;
		private boolean selected;

		public SpellBox(Spell spell) {
			this.spell = spell;
			this.selected = profile().getSelectedSpells().contains(spell);
			setupDisplay(spell);
			setupAction(spell);
			update();
		}

		private void setupAction(Spell spell) {
			this.setOnMouseClicked((e) -> {
				selected = !selected;
				update();
			});
		}

		private void setupDisplay(Spell spell) {
			setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
			setGraphic(new ImageView(PackManager.pack().getSpellIcon(spell)));
			setTooltip(new Tooltip(spell.getName()));
		}

		public boolean selected() {
			return selected;
		}

		public void update() {
			setStyle(selected() ? "-fx-border-color: red;" : "");
		}
	}

	private Node grimoire() {

		GridPane grimoire = new GridPane();
		grimoire.setAlignment(Pos.CENTER);
		grimoire.setHgap(5);
		grimoire.setVgap(5);
		if (profile().getGrimoire().isEmpty()) {
			grimoire.add(FxHelper.createLabel(TextType.TEXT, "game.profile.grimoire.empty"), 0, 0);
			return grimoire;
		}

		boxes = new ArrayList<>();
		final int perRow = 8;
		int count = 0;
		for (Spell spell : profile().getGrimoire()) {
			SpellBox box = new SpellBox(spell);
			boxes.add(box);
			grimoire.add(box, count % perRow, count / perRow);
			count++;
		}

		return grimoire;
	}

	private Profile profile() {
		return client.getData().getMe();
	}

	private Button saveButton() {
		Button saveButton = FxHelper.createButton("common.save");
		saveButton.setOnAction(e -> {
			sendUpdateMessage();
			status.setText(I18n.t("common.loading"));
		});
		return saveButton;
	}

	private void sendUpdateMessage() {
		List<Spell> newSelectedSpells = boxes.stream().filter(b -> b.selected).map(b -> b.spell).collect(Collectors.toList());
		String newAvatar = avatarUrl.getText();
		client.sendMessage(new UpdateProfileRequest(newAvatar, newSelectedSpells));
	}

	private void createTitle(GridPane pane) {
		pane.add(FxHelper.createLabel(TextType.TITLE, "game.profile.title"), 0, 0, 2, 1);
	}
}

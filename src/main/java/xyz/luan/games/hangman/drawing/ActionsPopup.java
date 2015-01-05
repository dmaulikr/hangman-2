package xyz.luan.games.hangman.drawing;

import xyz.luan.games.hangman.game.Profile;
import xyz.luan.games.hangman.texture.FxHelper;
import xyz.luan.games.hangman.texture.IconType;
import xyz.luan.games.hangman.texture.PackManager;
import xyz.luan.games.hangman.texture.TextType;
import xyz.luan.games.hangman.texture.TileManager.TileType;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.image.ImageView;
import javafx.scene.layout.ColumnConstraints;
import javafx.scene.layout.GridPane;

public class ActionsPopup extends GridPane {

	private Profile profile;

	public ActionsPopup(Profile profile) {
		this.profile = profile;
		setProperties();
		createFields();
	}

	private void createFields() {
		add(duel(), 0, 0);
		add(message(), 0, 1);
		add(friend(), 0, 2);
	}

	private Label message() {
		Label label = FxHelper.createImageLabel(PackManager.pack().getIcon(IconType.MESSAGE));
		label.setOnMouseClicked(e -> {

		});
		return label;
	}

	private Label friend() {
		Label label = FxHelper.createImageLabel(PackManager.pack().getIcon(IconType.FRIEND));
		label.setOnMouseClicked(e -> {

		});
		return label;
	}

	private Label duel() {
		Label label = FxHelper.createImageLabel(PackManager.pack().getIcon(IconType.DUEL));
		label.setOnMouseClicked(e -> {
			System.out.println("Dueling " + profile.getUsername());
		});
		return label;
	}

	private static final int hgap = 10;
	private static final int inset = 2;

	private void setProperties() {
		setStyle("-fx-border-color: black;");
		setAlignment(Pos.CENTER);
		setHgap(hgap);
		setPadding(new Insets(inset, inset, inset, inset));
		setColumnWidths();
	}

	private void setColumnWidths() {
		int totalHeight = TileType.AVATAR.getSize() + 2 * inset;
		setMinHeight(totalHeight);
		setMaxHeight(totalHeight);
	}
}

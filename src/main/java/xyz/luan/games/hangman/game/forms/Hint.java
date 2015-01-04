package xyz.luan.games.hangman.game.forms;

import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.Tooltip;
import javafx.scene.image.ImageView;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.texture.IconType;
import xyz.luan.games.hangman.texture.PackManager;

public class Hint extends Label {

	public Hint(String label, String hint) {
		super(label);
		setContentDisplay(ContentDisplay.RIGHT);
		setGraphic(new ImageView(PackManager.pack().getIcon(IconType.HELP)));
		setTooltip(new Tooltip(hint));
	}

	public Hint(String bundle) {
		this(I18n.t(bundle + ".label"), I18n.t(bundle + ".hint"));
	}
}

package xyz.luan.games.hangman.texture;

import javafx.scene.control.Button;
import javafx.scene.control.ContentDisplay;
import javafx.scene.control.Label;
import javafx.scene.control.TextField;
import javafx.scene.image.Image;
import javafx.scene.image.ImageView;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.texture.TextType.TextTypeReference;

public final class FxHelper {

	private FxHelper() {
		throw new RuntimeException("Should not be isntanciated.");
	}

	public static Label createLabel(TextType type, String bundle) {
		return createRawLabel(type, I18n.t(bundle));
	}

	public static Label createEmptyLabel(TextType type) {
		return createRawLabel(type, "");
	}

	public static Label createRawLabel(TextType type, String text) {
		Label label = new Label(text);
		setupLabel(type, label);
		return label;
	}

	public static void setupLabel(TextType type, Label label) {
		TextTypeReference reference = PackManager.pack().getReference(type);
		label.setFont(reference.getFont());
		label.setTextFill(reference.getColor());
	}

	public static Button createButton(String bundle) {
		Button button = new Button(I18n.t(bundle));
		setupButton(button);
		return button;
	}

	public static Button createEmptyButton() {
		Button button = new Button();
		setupButton(button);
		return button;
	}

	public static void setupButton(Button button) {
		TextTypeReference reference = PackManager.pack().getReference(TextType.BUTTON);
		button.setFont(reference.getFont());
		button.setTextFill(reference.getColor());
	}

	public static TextField createTextField() {
		TextField field = new TextField();
		setupTextField(field);
		return field;
	}

	public static void setupTextField(TextField field) {
		TextTypeReference reference = PackManager.pack().getReference(TextType.FORM_LABEL);
		field.setFont(reference.getFont());
		// TODO set color
	}

	public static Label createImageLabel(Image icon) {
		Label label = new Label();
		label.setGraphic(new ImageView(icon));
		label.setContentDisplay(ContentDisplay.GRAPHIC_ONLY);
		return label;
	}
}

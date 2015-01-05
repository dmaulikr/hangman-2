package xyz.luan.games.hangman.client.scenes;

import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TextField;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.MainGameStatus;
import xyz.luan.games.hangman.game.forms.FormUtils;
import xyz.luan.games.hangman.messaging.client.LoginMessage;
import xyz.luan.games.hangman.messaging.client.LogoutMessage;
import xyz.luan.games.hangman.texture.FxHelper;
import xyz.luan.games.hangman.texture.TextType;
import xyz.luan.games.hangman.util.PasswordHasher;

public class LoginScene extends ClientScene {

	private Label errors;
	private TextField username, password;

	@Override
	protected Pane generatePane() {
		GridPane pane = FormUtils.defaultGrid();

		createTitle(pane);
		createErrorsLabel(pane);
		createFormFields(pane);
		createActionButtons(pane);
		createBottomButtons(pane);

		return pane;
	}

	private void createBottomButtons(GridPane pane) {
		// TODO use StateChangeButton here ?
		Button registerButton = FxHelper.createButton("client.register.title");
		registerButton.setOnAction(e -> {
			mainRef.setStatus(ClientStatus.REGISTER);
		});
		pane.add(registerButton, 0, 5, 2, 1);
	}

	private void createActionButtons(GridPane pane) {
		Button okButton = FxHelper.createButton("common.ok");
		okButton.setOnAction(e -> {
			client.sendMessage(new LoginMessage(username.getText(), PasswordHasher.hash(password.getText())));
		});
		pane.add(okButton, 0, 4);

		// TODO use StateChangeButton here ?
		Button cancelButton = FxHelper.createButton("common.cancel");
		cancelButton.setOnAction(e -> {
			mainRef.disconnect();
			mainRef.setStatus(MainGameStatus.CONNECT_TO_SERVER);
		});
		pane.add(cancelButton, 1, 4);
	}

	private void createFormFields(GridPane pane) {
		pane.add(FxHelper.createLabel(TextType.FORM_LABEL, "game.profile.username"), 0, 2);
		username = FxHelper.createTextField();
		pane.add(username, 1, 2);

		pane.add(FxHelper.createLabel(TextType.FORM_LABEL, "client.register.password"), 0, 3);
		password = new PasswordField();
		pane.add(password, 1, 3);
	}

	private void createErrorsLabel(GridPane pane) {
		errors = FxHelper.createEmptyLabel(TextType.FORM_LABEL);
		pane.add(errors, 0, 1, 2, 1);
	}

	private void createTitle(GridPane pane) {
		pane.add(FxHelper.createLabel(TextType.TITLE, "client.login.title"), 0, 0, 2, 1);
	}

	public void setErrors(String... errors) {
		LoginScene.this.errors.setText(FormUtils.convertErrorMessages(errors));
	}

}

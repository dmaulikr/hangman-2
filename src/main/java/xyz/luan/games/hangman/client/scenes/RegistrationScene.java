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
import xyz.luan.games.hangman.game.forms.FormUtils;
import xyz.luan.games.hangman.messaging.client.RegistrationMessage;
import xyz.luan.games.hangman.util.PasswordHasher;

public class RegistrationScene extends ClientScene {

	private Label errors;
	private TextField username, password, passwordRepeat;

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
		Button registerButton = new Button(I18n.t("client.login.title"));
		registerButton.setOnAction(e -> {
			mainRef.setStatus(ClientStatus.LOGIN);
		});
		pane.add(registerButton, 0, 6, 2, 1);
	}

	private void createActionButtons(GridPane pane) {
		Button okButton = new Button(I18n.t("common.ok"));
		okButton.setOnAction(e -> {
			String passwordString = password.getText();
			if (passwordRepeat.getText().equals(passwordString)) {
				errors.setText(I18n.t("common.loading"));
				client.sendMessage(new RegistrationMessage(username.getText(), PasswordHasher.hash(passwordString)));
			} else {
				errors.setText(I18n.t("client.register.errors.passwordsDontMatch"));
			}
		});
		pane.add(okButton, 0, 5);
		pane.add(new StateChangeButton("common.cancel", ClientStatus.LOGIN), 1, 5);
	}

	private void createFormFields(GridPane pane) {
		pane.add(new Label(I18n.t("game.profile.username")), 0, 2);
		username = new TextField();
		pane.add(username, 1, 2);

		pane.add(new Label(I18n.t("client.register.password")), 0, 3);
		password = new PasswordField();
		pane.add(password, 1, 3);

		pane.add(new Label(I18n.t("client.register.repeat_password")), 0, 4);
		passwordRepeat = new PasswordField();
		pane.add(passwordRepeat, 1, 4);
	}

	private void createErrorsLabel(GridPane pane) {
		errors = new Label("");
		pane.add(errors, 0, 1, 2, 1);
	}

	/* TODO extract this to FormUtils maybe? */
	private void createTitle(GridPane pane) {
		Text sceneTitle = new Text(I18n.t("client.register.title"));
		sceneTitle.getStyleClass().add("title");
		pane.add(sceneTitle, 0, 0, 2, 1);
	}

	public void setErrors(String... errors) {
		RegistrationScene.this.errors.setText(FormUtils.convertErrorMessages(errors));
	}

}

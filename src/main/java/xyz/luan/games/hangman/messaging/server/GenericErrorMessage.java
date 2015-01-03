package xyz.luan.games.hangman.messaging.server;

import lombok.AllArgsConstructor;
import xyz.luan.games.hangman.client.Client;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.forms.DialogHelper;

@AllArgsConstructor
public class GenericErrorMessage implements ServerMessage {

	private static final long serialVersionUID = -1363159170719142255L;

	private Error error;

	public enum Error {
		MUST_BE_LOGGED_IN, CANNOT_BE_LOGGED_IN;
	}

	@Override
	public void handle(Client client) {
		DialogHelper.show(I18n.t("common.error"), I18n.t(error.name().toLowerCase()));
	}

}
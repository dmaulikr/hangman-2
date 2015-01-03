package xyz.luan.games.hangman.messaging.server;

import lombok.AllArgsConstructor;
import xyz.luan.games.hangman.client.Client;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.forms.DialogHelper;
import xyz.luan.games.hangman.messaging.client.ClientMessage.Requirement;

@AllArgsConstructor
public class GenericErrorMessage implements ServerMessage {

	private Requirement error;

	@Override
	public void handle(Client client) {
		DialogHelper.show(I18n.t("common.error"), I18n.t("errors." + error.name().toLowerCase()));
	}

}
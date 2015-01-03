package xyz.luan.games.hangman.messaging.server;

import lombok.AllArgsConstructor;
import xyz.luan.games.hangman.client.Client;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.client.scenes.LoginScene;
import xyz.luan.games.hangman.client.scenes.RegistrationScene;
import xyz.luan.games.hangman.game.Profile;

@AllArgsConstructor
public class RegistrationResponse implements ServerMessage {

	private static final long serialVersionUID = 5155627622031471945L;

	private Status status;

	public enum Status {
		USERNAME_EMPTY, USERNAME_ALREADY_REGISTERED, OK {
			@Override
			public String toString() {
				return "client.register.success";
			}
		};

		@Override
		public String toString() {
			return "client.register.errors." + this.name().toLowerCase();
		}
	}

	@Override
	public void handle(Client client) {
		client.getCurrentScene().as(RegistrationScene.class).setErrors(status.toString());
	}
}

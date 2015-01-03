package xyz.luan.games.hangman.messaging.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.luan.games.hangman.client.Client;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.client.scenes.LoginScene;
import xyz.luan.games.hangman.game.Profile;

@AllArgsConstructor
public class LoginResponse implements ServerMessage {

	private static final long serialVersionUID = 5155627622031471945L;

	private Status status;

	@Getter
	private Profile profile;

	public LoginResponse(Status status) {
		this(status, null);
		assert status != Status.OK;
	}

	public enum Status {
		USERNAME_NOT_FOUND {
			@Override
			public String toString() {
				return "client.login.errors.usernameNotFound";
			}
		},
		WRONG_PASSWORD {
			@Override
			public String toString() {
				return "client.login.errors.wrongPassword";
			}
		},
		OK;
	}

	@Override
	public void handle(Client client) {
		if (status == Status.OK) {
			client.setMode(ClientStatus.LOBBY);
			client.getData().setMe(profile);
		} else {
			client.getCurrentScene().as(LoginScene.class).setErrors(status.toString());
		}
	}
}

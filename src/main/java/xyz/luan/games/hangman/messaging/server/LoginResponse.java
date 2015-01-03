package xyz.luan.games.hangman.messaging.server;

import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import xyz.luan.games.hangman.client.Client;
import xyz.luan.games.hangman.client.ClientData;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.client.scenes.Lobby;
import xyz.luan.games.hangman.client.scenes.LoginScene;
import xyz.luan.games.hangman.game.Profile;

public class LoginResponse implements ServerMessage {

	private static final long serialVersionUID = 5155627622031471945L;

	@Getter
	private Status status;

	@Getter
	private Profile profile;

	@Getter
	@Setter
	private Set<Profile> loggedUsers;

	public LoginResponse(Status status, Profile profile) {
		this.status = status;
		this.profile = profile;
	}

	public LoginResponse(Status status) {
		this(status, null);
		assert status != Status.OK;
	}

	public enum Status {
		USERNAME_NOT_FOUND, WRONG_PASSWORD, ALREADY_LOGGED_IN, OK;

		@Override
		public String toString() {
			return "client.login.errors." + this.name().toLowerCase();
		}
	}

	@Override
	public void handle(Client client) {
		if (status == Status.OK) {
			client.setData(new ClientData(profile, loggedUsers));
			client.setMode(ClientStatus.LOBBY);
		} else {
			client.getCurrentScene().perform(LoginScene.class, s -> s.setErrors(status.toString()));
		}
	}
}

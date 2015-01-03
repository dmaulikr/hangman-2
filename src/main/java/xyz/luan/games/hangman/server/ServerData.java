package xyz.luan.games.hangman.server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import xyz.luan.games.hangman.messaging.server.LoginResponse;
import xyz.luan.games.hangman.messaging.server.RegistrationResponse;
import xyz.luan.games.hangman.messaging.server.RegistrationResponse.Status;
import xyz.luan.games.hangman.util.PasswordHasher;

public class ServerData implements Serializable {

	private static final long serialVersionUID = -5429690591374314006L;

	private Map<String, User> users;

	public ServerData() {
		this.users = new HashMap<>();
		this.users.put("luan", new User("luan", PasswordHasher.hash("123")));
	}

	public LoginResponse login(String username, String passwordHash) {
		User user = users.get(username);
		if (user == null) {
			return new LoginResponse(LoginResponse.Status.USERNAME_NOT_FOUND);
		}
		if (!user.getPasswordHash().equals(passwordHash)) {
			return new LoginResponse(LoginResponse.Status.WRONG_PASSWORD);
		}
		return new LoginResponse(LoginResponse.Status.OK, user.getProfile());
	}

	public RegistrationResponse register(String username, String passwordHash) {
		if (username == null || username.isEmpty()) {
			return new RegistrationResponse(Status.EMPTY_USERNAME);
		}
		User user = users.get(username);
		if (user != null) {
			return new RegistrationResponse(Status.USERNAME_ALREADY_REGISTERED);
		}
		users.put(username, new User(username, passwordHash));
		return new RegistrationResponse(Status.OK);
	}
}

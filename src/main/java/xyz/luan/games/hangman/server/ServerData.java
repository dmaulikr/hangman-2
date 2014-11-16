package xyz.luan.games.hangman.server;

import java.io.Serializable;
import java.util.HashMap;
import java.util.Map;

import xyz.luan.games.hangman.messaging.server.LoginResponse;

public class ServerData implements Serializable {

    private static final long serialVersionUID = -5429690591374314006L;

    private Map<String, User> users;

    public ServerData() {
        this.users = new HashMap<>();
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
}

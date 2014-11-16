package xyz.luan.games.hangman.messaging.client;

import lombok.AllArgsConstructor;
import xyz.luan.games.hangman.messaging.server.ServerMessage;
import xyz.luan.games.hangman.server.Server;
import xyz.luan.games.hangman.server.Server.ClientHandler;

@AllArgsConstructor
public class LoginMessage implements ClientMessage {

    private static final long serialVersionUID = 7467992423968139457L;

    private String username;
    private String passwordHash;

    @Override
    public ServerMessage handle(Server server, ClientHandler client) {
        return server.getData().login(username, passwordHash);
    }
}

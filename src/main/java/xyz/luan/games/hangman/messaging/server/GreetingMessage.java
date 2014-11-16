package xyz.luan.games.hangman.messaging.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.luan.games.hangman.client.Client;

@AllArgsConstructor
public class GreetingMessage implements ServerMessage {

    private static final long serialVersionUID = -1363159170719142255L;

    @Getter
    private String applicationName;

    @Override
    public void handle(Client client) {
        // TODO Auto-generated method stub
    }

}
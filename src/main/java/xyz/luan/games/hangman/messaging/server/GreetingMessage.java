package xyz.luan.games.hangman.messaging.server;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.luan.games.hangman.client.ClientData;

@AllArgsConstructor
public class GreetingMessage implements ServerMessage {

    @Getter
    private String applicationName;

    @Override
    public void handle(ClientData data) {
        // TODO Auto-generated method stub
    }

}
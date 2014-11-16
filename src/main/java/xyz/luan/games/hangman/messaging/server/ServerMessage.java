package xyz.luan.games.hangman.messaging.server;

import xyz.luan.games.hangman.client.ClientData;

public interface ServerMessage {

    public void handle(ClientData data);
}

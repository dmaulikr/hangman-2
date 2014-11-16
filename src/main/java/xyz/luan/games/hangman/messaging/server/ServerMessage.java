package xyz.luan.games.hangman.messaging.server;

import java.io.Serializable;

import xyz.luan.games.hangman.client.Client;

public interface ServerMessage extends Serializable {

    public void handle(Client client);
}

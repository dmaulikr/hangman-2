package xyz.luan.games.hangman.server;

import xyz.luan.games.hangman.server.ServerHandler.ClientHandler;

public interface ConnectionListener {

    public void connected(ClientHandler c);
    public void disconnected(ClientHandler c);
}

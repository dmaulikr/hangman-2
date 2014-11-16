package xyz.luan.games.hangman.messaging.client;

import xyz.luan.games.hangman.messaging.server.ServerMessage;
import xyz.luan.games.hangman.server.Server;
import xyz.luan.games.hangman.server.Server.ClientHandler;

public interface ClientMessage {

    ServerMessage handle(Server server, ClientHandler client);
}

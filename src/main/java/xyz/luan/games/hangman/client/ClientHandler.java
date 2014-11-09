package xyz.luan.games.hangman.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import xyz.luan.games.hangman.game.ConfigManager;

public class ClientHandler {

    private ObjectInputStream in;
    private ObjectOutputStream out;

    public ClientHandler(String ip) throws IOException {
        this(getSocket(ip));
    }

    public ClientHandler(Socket socket) throws IOException {
        out = new ObjectOutputStream(socket.getOutputStream());
        in = new ObjectInputStream(socket.getInputStream());
    }

    private static Socket getSocket(String ip) throws UnknownHostException, IOException {
        String[] parts = ip.split(":");
        if (parts.length > 2) {
            throw new IOException("Invalid host! Cannot have more than one ':' in the ip address.");
        }
        String host = parts[0];
        int port;
        if (parts.length > 1) {
            try {
                port = Integer.parseInt(parts[1]);
            } catch (NumberFormatException ex) {
                throw new IOException("Invalid host! Port must be a number.");
            }
        } else {
            port = ConfigManager.general.config().getPort();
        }
        return new Socket(host, port);
    }
}

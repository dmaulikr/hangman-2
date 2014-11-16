package xyz.luan.games.hangman.client;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.Socket;
import java.net.UnknownHostException;

import xyz.luan.games.hangman.game.ConfigManager;
import xyz.luan.games.hangman.messaging.client.ClientMessage;
import xyz.luan.games.hangman.messaging.server.ServerMessage;

public class Client extends Thread {

    private ObjectInputStream in;
    private ObjectOutputStream out;
    private boolean running;
    private ClientData data;

    // TODO think it through
    private FormScreenInterface bindedInterface;

    public Client(String ip) throws IOException {
        this(getSocket(ip));
    }

    public Client(Socket socket) throws IOException {
        this.out = new ObjectOutputStream(socket.getOutputStream());
        this.in = new ObjectInputStream(socket.getInputStream());
        this.running = true;
    }

    @Override
    public void run() {
        while (running) {
            processMessage(readMessage());
        }
    }

    private void processMessage(ServerMessage message) {
        message.handle(data);
    }

    public void sendMessage(ClientMessage message) {
        try {
            out.writeObject(message);
        } catch (IOException e) {
            throw new RuntimeException(e); // TODO handle properly
        }
    }

    private ServerMessage readMessage() {
        try {
            return (ServerMessage) in.readObject();
        } catch (ClassNotFoundException | IOException e) {
            throw new RuntimeException(e); // TODO handle properly
        }
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

    public void bindInterface(FormScreenInterface binding) {
        this.bindedInterface = binding;
    }
}

package xyz.luan.games.hangman.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.luan.games.hangman.messaging.Message;

public final class ServerHandler {

    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private ServerSocket server;
    private List<ClientHandler> handlers;

    public ServerHandler(int port) throws IOException {
        this.handlers = new ArrayList<>();
        this.server = new ServerSocket(port);
    }

    public void run() {
        while (true) {
            try {
                processSocket(server.accept());
            } catch (IOException e) {
                logger.error("Unable to connect to client", e);
            }
        }
    }

    public void quit() {
        for (ClientHandler c : handlers) {
            c.sendMessage(new Message("Server was shutdown. Bye, bye!"));
            c.quit();
        }
    }

    private void processSocket(Socket socket) throws IOException {
        ClientHandler handler = new ClientHandler(socket);
        handlers.add(handler);
        handler.run();
    }

    public class ClientHandler extends Thread {

        private boolean running;
        private ObjectInputStream in;
        private ObjectOutputStream out;

        public ClientHandler(Socket socket) throws IOException {
            in = new ObjectInputStream(socket.getInputStream());
            out = new ObjectOutputStream(socket.getOutputStream());
        }

        @Override
        public void run() {
            this.running = true;
            while (this.running) {
                try {
                    Message m = readMessage();
                    processMessage(m);
                } catch (InvalidCommunicationException ex) {
                    handleError(ex);
                }
            }
        }

        private void handleError(Exception ex) {
            logger.error("Invalid communication with client. Ending connection.", ex);
            quit();
        }

        private void processMessage(Message m) {
            sendMessage(new Message("echo: " + m.getText()));
        }

        public void sendMessage(Message m) {
            try {
                out.writeObject(m);
            } catch (IOException ex) {
                handleError(ex);
            }
        }

        private Message readMessage() throws InvalidCommunicationException {
            try {
                return (Message) in.readObject();
            } catch (IOException | ClassCastException | ClassNotFoundException e) {
                throw new InvalidCommunicationException("", e);
            }
        }

        private class InvalidCommunicationException extends Exception {
            private static final long serialVersionUID = 3054633885301026467L;

            public InvalidCommunicationException(String message, Throwable t) {
                super(message, t);
            }
        }

        public void quit() {
            this.running = false;
            handlers.remove(this);
        }
    }

}

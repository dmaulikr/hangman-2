package xyz.luan.games.hangman.server;

import java.io.EOFException;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.luan.games.hangman.messaging.Message;

public final class ServerHandler extends Thread {

    private static final Logger logger = LoggerFactory.getLogger(ServerHandler.class);

    private ServerSocket server;
    private List<ClientHandler> handlers;
    private ConnectionListener listener;

    public ServerHandler(int port, ConnectionListener listener) throws IOException {
        this.listener = listener;
        this.handlers = new ArrayList<>();
        this.server = new ServerSocket(port);
    }

    @Override
    public void run() {
        while (true) {
            try {
                processSocket(server.accept());
            } catch (SocketException serverQuitted) {
            } catch (IOException e) {
                logger.error("Unable to connect to client", e);
            }
        }
    }

    public void quit() {
        handlers.stream().forEach(c -> c.notifyQuit());
        closeConnection();
    }

    private void closeConnection() {
        try {
            server.close();
        } catch (IOException e) {
            logger.error("Unable to close server socket", e);
        }
    }

    private void processSocket(Socket socket) throws IOException {
        ClientHandler handler = new ClientHandler(socket);
        handlers.add(handler);
        listener.connected(handler);
        handler.start();
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
                } catch (EOFException clientQuitted) {
                    quit();
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

        private Message readMessage() throws EOFException, InvalidCommunicationException {
            try {
                return (Message) in.readObject();
            } catch (EOFException e) {
                throw e;
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

        public void notifyQuit() {
            this.sendMessage(new Message("Server was shutdown. Bye, bye!"));
            this.running = false;
        }

        public void quit() {
            this.running = false;
            handlers.remove(this);
            listener.disconnected(this);
        }
    }
}

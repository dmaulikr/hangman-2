package xyz.luan.games.hangman.server;

import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.net.ServerSocket;
import java.net.Socket;
import java.net.SocketException;
import java.util.ArrayList;
import java.util.List;

import lombok.Getter;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.luan.games.hangman.messaging.client.ClientMessage;
import xyz.luan.games.hangman.messaging.server.QuitMessage;
import xyz.luan.games.hangman.messaging.server.ServerMessage;

public final class Server extends Thread {

	private static final Logger logger = LoggerFactory.getLogger(Server.class);

	private ServerSocket server;
	private ConnectionListener listener;

	@Getter
	private List<ClientHandler> handlers;

	@Getter
	private ServerData data;

	public Server(int port, ConnectionListener listener) throws IOException {
		this.listener = listener;
		this.handlers = new ArrayList<>();
		this.server = new ServerSocket(port);

		/* TODO generate from configurations */
		this.data = new ServerData();
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
					ClientMessage m = readMessage();
					processMessage(m);
				} catch (SocketException clientQuitted) {
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

		private void processMessage(ClientMessage m) {
			ServerMessage response = m.handle(Server.this, this);
			if (response != null) {
				sendMessage(response);
			}
		}

		public void sendMessage(ServerMessage m) {
			try {
				out.writeObject(m);
			} catch (IOException ex) {
				handleError(ex);
			}
		}

		private ClientMessage readMessage() throws SocketException, InvalidCommunicationException {
			try {
				return (ClientMessage) in.readObject();
			} catch (SocketException clientQuitted) {
				throw clientQuitted;
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
			this.sendMessage(new QuitMessage());
			stopConnection();
		}

		private void stopConnection() {
			this.running = false;
			try {
				this.in.close();
				this.out.close();
			} catch (IOException e) {
				logger.error("Unable to close connection with client", e);
			}
		}

		public void quit() {
			stopConnection();
			handlers.remove(this);
			listener.disconnected(this);
		}
	}
}

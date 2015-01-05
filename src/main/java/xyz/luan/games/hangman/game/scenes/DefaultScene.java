package xyz.luan.games.hangman.game.scenes;

import java.lang.annotation.ElementType;
import java.lang.annotation.Retention;
import java.lang.annotation.RetentionPolicy;
import java.lang.annotation.Target;
import java.lang.reflect.InvocationTargetException;
import java.lang.reflect.Method;
import java.util.Arrays;
import java.util.function.Predicate;

import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.Scene;
import javafx.scene.control.Button;
import javafx.scene.layout.Pane;
import xyz.luan.games.hangman.game.ConfigManager;
import xyz.luan.games.hangman.game.GameStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.Main;
import xyz.luan.games.hangman.messaging.server.ServerMessage;
import xyz.luan.games.hangman.texture.FxHelper;

public abstract class DefaultScene {

	protected Main mainRef;

	public void setMain(Main mainRef) {
		this.mainRef = mainRef;
	}

	protected abstract Pane generatePane();

	public Scene generateScene() {
		return new Scene(generatePane(), ConfigManager.general.config().getWidth(), ConfigManager.general.config().getHeight());
	}

	public class StateChangeButton extends Button {

		public StateChangeButton(String text, GameStatus newState) {
			setup(text, event -> mainRef.setStatus(newState));
		}

		private StateChangeButton(String text, EventHandler<ActionEvent> event) {
			setup(text, event);
		}

		private void setup(String text, EventHandler<ActionEvent> event) {
			this.setText(I18n.t(text));
			FxHelper.setupButton(this);
			this.setMaxSize(Double.MAX_VALUE, this.getHeight());
			this.setOnAction(event);
		}

	}

	public StateChangeButton stateChangeButtonWithEvent(String text, EventHandler<ActionEvent> event) {
		return new StateChangeButton(text, event);
	}

	/**
	 * Hook, to be overwritten
	 */
	public void closed() {
	}

	public void consume(ServerMessage message) {
		Predicate<? super Method> isMessageHandler = m -> m.getAnnotation(MessageHandler.class) != null;
		Predicate<? super Method> isHandlerForMessage = m -> m.getParameters().length == 1
				&& m.getParameters()[0].getType().isAssignableFrom(message.getClass());
		Arrays.stream(this.getClass().getDeclaredMethods()).filter(isMessageHandler).filter(isHandlerForMessage).forEach(m -> {
			try {
				m.invoke(this, message);
			} catch (IllegalAccessException | IllegalArgumentException | InvocationTargetException e) {
				throw new RuntimeException(e);
			}
		});
	}

	@Target(ElementType.METHOD)
	@Retention(RetentionPolicy.RUNTIME)
	public @interface MessageHandler {
	}
}
package xyz.luan.games.hangman.server;

import java.io.Serializable;
import java.lang.reflect.Field;

import lombok.Getter;
import xyz.luan.games.hangman.game.forms.FormValidations;
import xyz.luan.games.hangman.game.forms.InvalidFormException;

public class ServerConfig implements Serializable {

	private static final long serialVersionUID = 8601764662632179292L;

	@Getter
	private int maxSelectedSpells;

	public ServerConfig() {
		this.maxSelectedSpells = 8;
	}

	public String get(String field) {
		try {
			Field fieldRef = this.getClass().getDeclaredField(field);
			fieldRef.setAccessible(true);
			Object value = fieldRef.get(this);
			return String.valueOf(value);
		} catch (NoSuchFieldException | IllegalAccessException ex) {
			throw new RuntimeException("Invalid field access on ServerConfig: " + field, ex);
		}
	}

	public void set(String field, String value) throws InvalidFormException {
		switch (field) {
		case "maxSelectedSpells":
			this.maxSelectedSpells = FormValidations.toNatural(value);
			break;
		default:
			throw new RuntimeException("Invalid field access on GeneralConfig: " + field);
		}
	}
}
package xyz.luan.games.hangman.client;

import java.util.Arrays;
import java.util.stream.Collectors;

import xyz.luan.games.hangman.game.I18n;

public interface FormScreenInterface {

	public void setErrors(String... errors);

	public static String convert(String... errors) {
		return Arrays.stream(errors).map(t -> I18n.t(t)).collect(Collectors.joining("\n"));
	}
}

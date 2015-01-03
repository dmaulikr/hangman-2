package xyz.luan.games.hangman.game.forms;

import java.util.Arrays;
import java.util.stream.Collectors;

import xyz.luan.games.hangman.game.I18n;
import javafx.geometry.Insets;
import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;

public final class FormUtils {

	private FormUtils() {
		throw new RuntimeException("Should not be instanciated.");
	}

	public static String convertErrorMessages(String[] errors) {
		return Arrays.stream(errors).map(t -> I18n.t(t)).collect(Collectors.joining("\n"));
	}

	public static GridPane defaultGrid() {
		GridPane grid = new GridPane();
		grid.setAlignment(Pos.CENTER);
		grid.setHgap(10);
		grid.setVgap(10);
		grid.setPadding(new Insets(25, 25, 25, 25));
		return grid;
	}
}
package xyz.luan.games.hangman.game.scenes;

import static xyz.luan.games.hangman.game.MainGameStatus.CONNECT_TO_SERVER;
import static xyz.luan.games.hangman.game.MainGameStatus.GENERAL_CONFIG;
import static xyz.luan.games.hangman.game.MainGameStatus.QUIT;
import static xyz.luan.games.hangman.game.MainGameStatus.SERVER_CONFIG;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import xyz.luan.games.hangman.game.MainGameStatus;
import xyz.luan.games.hangman.game.forms.FormUtils;
import xyz.luan.games.hangman.texture.FxHelper;
import xyz.luan.games.hangman.texture.TextType;

public class MenuScene extends DefaultScene {
	private static final String[] ITEMS = { "host", "connect", "options", "exit" };
	private static final MainGameStatus[] ACTIONS = { SERVER_CONFIG, CONNECT_TO_SERVER, GENERAL_CONFIG, QUIT };

	@Override
	protected Pane generatePane() {
		GridPane grid = FormUtils.defaultGrid();

		grid.add(FxHelper.createLabel(TextType.GAME_TITLE, "main.title"), 0, 0);

		assert ITEMS.length == ACTIONS.length;
		for (int i = 0; i < ITEMS.length; i++) {
			grid.add(new StateChangeButton("main.menu." + ITEMS[i], ACTIONS[i]), 0, i + 1);
		}

		return grid;
	}
}
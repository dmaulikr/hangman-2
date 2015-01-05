package xyz.luan.games.hangman.drawing;

import javafx.scene.layout.BorderPane;
import xyz.luan.games.hangman.game.Profile;

public class ExtendedPlayerCard extends BorderPane {

	private PlayerCard baseCard;
	private ActionsPopup actionsPopup;

	public ExtendedPlayerCard(Profile profile) {
		this.baseCard = new PlayerCard(profile);
		this.actionsPopup = new ActionsPopup(profile);
		setup();
	}

	private void setup() {
		this.setCenter(baseCard);
		this.setRight(actionsPopup);
	}
}

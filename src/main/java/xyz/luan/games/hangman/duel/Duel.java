package xyz.luan.games.hangman.duel;

import java.util.List;

import xyz.luan.games.hangman.drawing.PlayerColor;
import xyz.luan.games.hangman.game.Profile;

public class Duel {

	private DuelOptions options;
	private Word word;
	private Player[] players;

	private int currentTurn;

	public Duel(DuelOptions options, List<Profile> profiles) {
		this.options = options;
		PlayerColor[] colors = this.options.getColorOrder();
		if (profiles.size() > colors.length) {
			throw new IllegalArgumentException("You can't have more players than colors.");
		}

		this.players = new Player[profiles.size()];
		for (int i = 0; i < this.players.length; i++) {
			this.players[i] = new Player(options, profiles.get(i), colors[i], profiles.get(i).getSelectedSpells());
		}

		this.word = null; // TODO
		this.currentTurn = 0;
	}

	public int getPlayerCode(Player p) {
		for (int i = 0; i < players.length; i++) {
			if (players[i] == p) {
				return i;
			}
		}
		return -1;
	}
}
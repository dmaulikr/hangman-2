package xyz.luan.games.hangman.drawing;

import java.io.Serializable;

import lombok.Getter;

public class ScreenOptions implements Serializable {

	private static final long serialVersionUID = 8047807445043986777L;

	/**
	 * Spells and avatars
	 */
	@Getter
	private int bigTileSize;

	/**
	 * Letters
	 */
	@Getter
	private int smallTileSize;

	/**
	 * Main bar (game name and menu)
	 * Duel bar (turn, player, phase)
	 * Word
	 * Own Player
	 * The rest of the space will be divided for all the other players
	 */
	private double[] sections;

	/**
	 * fixDimension can be:
	 * 0 - Horizontal
	 * 1 - Vertical
	 * 
	 * fixedAmount will be fixed in the desired orientation, and the other one will be expanded to fit all players
	 */
	private int fixDimension;
	private int fixedAmount;

	public ScreenOptions() {
		this.bigTileSize = 64;
		this.smallTileSize = 32;

		this.sections = new double[] { 3f / 28f, 1f / 28f, 12f / 28f };

		this.fixDimension = 0;
		this.fixedAmount = 2;
	}
}
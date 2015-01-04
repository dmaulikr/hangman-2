package xyz.luan.games.hangman.drawing;

import java.awt.Graphics2D;

import xyz.luan.games.hangman.duel.Player;

public class PlayerChartDrawing extends Drawing {

	private static final long serialVersionUID = 5615876555094412722L;

	private String name;

	private String avatar;
	private int lives, points;
	private double mana;

	public PlayerChartDrawing(Player p) {
		super(p.getColor());
		this.name = p.getProfile().getUsername();
		this.avatar = p.getProfile().getAvatar();
		this.lives = p.getLives();
		this.mana = p.getMana();
	}

	public void render(Graphics2D g, int x, int y, int tileSize) {
		// TODO
	}

}
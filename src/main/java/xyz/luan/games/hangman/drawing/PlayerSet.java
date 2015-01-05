package xyz.luan.games.hangman.drawing;

import java.util.Set;

import javafx.geometry.Pos;
import javafx.scene.layout.GridPane;
import xyz.luan.games.hangman.game.Profile;

public class PlayerSet extends GridPane {

	private static final int PER_ROW = 4;

	private Set<Profile> players;

	public PlayerSet(Set<Profile> players) {
		this.players = players;
		setProperties();
		makeGrid();
	}

	private void makeGrid() {
		getChildren().clear();
		int count = 0;
		for (Profile player : players) {
			add(new PlayerCard(player), count % PER_ROW, count / PER_ROW);
			count++;
		}
	}

	private void setProperties() {
		setAlignment(Pos.CENTER);
		setHgap(5);
		setVgap(5);
	}

	public void add(Profile player) {
		players.add(player);
		makeGrid();
	}

	public void remove(Profile player) {
		players.remove(player);
		makeGrid();
	}

	public void update(Profile player) {
		players.remove(player);
		players.add(player);
		makeGrid();
	}

}

package xyz.luan.games.hangman.server;

import java.io.Serializable;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.luan.games.hangman.game.Profile;

@AllArgsConstructor
public class Room implements Serializable, Comparable<Room> {

	private static final long serialVersionUID = -3600032491683953235L;

	@Getter
	private String name;

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof Room) {
			return this.compareTo((Room) obj) == 0;
		}
		return false;
	}

	@Override
	public int compareTo(Room user) {
		return this.getName().compareTo(user.getName());
	}

	@Override
	public int hashCode() {
		return this.getName().hashCode();
	}
}

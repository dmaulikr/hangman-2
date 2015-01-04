package xyz.luan.games.hangman.server;

import java.io.Serializable;

import lombok.Getter;
import lombok.Setter;
import xyz.luan.games.hangman.game.Profile;

public class User implements Serializable, Comparable<User> {

	private static final long serialVersionUID = -3600032491683953235L;

	@Getter
	private Profile profile;

	@Getter
	private String passwordHash;

	@Getter
	@Setter
	private transient boolean loggedIn = false;

	public User(String username, String passwordHash) {
		this.profile = new Profile(username);
		this.passwordHash = passwordHash;
	}

	@Override
	public boolean equals(Object obj) {
		if (obj instanceof User) {
			return this.compareTo((User) obj) == 0;
		}
		return false;
	}

	@Override
	public int compareTo(User user) {
		return this.profile.compareTo(user.profile);
	}

	@Override
	public int hashCode() {
		return this.profile.hashCode();
	}
}

package xyz.luan.games.hangman.client;

import java.util.Set;

import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.Setter;
import xyz.luan.games.hangman.game.Profile;

@AllArgsConstructor
public class ClientData {

	@Getter
	@Setter
	private Profile me;

	@Getter
	private Set<Profile> loggedUsers;

	public void notifyLogin(Profile profile) {
		loggedUsers.add(profile);
	}

	public void notifyLogout(Profile profile) {
		loggedUsers.remove(profile);
	}

	public void notifyProfileUpdate(Profile profile) {
		if (me.equals(profile)) {
			me = profile;
		}
		loggedUsers.remove(profile);
		loggedUsers.add(profile);
	}

}

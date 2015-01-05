package xyz.luan.games.hangman.messaging.client;

import java.util.List;

import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.luan.games.hangman.client.Client;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.client.scenes.MyProfile;
import xyz.luan.games.hangman.game.ConfigManager;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.Profile;
import xyz.luan.games.hangman.messaging.server.ServerMessage;
import xyz.luan.games.hangman.server.Server;
import xyz.luan.games.hangman.server.Server.ClientHandler;
import xyz.luan.games.hangman.spells.Spell;

@AllArgsConstructor
public class UpdateProfileRequest implements ClientMessage {

	@AllArgsConstructor
	private static class UpdateProfileError implements ServerMessage {

		private String error;

		@Override
		public void handle(Client client) {
			client.getCurrentScene().perform(MyProfile.class, s -> s.setError(error));
		}
	}

	public static class UpdateProfileNotification implements ServerMessage {

		@Getter
		private Profile profile;

		private UpdateProfileNotification(Profile profile) {
			this.profile = profile;
		}

		@Override
		public void handle(Client client) {
			client.getData().notifyProfileUpdate(profile);
		}
	}

	private String newAvatar;
	private List<Spell> selectedSpells;

	@Override
	public ServerMessage handle(Server server, ClientHandler client) {
		boolean updated = false;
		if (!client.getProfile().getSelectedSpells().equals(selectedSpells)) {
			for (Spell spell : selectedSpells) {
				if (!client.getProfile().getGrimoire().contains(spell)) {
					return new UpdateProfileError(I18n.t("game.profile.errors.invalid_spells_selected"));
				}
			}
			int maxSpells = ConfigManager.server.config().getMaxSelectedSpells();
			if (selectedSpells.size() > maxSpells) {
				return new UpdateProfileError(I18n.t("game.profile.errors.selected_too_many_spells", String.valueOf(maxSpells)));
			}
			client.getProfile().setSelectedSpells(selectedSpells);
			updated = true;
		}
		if (!client.getProfile().getAvatar().equals(newAvatar)) {
			client.getProfile().setAvatar(newAvatar);
			updated = true;
		}
		if (updated) {
			notifyChanges(server, client.getProfile());
		}
		return successMessage();
	}

	private void notifyChanges(Server server, Profile profile) {
		server.sendAll(new UpdateProfileNotification(profile));
	}

	private ServerMessage successMessage() {
		return new ServerMessage() {
			@Override
			public void handle(Client client) {
				client.setMode(ClientStatus.LOBBY);
			}
		};
	}

	@Override
	public Requirement requirement() {
		return Requirement.MUST_BE_LOGGED_IN;
	}

}

package xyz.luan.games.hangman.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import xyz.luan.games.hangman.spells.Spell;

@Getter
public class Profile implements Serializable {

	private static final long serialVersionUID = -5583798152789871501L;

	private String username;
	private String avatar;

	private int points;
	private Set<Spell> grimoire;
	private List<Spell> selectedSpells;

	private ProfileStatuses statuses;

	public Profile(String username) {
		this.username = username;
		this.avatar = "hangman://default";

		this.grimoire = new HashSet<>();
		this.selectedSpells = new ArrayList<>();

		this.points = 0;
		this.statuses = new ProfileStatuses();
	}

	@Override
	public String toString() {
		return this.username;
	}
}
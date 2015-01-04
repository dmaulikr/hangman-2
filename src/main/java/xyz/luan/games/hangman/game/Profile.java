package xyz.luan.games.hangman.game;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Getter;
import lombok.Setter;
import xyz.luan.games.hangman.spells.Spell;

@Getter
public class Profile implements Serializable, Comparable<Profile> {

	private static final long serialVersionUID = -5583798152789871502L;

	private String username;
	@Setter
	private String avatar;

	private int points;
	private Set<Spell> grimoire;
	@Setter
	private List<Spell> selectedSpells;

	private ProfileStatuses statuses;

	public Profile(String username) {
		this.username = username;
		this.avatar = "hangman://default_male.png";

		this.grimoire = new HashSet<>();
		// TODO remove, just for testing purposes
		this.grimoire.add(Spell.generateRandomSpell(1.0));
		this.grimoire.add(Spell.generateRandomSpell(0.75));
		this.grimoire.add(Spell.generateRandomSpell(0.5));
		this.grimoire.add(Spell.generateRandomSpell(0.25));
		this.selectedSpells = new ArrayList<>();

		this.points = 0;
		this.statuses = new ProfileStatuses();
	}

	@Override
	public String toString() {
		return this.username;
	}

	@Override
	public boolean equals(Object object) {
		if (this == object) {
			return true;
		}
		if (object instanceof Profile) {
			return ((Profile) object).getUsername().equals(this.username);
		}
		return false;
	}

	@Override
	public int compareTo(Profile profile) {
		return this.getUsername().compareTo(profile.getUsername());
	}

	@Override
	public int hashCode() {
		return this.getUsername().hashCode();
	}
}
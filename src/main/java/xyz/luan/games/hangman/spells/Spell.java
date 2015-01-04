package xyz.luan.games.hangman.spells;

import java.io.Serializable;
import java.util.ArrayList;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

import lombok.Data;
import xyz.luan.games.hangman.texture.SpellIcon;

@Data
public class Spell implements Serializable {

	private static final long serialVersionUID = -6679642597645667817L;

	private String name;
	private String description;
	private SpellIcon icon;

	private double manaCost;
	private Set<Requirement> costs;
	private List<Effect> effects;

	private Spell(double manaCost, Set<Requirement> costs, List<Effect> effetcs) {
		this.manaCost = manaCost;
		this.costs = costs;
		this.effects = effetcs;
	}

	private void generateIdentification() {
		// TODO proper generate name, desc and icon
		this.name = "Generic Spell III";
		this.description = "A super generic spell, that can do anything.";
		this.icon = SpellIcon.random();
	}

	public double getSpellLevel() {
		return 0; // TODO calculate this; must be near the selected level on generateRandomSpell, but not necessarily the same
	}

	// TODO proper generate random spell
	public static Spell generateRandomSpell(double expectedLevel) {
		Spell spell = new Spell(20, new HashSet<>(), new ArrayList<>());
		// TODO assert spell.getLevel() is about expectedLevel
		spell.generateIdentification();
		return spell;
	}
}
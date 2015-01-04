package xyz.luan.games.hangman.texture;

import static xyz.luan.games.hangman.texture.SpellIcon.Color.ACID;
import static xyz.luan.games.hangman.texture.SpellIcon.Color.AIR;
import static xyz.luan.games.hangman.texture.SpellIcon.Color.BLUE;
import static xyz.luan.games.hangman.texture.SpellIcon.Color.EERIE;
import static xyz.luan.games.hangman.texture.SpellIcon.Color.FIRE;
import static xyz.luan.games.hangman.texture.SpellIcon.Color.JADE;
import static xyz.luan.games.hangman.texture.SpellIcon.Color.MAGENTA;
import static xyz.luan.games.hangman.texture.SpellIcon.Color.ORANGE;
import static xyz.luan.games.hangman.texture.SpellIcon.Color.PLAIN;
import static xyz.luan.games.hangman.texture.SpellIcon.Color.RED;
import static xyz.luan.games.hangman.texture.SpellIcon.Color.ROYAL;
import static xyz.luan.games.hangman.texture.SpellIcon.Color.SKY;
import static xyz.luan.games.hangman.texture.SpellIcon.Color.SPIRIT;
import static xyz.luan.games.hangman.texture.SpellIcon.Color.WATER;

import java.io.Serializable;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;

import javafx.scene.image.Image;
import lombok.Data;

@Data
public class SpellIcon implements Serializable {

	private static final List<Integer> LEVELS = Arrays.asList(1, 2, 3);

	private Type type;
	private Color color;
	private int level;

	public SpellIcon(Type type, Color color, int level) {
		this.type = type;
		this.color = color;
		this.level = level;
		assertValid();
	}

	public String getName() {
		return this.type.name().toLowerCase() + "-" + this.color.name().toLowerCase() + "-" + this.level + ".png";
	}

	private void assertValid() {
		if (!type.colors.contains(color)) {
			throw new RuntimeException("Selected type " + type + " does not support color " + color + ".");
		}
	}

	public static SpellIcon random() {
		return random(selectRandom(LEVELS));
	}

	public static SpellIcon random(int level) {
		Type type = selectRandom(Type.types());
		Color color = selectRandom(type.colors);
		return new SpellIcon(type, color, level);
	}

	public static <T> T selectRandom(List<T> list) {
		return list.get((int) (Math.random() * list.size()));
	}

	public static Map<SpellIcon, Image> loadAllIcons(String path) {
		Map<SpellIcon, Image> spellIcons = new HashMap<>();
		Type.types().forEach(t -> t.colors.forEach(c -> LEVELS.forEach(l -> {
			SpellIcon icon = new SpellIcon(t, c, l);
			spellIcons.put(icon, new Image(path + "/" + icon.getName(), 32, 32, true, true)); // TODO << tileSize actually
			})));
		return spellIcons;
	}

	public enum Type {
		ENCHANT(BLUE, JADE, RED, ROYAL, ORANGE, SKY, EERIE, ACID, MAGENTA),
		FIREBALL(RED, SKY, EERIE, ACID),
		ICE(BLUE, JADE, SKY),
		LEAF(ROYAL, JADE, ACID, ORANGE),
		PROTECT(BLUE, ROYAL, ORANGE, ACID, EERIE, MAGENTA, SKY, RED, JADE),
		LIGHT(EERIE, JADE, MAGENTA, SKY, ROYAL, BLUE),
		RIP(WATER, JADE, SKY, ACID, MAGENTA),
		HEAL(SKY, ROYAL, JADE),
		WIND_GRASP(MAGENTA, EERIE, SKY, ACID, AIR),
		SHIELDING(ACID, EERIE, SPIRIT, FIRE),
		NEEDLES(BLUE, FIRE, ACID, ROYAL, SKY),
		WILD(ACID, EERIE, ORANGE, JADE),
		HORROR(EERIE, RED, ACID),
		LIGHTNING(ORANGE, MAGENTA, BLUE, JADE),
		BEAM(JADE, ACID, MAGENTA, ORANGE, EERIE, BLUE, RED, ROYAL, SKY),
		FIRE_ARROWS(RED, JADE, ROYAL, SKY, MAGENTA),
		AIR_BURST(MAGENTA, JADE, AIR, SKY),
		FOG(MAGENTA, BLUE, AIR, SKY, ACID, ORANGE),
		VINES(ACID, JADE, EERIE, PLAIN),
		EXPLOSION(MAGENTA, ROYAL, SKY, RED, ORANGE),
		RUNES(MAGENTA, ROYAL, BLUE, ORANGE),
		LIGHTING(EERIE, SKY, ACID, FIRE, ROYAL),
		HASTE(SKY, FIRE, ROYAL),
		WIND(MAGENTA, RED, SKY, BLUE),
		SLICE(ACID, ORANGE, SPIRIT, SKY),
		ROCK(SKY, PLAIN, ACID, ROYAL, ORANGE),
		LIGHT_AIR(FIRE),
		LINK(BLUE, SPIRIT, EERIE, ROYAL),
		FOG_WATER(AIR);

		private List<Color> colors;

		private Type(Color... colors) {
			this.colors = Arrays.asList(colors);
		}

		public static List<Type> types() {
			return Arrays.asList(values());
		}
	}

	public enum Color {
		BLUE,
		JADE,
		RED,
		ROYAL,
		ORANGE,
		SKY,
		EERIE,
		ACID,
		MAGENTA,
		WATER,
		AIR,
		SPIRIT,
		FIRE,
		PLAIN;
	}
}

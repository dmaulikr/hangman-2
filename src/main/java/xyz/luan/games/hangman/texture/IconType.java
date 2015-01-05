package xyz.luan.games.hangman.texture;

import java.io.File;
import java.io.InputStream;
import java.util.HashMap;
import java.util.Map;

import javafx.scene.image.Image;
import xyz.luan.games.hangman.texture.TexturePack.BadPackException;

public enum IconType {

	HELP("help.png"),
	STAR("star.png");

	private String name;

	private IconType(String name) {
		this.name = name;
	}

	public static Map<IconType, Image> loadIconsFromPath(String path) throws BadPackException {
		Map<IconType, Image> icons = new HashMap<>();
		for (IconType t : IconType.values()) {
			InputStream stream = PackManager.class.getResourceAsStream(path + "/" + t.name);
			if (stream == null) {
				throw new BadPackException("Icon " + t.name + " in icons/ folder was not found.");
			}
			icons.put(t, new Image(stream));
		}
		return icons;
	}
}

package xyz.luan.games.hangman.texture;

import java.io.InputStream;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;

import javafx.scene.image.Image;
import xyz.luan.games.hangman.texture.TexturePack.BadPackException;
import xyz.luan.games.hangman.texture.TileManager.TileType;

public enum DefaultAvatar {

	UNKNOWN("default_male.png"), DEFAULT_MALE("default_male.png"), DEFAULT_FEMALE("default_female.png");

	private String name;

	private DefaultAvatar(String name) {
		this.name = name;
	}

	public static Map<DefaultAvatar, Image> loadAvatarsFromPath(String path) throws BadPackException {
		Map<DefaultAvatar, Image> avatars = new HashMap<>();
		for (DefaultAvatar t : DefaultAvatar.values()) {
			InputStream stream = PackManager.class.getResourceAsStream(path + "/" + t.name);
			if (stream == null) {
				throw new BadPackException("Default avatar " + t.name + " in avatars/ folder was not found.");
			}
			avatars.put(t, TileManager.createTile(TileType.AVATAR, stream));
		}
		return avatars;
	}

	public String getUrl() {
		return "hangman://" + name;
	}

	public static List<DefaultAvatar> displayables() {
		return Arrays.stream(DefaultAvatar.values()).filter(avatar -> avatar != DefaultAvatar.UNKNOWN).collect(Collectors.toList());
	}

	public static DefaultAvatar get(String name) {
		return Arrays.stream(values()).filter(a -> a.name.equals(name)).findAny().orElse(UNKNOWN);
	}
}

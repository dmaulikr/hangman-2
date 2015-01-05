package xyz.luan.games.hangman.texture;

import java.util.Map;

import javafx.scene.image.Image;
import xyz.luan.games.hangman.spells.Spell;
import xyz.luan.games.hangman.texture.TextType.TextTypeReference;
import xyz.luan.games.hangman.texture.TileManager.TileType;

public class TexturePack {

	private Map<TextType, TextTypeReference> textMapping;
	private Map<IconType, Image> icons;
	private Map<DefaultAvatar, Image> defaultAvatars;
	private Map<SpellIcon, Image> spellIcons;

	public TexturePack(String name) throws BadPackException {
		String path = "/xyz/luan/games/hangman/textures/" + name;
		this.textMapping = TextType.Loader.loadFontsFromPath(path);
		this.icons = IconType.loadIconsFromPath(path + "/icons");
		this.defaultAvatars = DefaultAvatar.loadAvatarsFromPath(path + "/avatars");
		this.spellIcons = SpellIcon.loadAllIcons(path + "/spells");
	}

	public Image getSpellIcon(Spell spell) {
		return spellIcons.get(spell.getIcon());
	}

	public Image getAvatar(String url) {
		final String defaultAvatarProtocol = "hangman://";
		if (url.startsWith(defaultAvatarProtocol)) {
			return defaultAvatars.get(DefaultAvatar.get(url.substring(defaultAvatarProtocol.length())));
		}
		return TileManager.createTile(TileType.AVATAR, url);
	}

	public Image getIcon(IconType type) {
		return icons.get(type);
	}

	public TextTypeReference getReference(TextType font) {
		return textMapping.get(font);
	}

	public static class BadPackException extends Exception {

		private static final long serialVersionUID = 2649279410609906582L;

		public BadPackException(String message) {
			super(message);
		}

		public BadPackException(String message, Throwable cause) {
			super(message, cause);
		}
	}
}
package xyz.luan.games.hangman.texture;

import java.io.IOException;
import java.io.InputStream;
import java.util.Arrays;
import java.util.Map;

import javafx.scene.paint.Color;
import javafx.scene.text.Font;
import lombok.AllArgsConstructor;
import lombok.Getter;
import xyz.luan.games.hangman.texture.TexturePack.BadPackException;

import com.eclipsesource.json.JsonObject;

public enum TextType {
	GAME_TITLE("GAME_TITLE"),
	TITLE("TITLE"),
	BUTTON("BUTTON"),
	FORM_LABEL("FORM_LABEL"),
	PLAYER_CARD("PLAYER_CARD"),
	LETTER("LETTER"),
	TEXT("TEXT");

	private String name;

	private TextType(String name) {
		this.name = name;
	}

	public static TextType getByName(String name) {
		return Arrays.stream(values()).filter(t -> t.name.equals(name)).findAny().orElse(null);
	}

	@AllArgsConstructor
	@Getter
	public static class TextTypeReference {
		private Font font;
		private Color color;
	}

	static class Loader {

		private static final String INVALID_FONT_NAME = "Invalid font name specified in fonts.json from texturepack/fonts: %s. See the example tp for valid font names and more instructions.";
		private static final String CANT_FIND_FONT = "Can't find font file specified in fonts.json from texturepack/fonts: %s. Are you sure it exists?";

		public static Map<TextType, TextTypeReference> loadFontsFromPath(String path) throws BadPackException {
			return PackManager.loadMapFromJson(path + "/text.json", (Map<TextType, TextTypeReference> map, JsonObject font) -> {
				String name = font.get("font").asString();
				TextType f = TextType.getByName(name);

				if (f == null) {
					return new BadPackException(String.format(INVALID_FONT_NAME, name));
				}

				String file = font.get("file").asString();
				float size = font.get("size").asFloat();
				String colorHex = font.get("color").asString();

				Color color = Color.web(colorHex);
				try (InputStream fontStream = Loader.class.getResourceAsStream(path + "/fonts/" + file)) {
					Font realFont = Font.loadFont(fontStream, size);
					map.put(f, new TextTypeReference(realFont, color));
				} catch (IOException ex) {
					return new BadPackException(String.format(CANT_FIND_FONT, file), ex);
				}
				return null; // success
				});
		}
	}
}
package xyz.luan.games.hangman.texture;

import java.util.Map;

import xyz.luan.games.hangman.texture.TexturePack.BadPackException;

import com.eclipsesource.json.JsonObject;

public final class ColorSchema {

	private static final String[] REQUIRED_COLORS = { "main.title", "main.menu.selected", "main.menu.default" };

	private ColorSchema() {
		throw new RuntimeException("Cannot be instanciated!");
	}

	public static Map<String, java.awt.Color> loadColorsFromPath(String path) throws BadPackException {
		Map<String, java.awt.Color> result = PackManager.loadMapFromJson(path + "/color.schema.json", (Map<String, java.awt.Color> map, JsonObject obj) -> {
			String name = obj.get("name").asString();
			java.awt.Color color = new java.awt.Color(Integer.parseInt(obj.get("color").asString(), 16));

			map.put(name, color);
			return null; // success
			});

		for (String req : REQUIRED_COLORS) {
			if (result.get(req) == null) {
				throw new BadPackException("Required color " + req + " not found in color.schema.json on the pack.");
			}
		}

		return result;
	}
}
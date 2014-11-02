package xyz.luan.games.hangman.texture;

import java.io.IOException;
import java.io.InputStream;
import java.util.Map;

import xyz.luan.games.hangman.texture.TexturePack.BadPackException;

import com.eclipsesource.json.JsonObject;

public enum Font {
    GAME_TITLE("GAME_TITLE"), MENU("MENU"), LETTERS("LETTERS"), MANA_COST("MANA_COST"), TITLE("TITLE"), TEXT("TEXT"), TEXT_SMALL(
            "TEXT_SMALL");

    private String name;

    private Font(String name) {
        this.name = name;
    }

    public static Font getByName(String name) {
        for (Font f : values()) {
            if (f.name.equals(name)) {
                return f;
            }
        }
        return null;
    }

    static class Loader {

        public static Map<Font, java.awt.Font> loadFontsFromPath(String path) throws BadPackException {
            return PackManager.loadMapFromJson(path + "/fonts.json", (Map<Font, java.awt.Font> map, JsonObject font) -> {
                String name = font.get("font").asString();
                Font f = Font.getByName(name);

                if (f == null) {
                    return new BadPackException("Invalid font name specified in fonts.json from texturepack/fonts: " + name
                            + ". See the example tp for valid font names and more instructions.");
                }

                String file = font.get("file").asString();
                float size = font.get("size").asFloat();

                try (InputStream fontStream = Loader.class.getResourceAsStream(path + "/" + file)) {
                    java.awt.Font realFont = java.awt.Font.createFont(java.awt.Font.TRUETYPE_FONT, fontStream).deriveFont(size);
                    map.put(f, realFont);
                } catch (IOException ex) {
                    return new BadPackException("Can't find font file specified in fonts.json from texturepack/fonts: " + file
                            + ". Are you sure it exists?", ex);
                } catch (java.awt.FontFormatException ex) {
                    return new BadPackException("The font file texturepack/fonts/" + file + " is not a valid font file.", ex);
                }

                return null; // success
                });
        }
    }
}
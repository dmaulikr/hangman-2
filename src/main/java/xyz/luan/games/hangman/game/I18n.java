package xyz.luan.games.hangman.game;

import java.io.File;
import java.io.IOException;
import java.io.InputStream;
import java.io.InputStreamReader;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Locale;
import java.util.Map;
import java.util.stream.Collectors;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.luan.games.hangman.texture.PackManager;

import com.eclipsesource.json.JsonObject;

public final class I18n {

    private static final Logger logger = LoggerFactory.getLogger(I18n.class);

    private static final String BASE_PATH = "/xyz/luan/games/hangman/i18n/";

    private I18n() {
        throw new RuntimeException("Cannot be instanciated!");
    }

    private static Map<String, String> keyValues;

    public static List<String> validLocales() {
        File dir = new File(PackManager.class.getResource(BASE_PATH).getFile());
        return Arrays.stream(dir.listFiles()).filter(f -> f.getAbsolutePath().endsWith(".json"))
                .map(f -> f.getName().replaceFirst("[.][^.]+$", "")).collect(Collectors.toList());
    }

    public static boolean load(Locale locale) {
        String lang = locale.toLanguageTag();
        keyValues = new HashMap<>();
        String jsonPath = BASE_PATH + lang + ".json";
        try (InputStream jsonStream = PackManager.class.getResourceAsStream(jsonPath)) {
            if (jsonStream == null) {
                logger.error("Error trying to load i18n file {}. File doesn't exist.", lang);
                return false;
            }
            JsonObject pairs = JsonObject.readFrom(new InputStreamReader(jsonStream));
            for (String key : pairs.names()) {
                keyValues.put(key, pairs.get(key).asString());
            }
            return true;
        } catch (IOException ex) {
            logger.error("Error trying to load i18n file {}. Improper json file.", lang);
            return false;
        }
    }

    public static String t(String key) {
        String i18n = keyValues.get(key);
        if (i18n == null) {
            logger.error("Missing i18n: {}", key);
            return "Missing i18n: " + key;
        }
        return i18n;
    }

}
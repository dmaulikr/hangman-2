package game;

import java.util.*;
import java.io.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import com.eclipsesource.json.JsonObject;

import texture.PackManager;

public final class I18n {

  private static final Logger logger = LoggerFactory.getLogger(I18n.class);

  private I18n() {
    throw new RuntimeException("Cannot be instanciated!");
  }

  private static Map<String, String> keyValues;

  public static boolean load(Locale locale) {
    String lang = locale.toLanguageTag();
    keyValues = new HashMap<>();
    String jsonPath = "/i18n/" + lang + ".json" ;
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
package texture;

import java.util.*;
import java.io.*;
import java.util.function.BiFunction;
import com.eclipsesource.json.*;

import texture.TexturePack.BadPackException;

public final class PackManager {

  private PackManager() {
    throw new RuntimeException("Cannot be instanciated!");
  }

  private static TexturePack currentPack;

  public static void load() {
    try {
      currentPack = new TexturePack(game.ConfigManager.client.config().getPackName());
    } catch (BadPackException ex) {
      System.err.println("Invalid texture pack!");
      ex.printStackTrace();
      System.exit(1);
    }
  }

  //TODO list method

  public static TexturePack pack() {
    return currentPack;
  }

  public static <K, V> Map<K, V> loadMapFromJson(String jsonPath, BiFunction<Map<K, V>, JsonObject, BadPackException> processAndPut) throws BadPackException {
      Map<K, V> map = new HashMap<>();
      try (InputStream jsonStream = PackManager.class.getResourceAsStream(jsonPath)) {
        if (jsonStream == null) {
          throw new BadPackException(jsonPath + " file not found in your texturepack folder.");
        }
        JsonArray elements = JsonArray.readFrom(new InputStreamReader(jsonStream));
        for (JsonValue el : elements) {
          JsonObject obj = el.asObject();
          BadPackException ex = processAndPut.apply(map, obj);
          if (ex != null)
            throw ex;
        }
      } catch (IOException ex) {
        throw new BadPackException("Error loading " + jsonPath + " file from texturepack. Are you sure your texture pack is ok?", ex);
      }
      return map;
  }
}
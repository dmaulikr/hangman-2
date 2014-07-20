package game;

import java.io.*;
import java.util.*;
import java.lang.reflect.*;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import client.ClientConfig;
import server.ServerConfig;

public final class ConfigManager {

  private static final Logger logger = LoggerFactory.getLogger(ConfigManager.class);

  private ConfigManager() {
    throw new RuntimeException("Cannot be instanciated!");
  }

  public static Manager<GeneralConfig> general;
  public static Manager<ClientConfig> client;
  public static Manager<ServerConfig> server;

  public static void load(int mode) {
    general = new Manager<GeneralConfig>("general.dat", GeneralConfig.class);
    logger.info("Locale set to {}.", general.config().getLocale());
    I18n.load(general.config().getLocale());

    if (mode != 1) {
      server = new Manager<ServerConfig>("server.dat", ServerConfig.class);
    }
    if (mode != 2) {
      client = new Manager<ClientConfig>("client.dat", ClientConfig.class);
      texture.PackManager.load();
    }
  }

  @SuppressWarnings("unchecked")
  public static class Manager<T> {

    private T config = null;

    public Class<T> getPersistentClass() {
      return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
    }

    public Manager(String filename, Class<T> clazz) {
      File file = new File(filename);
      if (file.exists()) {
        try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
          config = (T) stream.readObject();
        } catch (ClassNotFoundException | IOException ex) {
          System.err.println("Invalid config file found at " + filename + ". You can fix it or delete it to restore defaults.");
          System.exit(1);
        }
      } else {
        try {
          config = clazz.newInstance();
        } catch (IllegalAccessException | InstantiationException ex) {}
      }
    }

    public T config() {
      return config;
    }
  }
}
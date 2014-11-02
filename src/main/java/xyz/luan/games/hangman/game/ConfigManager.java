package xyz.luan.games.hangman.game;

import java.io.File;
import java.io.FileInputStream;
import java.io.FileOutputStream;
import java.io.IOException;
import java.io.ObjectInputStream;
import java.io.ObjectOutputStream;
import java.lang.reflect.ParameterizedType;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import xyz.luan.games.hangman.client.ClientConfig;
import xyz.luan.games.hangman.server.ServerConfig;

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
        I18n.load(general.config().getLocale());

        if (mode != 1) {
            server = new Manager<ServerConfig>("server.dat", ServerConfig.class);
        }
        if (mode != 2) {
            client = new Manager<ClientConfig>("client.dat", ClientConfig.class);
            xyz.luan.games.hangman.texture.PackManager.load();
        }
    }

    @SuppressWarnings("unchecked")
    public static class Manager<T> {

        private String filename;
        private T config = null;

        public Class<T> getPersistentClass() {
            return (Class<T>) ((ParameterizedType) getClass().getGenericSuperclass()).getActualTypeArguments()[0];
        }

        public Manager(String filename, Class<T> clazz) {
            this.filename = filename;
            File file = getFile();
            if (file.exists()) {
                try (ObjectInputStream stream = new ObjectInputStream(new FileInputStream(file))) {
                    config = (T) stream.readObject();
                } catch (ClassNotFoundException | IOException ex) {
                    logger.error("Invalid config file found at " + filename + ". You can fix it or delete it to restore defaults.");
                    System.exit(1);
                }
            } else {
                try {
                    config = clazz.newInstance();
                } catch (IllegalAccessException | InstantiationException ex) {
                    throw new RuntimeException("Config class must have a public default constructor", ex);
                }
            }
        }

        private File getFile() {
            return new File(this.filename);
        }

        public void save() {
            try (ObjectOutputStream stream = new ObjectOutputStream(new FileOutputStream(getFile()))) {
                stream.writeObject(config);
            } catch (IOException ex) {
                logger.error("Error while saving config file at " + filename + ". Any changes made will only be applyable in this execution. Try saving again later.", ex);
            }
        }
        
        public T config() {
            return config;
        }
    }
}
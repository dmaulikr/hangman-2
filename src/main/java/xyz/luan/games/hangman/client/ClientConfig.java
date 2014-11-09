package xyz.luan.games.hangman.client;

import java.io.Serializable;
import java.lang.reflect.Field;

import xyz.luan.games.hangman.drawing.ScreenOptions;
import xyz.luan.games.hangman.game.forms.InvalidFormException;

public class ClientConfig implements Serializable {

    private static final long serialVersionUID = 3538321516189351015L;

    private String texturePack;
    private ScreenOptions screenOptions;

    public ClientConfig() {
        this.texturePack = "default";
        this.screenOptions = new ScreenOptions();
    }

    public String getPackName() {
        return this.texturePack;
    }

    public String get(String field) {
        try {
            Field fieldRef = this.getClass().getDeclaredField(field);
            fieldRef.setAccessible(true);
            Object value = fieldRef.get(this);
            return String.valueOf(value);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            throw new RuntimeException("Invalid field access on ServerConfig: " + field, ex);
        }
    }

    public void set(String field, String value) throws InvalidFormException {
        switch (field) {
        default:
            throw new RuntimeException("Invalid field access on ServerConfig: " + field);
        }
    }
}
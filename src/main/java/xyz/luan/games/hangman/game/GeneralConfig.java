package xyz.luan.games.hangman.game;

import java.lang.reflect.Field;
import java.util.Locale;

import xyz.luan.games.hangman.game.forms.InvalidFormException;
import static xyz.luan.games.hangman.game.forms.FormValidations.*;

public class GeneralConfig {

    private int width, height;
    private Locale locale;

    public GeneralConfig() {
        this.width = 800;
        this.height = 600;
        this.locale = Locale.forLanguageTag("en-Us");
    }

    public Locale getLocale() {
        return this.locale;
    }

    public int getScreenWidth() {
        return this.width;
    }

    public int getScreenHeight() {
        return this.height;
    }

    public String get(String field) {
        try {
            Field fieldRef = this.getClass().getDeclaredField(field);
            fieldRef.setAccessible(true);
            Object value = fieldRef.get(this);
            if (value.getClass().equals(Locale.class)) {
                return Locale.class.cast(value).toLanguageTag();
            }
            return String.valueOf(value);
        } catch (NoSuchFieldException | IllegalAccessException ex) {
            throw new RuntimeException("Invalid field access on GeneralConfig: " + field, ex);
        }
    }

    public void set(String field, String value) throws InvalidFormException {
        switch (field) {
        case "width":
            this.width = toNatural(value);
            break;
        case "height":
            this.height = toNatural(value);
            break;
        case "locale":
            this.locale = Locale.forLanguageTag(value);
            break;
        default:
            throw new RuntimeException("Invalid field access on GeneralConfig: " + field);
        }
    }

}
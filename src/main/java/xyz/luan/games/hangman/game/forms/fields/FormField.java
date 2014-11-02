package xyz.luan.games.hangman.game.forms.fields;

import javafx.scene.Node;

public interface FormField {

    public abstract String getFormValue();
    public abstract Node getAsNode();
    public abstract void setFormValue(String value);
}

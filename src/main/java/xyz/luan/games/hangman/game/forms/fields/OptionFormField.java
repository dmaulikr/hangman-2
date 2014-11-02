package xyz.luan.games.hangman.game.forms.fields;

import java.util.List;

import javafx.collections.FXCollections;
import javafx.scene.Node;
import javafx.scene.control.ComboBox;

public class OptionFormField extends ComboBox<String> implements FormField {

    public OptionFormField(List<String> values) {
        super(FXCollections.observableArrayList(values));
    }

    @Override
    public void setFormValue(String value) {
        this.getSelectionModel().select(value);
    }

    @Override
    public String getFormValue() {
        return this.getSelectionModel().getSelectedItem();
    }

    @Override
    public Node getAsNode() {
        return this;
    }

}

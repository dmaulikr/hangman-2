package xyz.luan.games.hangman.game.forms.fields;

import xyz.luan.games.hangman.texture.FxHelper;
import javafx.scene.Node;
import javafx.scene.control.TextField;

public class TextFormField extends TextField implements FormField {

	public TextFormField() {
		FxHelper.setupTextField(this);
	}
	
    @Override
    public String getFormValue() {
        return this.getText();
    }

    @Override
    public Node getAsNode() {
        return this;
    }

    @Override
    public void setFormValue(String value) {
        this.setText(value);
    }
}

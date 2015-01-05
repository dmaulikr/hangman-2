package xyz.luan.games.hangman.game.scenes;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.MainGameStatus;
import xyz.luan.games.hangman.game.forms.FormComponent;
import xyz.luan.games.hangman.game.forms.FormUtils;
import xyz.luan.games.hangman.game.forms.InvalidFormException;
import xyz.luan.games.hangman.game.forms.fields.FormField;
import xyz.luan.games.hangman.texture.FxHelper;
import xyz.luan.games.hangman.texture.TextType;

public abstract class DefaultForm extends DefaultScene {
	private FormField[] fields;
	private Label errors;

	@Override
	protected Pane generatePane() {
		GridPane grid = FormUtils.defaultGrid();

		grid.add(FxHelper.createLabel(TextType.TITLE, title()), 0, 0, 2, 1);

		this.errors = FxHelper.createRawLabel(TextType.TEXT, "");
		grid.add(this.errors, 0, 1, 2, 1);

		FormComponent[] components = getComponents();
		fields = new FormField[components.length];
		for (int i = 0; i < fields.length; i++) {
			grid.add(FxHelper.createRawLabel(TextType.FORM_LABEL, getFieldName(i)), 0, i + 2);

			fields[i] = components[i].generate();
			fields[i].setFormValue(get(components[i].getName()));
			grid.add(fields[i].getAsNode(), 1, i + 2);
		}

		grid.add(cancelButton(), 0, components.length + 2);
		grid.add(stateChangeButtonWithEvent("common.save", event -> {
			this.errors.setText("");
			boolean allOk = true;
			for (int i = 0; i < fields.length; i++) {
				try {
					set(components[i].getName(), fields[i].getFormValue());
				} catch (InvalidFormException ex) {
					allOk = false;
					String message = ex.getErrorMessage(getFieldName(i));
					this.errors.setText(this.errors.getText() + "\n" + message);
				}
			}
			if (allOk) {
				onOkHook();
			}
		}), 1, getComponents().length + 2);

		return grid;
	}

	protected StateChangeButton cancelButton() {
		return new StateChangeButton("common.cancel", MainGameStatus.MAIN_MENU);
	}

	protected abstract String title();

	protected abstract void onOkHook();

	protected abstract FormComponent[] getComponents();

	protected abstract String getFieldName(int i);

	protected abstract String get(String name);

	protected abstract void set(String name, String value) throws InvalidFormException;
}

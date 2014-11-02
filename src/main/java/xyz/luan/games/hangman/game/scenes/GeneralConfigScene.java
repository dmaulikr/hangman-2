package xyz.luan.games.hangman.game.scenes;

import javafx.scene.control.Label;
import javafx.scene.layout.GridPane;
import javafx.scene.layout.Pane;
import javafx.scene.text.Text;
import xyz.luan.games.hangman.game.ConfigManager;
import xyz.luan.games.hangman.game.GameStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.forms.FormComponent;
import xyz.luan.games.hangman.game.forms.FormUtils;
import xyz.luan.games.hangman.game.forms.InvalidFormException;
import xyz.luan.games.hangman.game.forms.fields.FormField;
import xyz.luan.games.hangman.game.forms.fields.OptionFormField;

public class GeneralConfigScene extends DefaultScene {

    private static final FormComponent[] FIELDS = {
        new FormComponent("width"),
        new FormComponent("height"),
        new FormComponent("locale", () -> new OptionFormField(I18n.validLocales()))
    };

    private FormField[] fields;

    private static String getFieldName(int field) {
        return I18n.t("options." + FIELDS[field].getName());
    }

    @Override
    protected Pane generatePane() {
        GridPane grid = FormUtils.defaultGrid();

        Text sceneTitle = new Text(I18n.t("main.menu.options"));
        sceneTitle.getStyleClass().add("title");
        grid.add(sceneTitle, 0, 0, 2, 1);

        fields = new FormField[FIELDS.length];
        for (int i = 0; i < fields.length; i++) {
            grid.add(new Label(getFieldName(i)), 0, i + 1);

            fields[i] = FIELDS[i].generate();
            fields[i].setFormValue(ConfigManager.general.config().get(FIELDS[i].getName()));
            grid.add(fields[i].getAsNode(), 1, i + 1);
        }

        grid.add(new StateChangeButton("common.cancel", GameStatus.MAIN, mainRef), 0, FIELDS.length + 1);
        grid.add(new StateChangeButton("common.save", event -> {
            boolean ok = true;
            for (int i = 0; i < fields.length; i++) {
                try {
                    ConfigManager.general.config().set(FIELDS[i].getName(), fields[i].getFormValue());
                } catch (InvalidFormException ex) {
                    ok = false;
                    /* TODO put on screen */
                    System.out.println(ex.getErrorMessage(getFieldName(i)));
                }
            }
            if (ok) {
                ConfigManager.general.save();
                mainRef.setStatus(GameStatus.MAIN);
            }
        }), 1, FIELDS.length + 1);

        return grid;
    }
}
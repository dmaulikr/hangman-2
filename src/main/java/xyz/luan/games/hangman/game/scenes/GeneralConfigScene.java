package xyz.luan.games.hangman.game.scenes;

import xyz.luan.games.hangman.game.ConfigManager;
import xyz.luan.games.hangman.game.MainGameStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.forms.FormComponent;
import xyz.luan.games.hangman.game.forms.InvalidFormException;
import xyz.luan.games.hangman.game.forms.fields.OptionFormField;

public class GeneralConfigScene extends DefaultForm {

    private static final FormComponent[] FIELDS = {
        new FormComponent("width"),
        new FormComponent("height"),
        new FormComponent("port"),
        new FormComponent("locale", () -> new OptionFormField(I18n.validLocales()))
    };

    @Override
    protected String getFieldName(int field) {
        return I18n.t("options." + FIELDS[field].getName());
    }

    @Override
    protected void onOkHook() {
        ConfigManager.general.save();
        mainRef.setStatus(MainGameStatus.MAIN_MENU);
    }

    @Override
    protected FormComponent[] getComponents() {
        return FIELDS;
    }

    @Override
    protected void set(String name, String value) throws InvalidFormException {
        ConfigManager.general.config().set(name, value);
    }

    @Override
    protected String get(String name) {
        return ConfigManager.general.config().get(name);
    }

    @Override
    protected String title() {
        return "main.menu.options";
    }
}
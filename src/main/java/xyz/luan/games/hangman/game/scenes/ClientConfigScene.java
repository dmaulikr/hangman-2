package xyz.luan.games.hangman.game.scenes;

import xyz.luan.games.hangman.game.ConfigManager;
import xyz.luan.games.hangman.game.MainGameStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.forms.FormComponent;
import xyz.luan.games.hangman.game.forms.InvalidFormException;

public class ClientConfigScene extends DefaultForm {

    private static final FormComponent[] FIELDS = {};

    @Override
    protected StateChangeButton cancelButton() {
        return new StateChangeButton("common.cancel", MainGameStatus.CONNECT_TO_SERVER);
    }

    @Override
    protected String getFieldName(int field) {
        return I18n.t("client.configs." + FIELDS[field].getName());
    }

    @Override
    protected void onOkHook() {
        ConfigManager.client.save();
        mainRef.setStatus(MainGameStatus.CONNECT_TO_SERVER);
    }

    @Override
    protected FormComponent[] getComponents() {
        return FIELDS;
    }

    @Override
    protected void set(String name, String value) throws InvalidFormException {
        ConfigManager.client.config().set(name, value);
    }

    @Override
    protected String get(String name) {
        return ConfigManager.client.config().get(name);
    }

    @Override
    protected String title() {
        return "client.config";
    }
}

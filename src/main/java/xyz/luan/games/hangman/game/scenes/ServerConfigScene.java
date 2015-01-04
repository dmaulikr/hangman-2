package xyz.luan.games.hangman.game.scenes;

import xyz.luan.games.hangman.game.ConfigManager;
import xyz.luan.games.hangman.game.MainGameStatus;
import xyz.luan.games.hangman.game.I18n;
import xyz.luan.games.hangman.game.forms.FormComponent;
import xyz.luan.games.hangman.game.forms.InvalidFormException;

public class ServerConfigScene extends DefaultForm {

	private static final FormComponent[] FIELDS = { new FormComponent("maxSelectedSpells") };

	@Override
	protected String title() {
		return "host.config";
	}

	@Override
	protected void onOkHook() {
		ConfigManager.server.save();
		mainRef.setStatus(MainGameStatus.HOSTING_SERVER);
	}

	@Override
	protected FormComponent[] getComponents() {
		return FIELDS;
	}

	@Override
	protected String getFieldName(int i) {
		return I18n.t("host.options." + FIELDS[i].getName());
	}

	@Override
	protected String get(String name) {
		return ConfigManager.server.config().get(name);
	}

	@Override
	protected void set(String name, String value) throws InvalidFormException {
		ConfigManager.server.config().set(name, value);
	}

}

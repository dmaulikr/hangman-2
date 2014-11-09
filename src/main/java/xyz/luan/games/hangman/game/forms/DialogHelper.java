package xyz.luan.games.hangman.game.forms;

import org.controlsfx.dialog.Dialogs;

@SuppressWarnings("deprecation")
public final class DialogHelper {

    private DialogHelper() {
        throw new RuntimeException("Should not be instanciated.");
    }

    /*
     * TODO : replace with new native api on new JavaFX version when released
     */
    public static void show(String title, String message) {
        Dialogs.create().title(title).message(message).showInformation();
    }
}

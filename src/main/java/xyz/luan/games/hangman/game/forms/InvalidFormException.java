package xyz.luan.games.hangman.game.forms;

public class InvalidFormException extends Exception {
    private static final long serialVersionUID = 3532597322458976485L;

    public InvalidFormException(String error) {
        super(error);
    }

    public String getErrorMessage(String field) {
        return getMessage().replace("%{field}", field);
    }
}
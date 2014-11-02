package xyz.luan.games.hangman.game.forms;

public final class FormValidations {

    private FormValidations() {
        throw new RuntimeException("Should not be instanciated.");
    }

    public static int toNatural(String value) throws InvalidFormException {
        final String errorStart = "The field %{field} needs to be a positive integer; value ";
        try {
            int answ = Integer.parseInt(value);
            if (answ < 0) {
                throw new InvalidFormException(errorStart + value + " is negative.");
            }
            if (answ == 0) {
                throw new InvalidFormException(errorStart + value + " is 0.");
            }
            return answ;
        } catch (NumberFormatException ex) {
            throw new InvalidFormException(errorStart + value + " is not an integer.");
        }
    }
}
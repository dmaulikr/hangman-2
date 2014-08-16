package game.forms;

public class InvalidFormException extends Exception {
  public InvalidFormException(String error) {
    super(error);
  }

  public String getErrorMessage(String field) {
    return getMessage().replace("%{field}", field);
  }
}
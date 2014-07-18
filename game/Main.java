package game;

import javax.swing.*;

public class Main extends JFrame {

  public static void main(String[] args) {
    new Main();
  }

  public Main() {
    super("the Hangman");

    this.setSize(600, 800);
    this.setDefaultCloseOperation(EXIT_ON_CLOSE);
    this.setVisible(true);
  }
}
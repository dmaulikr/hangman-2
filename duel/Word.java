package duel;

import java.net.*;
import java.io.*;
import java.awt.*;

public class Word {

  private Letter[] letters;

  public static String randomWord(int method) throws IOException {
    if (method == 1) {
      final String[] WORDS = {"", "", ""};
      return WORDS[(int) (Math.random() * WORDS.length)];
    } else if (method == 2) {
      final URL url = new URL("http://randomword.setgetgo.com/get.php");
      URLConnection conn = url.openConnection();
      try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
        return in.readLine();
      }
    } else {
      return null;
    }
  }

  public Word(String word) {
    this.letters = new Letter[word.length()];
    for (int i = 0; i < this.letters.length; i++) {
      this.letters[i] = Letter.forWord(word.charAt(i));
    }
  }

  public void reveal() {
    for (int i = 0; i < letters.length; i++) {
      letters[i].setStatus(Letter.Status.REVEALED);
    }
  }

  public int tryLetter(char c, Player p) {
    boolean any = false, every = true;

    for (int i = 0; i < letters.length; i++) {
      if (!letters[i].revealed()) {
        if (letters[i].is(c)) {
          letters[i].setStatus(Letter.Status.REVEALED);
          letters[i].setOwner(p);
          any = true;
        } else {
          every = false;
        }
      }
    }

    return every ? 1 : (any ? 0 : -1);
  }

  public void render(Graphics2D g, int x, int y) {
    int ny = y;
    for (int i = 0; i < letters.length; i++) {
      letters[i].render(g, x, ny);
      ny += Letter.SIZE;
    }
  }
}
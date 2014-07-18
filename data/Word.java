package data;

import java.net.*;
import java.io.*;

public class Word {

  public static final char UNKNOWN_CHAR = '?';
  
  private String word;
  private boolean[] revealed;

  public static Word randomWord(int method) throws IOException {
    String str;
    if (method == 1) {
      final String[] WORDS = {"", "", ""};
      str = WORDS[(int) (Math.random() * WORDS.length)];
    } else if (method == 2) {
      final URL url = new URL("http://randomword.setgetgo.com/get.php");
      URLConnection conn = url.openConnection();
      try (BufferedReader in = new BufferedReader(new InputStreamReader(conn.getInputStream()))) {
        str = in.readLine();
      }
    } else {
      return null;
    }

    return new Word(str);
  }

  public Word(String word) {
    this.word = word;
    revealed = new boolean[word.length()];
  }

  public void reveal() {
    for (int i = 0; i < revealed.length; i++) {
      revealed[i] = true;
    }
  }

  public int tryLetter(char c) {
    boolean any = false, every = true;

    for (int i = 0; i < revealed.length; i++) {
      if (!revealed[i]) {
        if (word.charAt(i) == c) {
          revealed[i] = true;
          any = true;
        } else {
          every = false;
        }
      }
    }

    return every ? 1 : (any ? 0 : -1);
  }

  public String writeOut() {
    String res = "";
    for (int i = 0; i < revealed.length; i++) {
      res += (revealed[i] ? word.charAt(i) : UNKNOWN_CHAR);
    }
    return res;
  }
}
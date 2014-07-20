package texture;

import java.util.*;
import java.awt.Graphics2D;

public class TexturePack {
  
  private static final String BASE_PATH = "/res/";

  private String name;

  private Map<Font, java.awt.Font> fontsMapping;
  private Map<String, java.awt.Color> colorSchema;

  public TexturePack(String name) throws BadPackException {
    this.name = name;

    this.fontsMapping = Font.Loader.loadFontsFromPath(BASE_PATH + name + "/fonts");
    this.colorSchema = ColorSchema.loadColorsFromPath(BASE_PATH + name);
  }

  public void selectColor(Graphics2D g, String name) {
    g.setColor(colorSchema.get(name));
  }

  public void selectFont(Graphics2D g, Font f) {
    g.setFont(fontsMapping.get(f));
  }

  public void drawString(Graphics2D g, String text, int x, int y) {
    drawString(g, text, x, y, Aligment.START, Aligment.START);
  }

  public void drawString(Graphics2D g, String text, int x, int y, Aligment horizontal, Aligment vertical) {
    java.awt.FontMetrics fm = g.getFontMetrics();
    int hp = horizontal.getHorizontalMultiplier(), vp = vertical.getVerticalMultiplier();
    g.drawString(text, x + (hp)*fm.stringWidth(text)/2, y + (vp)*fm.getHeight()/2);
  }

  public enum Aligment {
    START(0, 1), MIDDLE(-1, 0), END(-2, -1);

    private int hm, vm;

    private Aligment(int hm, int vm) {
      this.hm = hm;
      this.vm = vm;
    }

    public int getHorizontalMultiplier() {
      return this.hm;
    }

    public int getVerticalMultiplier() {
      return this.vm;
    }
  }

  public static class BadPackException extends Exception {

    public BadPackException(String message) {
      super(message);
    }
    
    public BadPackException(String message, Throwable cause) {
      super(message, cause);
    }
  }
}
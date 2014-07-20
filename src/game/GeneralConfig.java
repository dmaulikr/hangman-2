package game;

import java.util.Locale;

public class GeneralConfig {
  
  private int screenWidth, screenHeight;
  private Locale locale;

  public GeneralConfig() {
    this.screenWidth = 800;
    this.screenHeight = 600;
    this.locale = Locale.forLanguageTag("en-Us");
  }

  public Locale getLocale() {
    return this.locale;
  }

  public int getScreenWidth() {
    return this.screenWidth;
  }

  public int getScreenHeight() {
    return this.screenHeight;
  }
}
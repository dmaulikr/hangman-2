package client;

import java.io.Serializable;

import drawing.ScreenOptions;

public class ClientConfig implements Serializable {
  
  private String texturePack;
  private ScreenOptions screenOptions;

  public ClientConfig() {
    this.texturePack = "default";
    this.screenOptions = new ScreenOptions();
  }

  public String getPackName() {
    return this.texturePack;
  }
}
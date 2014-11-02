package xyz.luan.games.hangman.client;

import java.io.Serializable;

import xyz.luan.games.hangman.drawing.ScreenOptions;

public class ClientConfig implements Serializable {

    private static final long serialVersionUID = 3538321516189351015L;

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
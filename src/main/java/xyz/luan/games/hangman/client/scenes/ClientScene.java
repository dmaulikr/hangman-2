package xyz.luan.games.hangman.client.scenes;

import xyz.luan.games.hangman.client.Client;
import xyz.luan.games.hangman.client.FormScreenInterface;
import xyz.luan.games.hangman.game.scenes.DefaultScene;

public abstract class ClientScene extends DefaultScene {

    protected Client client;

    public void setClient(Client client) {
        this.client = client;
        this.client.bindInterface(createBinding());
    }

    public abstract FormScreenInterface createBinding();
}

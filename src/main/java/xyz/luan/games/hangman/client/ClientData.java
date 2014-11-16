package xyz.luan.games.hangman.client;

import lombok.Data;
import xyz.luan.games.hangman.game.Profile;

@Data
public class ClientData {

    private Profile me;
    private ClientStatus mode;

}

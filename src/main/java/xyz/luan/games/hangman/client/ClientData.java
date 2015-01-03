package xyz.luan.games.hangman.client;

import java.io.Serializable;

import lombok.Data;
import xyz.luan.games.hangman.game.Main;
import xyz.luan.games.hangman.game.Profile;

@Data
public class ClientData {

	private Profile me;

}

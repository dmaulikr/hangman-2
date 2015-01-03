package xyz.luan.games.hangman.client;

import java.io.Serializable;

import lombok.Data;
import xyz.luan.games.hangman.game.Profile;

@Data
public class ClientData implements Serializable {

	private Profile me;
	private ClientStatus mode;

}

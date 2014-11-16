package xyz.luan.games.hangman.messaging.server;

import lombok.AllArgsConstructor;
import xyz.luan.games.hangman.client.ClientData;
import xyz.luan.games.hangman.client.ClientStatus;
import xyz.luan.games.hangman.game.Profile;

@AllArgsConstructor
public class LoginResponse implements ServerMessage {

    private Status status;
    private Profile profile;

    public LoginResponse(Status status) {
        this(status, null);
        assert status != Status.OK;
    }

    public enum Status {
        USERNAME_NOT_FOUND {
            @Override
            public String toString() {
                return "client.login.errors.usernameNotFound";
            }
        },
        WRONG_PASSWORD {
            @Override
            public String toString() {
                return "client.login.errors.wrongPassword";
            }
        },
        OK;
    }

    @Override
    public void handle(ClientData data) {
        if (status == Status.OK) {
            data.setMode(ClientStatus.LOBBY);
            data.setMe(profile);
        } else {
            //((LoginScreen) data.getScreen()).setErrors(status.toString());
        }
    }
}

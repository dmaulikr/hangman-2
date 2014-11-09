package xyz.luan.games.hangman.messaging;

import java.io.Serializable;

public class Message implements Serializable {

    private static final long serialVersionUID = 236106794112821849L;

    private String text;

    public Message(String text) {
        this.text = text;
    }

    public String getText() {
        return text;
    }

    public void setText(String text) {
        this.text = text;
    }
}

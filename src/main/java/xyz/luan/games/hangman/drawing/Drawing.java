package xyz.luan.games.hangman.drawing;

import java.awt.Graphics2D;
import java.io.Serializable;

public abstract class Drawing implements Serializable {

    private static final long serialVersionUID = -8199839147043234105L;

    private PlayerColor ownerColor;

    public Drawing(PlayerColor ownerColor) {
        this.ownerColor = ownerColor;
    }

    public abstract void render(Graphics2D g, int x, int y, int tileSize);
}
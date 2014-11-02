package xyz.luan.games.hangman.drawing;

import java.awt.Graphics2D;

import xyz.luan.games.hangman.duel.Letter;

public class LetterDrawing extends Drawing {

    private static final long serialVersionUID = -4772770571087389314L;

    private Character c;
    private Letter.Status status;

    public LetterDrawing(PlayerColor ownerColor, Character c, Letter.Status status) {
        super(ownerColor);
        this.c = c;
        this.status = status;
    }

    public void render(Graphics2D g, int x, int y, int tileSize) {
        // TODO
    }
}
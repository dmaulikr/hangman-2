package xyz.luan.games.hangman.drawing;

import java.awt.Graphics2D;

import xyz.luan.games.hangman.duel.Letter;
import xyz.luan.games.hangman.spells.Spell;

public class SpellDrawing extends Drawing {

    private static final long serialVersionUID = -30519711883853656L;

    private Spell spell;
    private Letter.Status status;

    public SpellDrawing(PlayerColor color, Spell spell, Letter.Status status) {
        super(color);
        this.spell = spell;
        this.status = status;
    }

    public void render(Graphics2D g, int x, int y, int tileSize) {
        // TODO
    }

}
package xyz.luan.games.hangman.drawing;

import java.awt.Graphics2D;
import java.io.Serializable;
import java.util.Arrays;
import java.util.List;

import xyz.luan.games.hangman.duel.Player;

public class PlayerPanel implements Serializable {

    private static final long serialVersionUID = 7676815178411917140L;

    private List<Drawing> keyboard;
    private List<Drawing> spells;
    private PlayerChartDrawing chart;

    public PlayerPanel(Player owner, Player viewer) {
        this.keyboard = Arrays.asList(owner.getDrawingsForKeyboard());
        this.spells = Arrays.asList(owner.getDrawingsForSpells(viewer));
        this.chart = new PlayerChartDrawing(owner);
    }

    public void render(Graphics2D g, int x, int y, int tileSize) {
        // TODO
    }
}
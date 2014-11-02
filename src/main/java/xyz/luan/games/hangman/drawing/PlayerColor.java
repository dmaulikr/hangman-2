package xyz.luan.games.hangman.drawing;

import java.awt.Graphics2D;

public enum PlayerColor {
    RED("CC3D4E"), PURPLE("B133D4"), MAGENTA("F005F0"), BLUE("202CD6"), CYAN("22D0F2"), GREEN("08C233"), YELLOW("C2BC08"), ORANGE("E8791E");

    private java.awt.Color color;

    private PlayerColor(String rgb) {
        this.color = new java.awt.Color(Integer.parseInt(rgb, 16));
    }

    public void select(Graphics2D g) {
        g.setColor(this.color);
    }
}
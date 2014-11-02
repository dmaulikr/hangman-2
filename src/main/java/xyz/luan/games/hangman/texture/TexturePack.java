package xyz.luan.games.hangman.texture;

import java.awt.Graphics2D;
import java.util.Map;

public class TexturePack {

    private Map<Font, java.awt.Font> fontsMapping;
    private Map<String, java.awt.Color> colorSchema;

    public TexturePack(String name) throws BadPackException {
        this.fontsMapping = Font.Loader.loadFontsFromPath("/xyz/luan/games/hangman/textures/" + name + "/fonts");
        this.colorSchema = ColorSchema.loadColorsFromPath("/xyz/luan/games/hangman/textures/" + name);
    }

    public void selectColor(Graphics2D g, String name) {
        g.setColor(colorSchema.get(name));
    }

    public void selectFont(Graphics2D g, Font f) {
        g.setFont(fontsMapping.get(f));
    }

    public java.awt.Rectangle drawString(Graphics2D g, String text, int x, int y) {
        return drawString(g, text, x, y, Aligment.START, Aligment.START);
    }

    public java.awt.Rectangle drawString(Graphics2D g, String text, int x, int y, Aligment horizontal, Aligment vertical) {
        int hm = horizontal.getMultiplier(), vm = vertical.getMultiplier();

        int width, height;
        {
            java.awt.FontMetrics fm = g.getFontMetrics();
            width = fm.stringWidth(text);
            height = fm.getHeight();
        }

        int startX = x + hm * width / 2;
        int startY = y + vm * height / 2;
        int middleY = startY + height / 2;
        g.drawString(text, startX, middleY);

        return new java.awt.Rectangle(startX, startY - height / 2, width, height);
    }

    public enum Aligment {
        START(0), MIDDLE(-1), END(-2);

        private int multiplier;

        private Aligment(int multiplier) {
            this.multiplier = multiplier;
        }

        private int getMultiplier() {
            return this.multiplier;
        }

    }

    public static class BadPackException extends Exception {

        private static final long serialVersionUID = 2649279410609906582L;

        public BadPackException(String message) {
            super(message);
        }

        public BadPackException(String message, Throwable cause) {
            super(message, cause);
        }
    }
}
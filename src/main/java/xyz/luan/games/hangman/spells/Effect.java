package xyz.luan.games.hangman.spells;

import java.io.Serializable;
import java.util.Map;

public class Effect implements Serializable {

    private static final long serialVersionUID = -4064873792306188692L;

    private Action type;
    private boolean blocking;
    private Map<String, Double> parameters;

}
package xyz.luan.games.hangman.spells;

import java.io.Serializable;
import java.util.Map;

public class Requirement implements Serializable {

    private static final long serialVersionUID = 3756568647980639968L;

    private Action type;
    private Map<String, Double> parameters;
}
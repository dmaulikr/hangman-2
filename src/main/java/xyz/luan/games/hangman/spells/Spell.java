package xyz.luan.games.hangman.spells;

import java.io.Serializable;
import java.util.List;
import java.util.Set;

public class Spell implements Serializable {

    private static final long serialVersionUID = -6679642597645667817L;

    private String name;
    private double manaCost;
    private int iconCode;

    private Set<Requirement> costs;
    private List<Effect> effects;
}
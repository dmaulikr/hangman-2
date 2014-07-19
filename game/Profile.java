package game;

import java.io.Serializable;
import java.util.*;
import spells.*;

public class Profile implements Serializable {
  
  private float mana;
  private int points;

  private Set<Spell> grimoire;
}
package spells;

import java.io.Serializable;
import java.util.*;

public class Spell implements Serializable {
  
  private String name;
  private float manaCost;
  private int iconCode;

  private Set<Requirement> costs;
  private List<Effect> effects;
}
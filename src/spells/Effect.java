package spells;

import java.io.Serializable;
import java.util.*;

public class Effect implements Serializable {

  private Action type;
  private boolean blocking;
  private Map<String, Double> parameters;

}
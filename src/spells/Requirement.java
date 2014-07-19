package spells;

import java.io.Serializable;
import java.util.*;

public class Requirement implements Serializable {
  
  private Action type;
  private Map<String, Double> parameters;
}
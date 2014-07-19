package game;

import java.io.Serializable;
import java.util.*;
import spells.*;

public class Profile implements Serializable {
  
  private String username;
  private String passwordHash;
  private int avatarCode;

  private int points;
  private Set<Spell> grimoire;

  //stats
  private int gamePlayed;
  private double hoursLoggedIn;
  private double hoursPlayed;

  public String getName() {
    return this.username;
  }

  public int getAvatarCode() {
    return this.avatarCode;
  }
}
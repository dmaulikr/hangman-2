package hub;

import java.util.List;

import game.Profile;
import spells.Spell;

public class ConfiguredPlayer {
  
  private Profile profile;
  private List<Spell> spells;

  public Profile getProfile() {
    return this.profile;
  }

  public List<Spell> getSpells() {
    return this.spells;
  }
}
package drawing;

public class ScreenOptions {
  
  private int bigTileSize, smallTileSize;

  private double[] sections;
  /*
   * Main bar (game name and menu)
   * Duel bar (turn, player, phase)
   * Word
   * Own Player
   * The rest of the space will be divided for all the other players
   */

  private int fixDimension;
  private int fixedAmount;
  /*
   * fixDimension can be:
      0 - Horizontal
      1 - Vertical
    fixedAmount will be fixed in the desired orientation, and the other one will be expanded to fit all players
   */
}
package cs3500.pa03.model;

import java.util.List;
import java.util.Random;

/**
 * Player class for a player playing through the console
 */
public class ConsolePlayer extends SalvoPlayer {

  private final ConsolePlayerDependencies dependencies;

  /**
   * Initializes a ConsolePlayer
   *
   * @param name the name of the player
   * @param random a random object
   * @param dependencies a console player's dependencies
   */
  public ConsolePlayer(String name, Random random, ConsolePlayerDependencies dependencies) {
    super(name, random);
    this.dependencies = dependencies;
  }

  /**
   * Returns this player's shots on the opponent's board. The number of shots returned should
   * equal the number of ships on this player's board that have not sunk.
   *
   * @return the locations of shots on the opponent's board
   */
  @Override
  public List<Coord> takeShots() {
    this.dependencies.clearShots();
    int shotCount = this.getShotCount();
    this.dependencies.getView().shots(shotCount);
    while (this.dependencies.getCurrentTurn().size() < shotCount) {
      try {
        String[] shots = this.dependencies.getView().read();
        if (shots.length == 2) {
          int row = Integer.parseInt(shots[0]);
          int col = Integer.parseInt(shots[1]);
          if (row >= 0 && row < this.opponentBoard.length
              && col >= 0 && col < this.opponentBoard[row].length
              && !this.dependencies.getAllShots().contains(this.opponentBoard[row][col])) {
            this.dependencies.addCoord(this.opponentBoard[row][col]);
          } else {
            throw new IllegalArgumentException("Invalid shot.");
          }
        } else {
          throw new IllegalArgumentException("Invalid shot.");
        }
      } catch (Exception e) {
        this.dependencies.getView().invalidShots(shotCount
            - this.dependencies.getCurrentTurn().size());
      }
    }
    List<Coord> currentTurn = dependencies.getCurrentTurn();
    this.currentTurnShots = currentTurn;
    return currentTurn;
  }

}

package cs3500.pa03.controller;

import cs3500.pa03.model.BoardType;
import cs3500.pa03.model.Coord;
import cs3500.pa03.model.GameResult;
import cs3500.pa03.model.SalvoPlayer;
import cs3500.pa03.model.ShipType;
import cs3500.pa03.view.BattleSalvoView;
import java.util.HashMap;
import java.util.List;

/**
 * Represents a controller for BattleSalvo
 */
public class BattleSalvoController implements Controller {

  private final BattleSalvoView view;
  private final SalvoPlayer playerOne;
  private final SalvoPlayer playerTwo;

  /**
   * Instantiates a BattleSalvoController
   *
   * @param view a BattleSalvo view
   * @param one player one
   * @param two player two
   */
  public BattleSalvoController(BattleSalvoView view, SalvoPlayer one, SalvoPlayer two) {
    this.view = view;
    this.playerOne = one;
    this.playerTwo = two;
  }


  /**
   * Runs the controller
   */
  @Override
  public void run() {
    view.welcome();
    acceptSpecifications();
    play();
  }

  /**
   * Accepts the board size and fleet size from the user
   */
  public void acceptSpecifications() {

    // accepting board size
    view.dimensions();
    int[] dimensions = acceptBoardSize();
    int height = dimensions[0];
    int width = dimensions[1];
    int maxSize = Math.min(height, width);

    // accepting fleet size
    view.fleetSelect(maxSize);
    int[] fleet = acceptFleet(maxSize);
    HashMap<ShipType, Integer> map = new HashMap<>();
    map.put(ShipType.CARRIER, fleet[0]);
    map.put(ShipType.BATTLESHIP, fleet[1]);
    map.put(ShipType.DESTROYER, fleet[2]);
    map.put(ShipType.SUBMARINE, fleet[3]);

    // setup each player's board
    playerOne.setup(height, width, map);
    playerTwo.setup(height, width, map);
  }

  /**
   * Accepts a valid board size from the user
   *
   * @return an int[] of the width and height
   */
  private int[] acceptBoardSize() {
    while (true) {
      String[] input = view.read();
      int[] dimensions = new int[input.length];
      try {
        for (int i = 0; i < dimensions.length; i++) {
          int current = Integer.parseInt(input[i]);
          dimensions[i] = current;
        }
        if (dimensions.length == 2) {
          int height = dimensions[0];
          int width = dimensions[1];
          if (height >= 6 && height <= 15 && width >= 6 && width <= 15) {
            return dimensions;
          } else {
            throw new IllegalArgumentException("Invalid dimensions");
          }
        }
      } catch (Exception e) {
        view.invalidDimensions();
      }
    }
  }

  /**
   * Accepts a fleet of ships from the user
   *
   * @param maxSize the maximum size of the fleet
   * @return an int[] of the number of each ship in the fleet
   */
  private int[] acceptFleet(int maxSize) {
    while (true) {
      String[] input = view.read();
      int[] fleet = new int[input.length];
      int sum = 0;
      try {
        boolean greaterThanZero = false;
        for (int i = 0; i < fleet.length; i++) {
          int current = Integer.parseInt(input[i]);
          if (current <= 0) {
            greaterThanZero = true;
          }
          sum += current;
          fleet[i] = current;
        }
        if (!greaterThanZero && fleet.length == 4 && sum <= maxSize) {
          return fleet;
        } else {
          throw new IllegalArgumentException("Invalid fleet");
        }
      } catch (Exception e) {
        view.invalidFleet(maxSize);
      }
    }
  }

  /**
   * Plays the game until an outcome is reached
   */
  private void play() {
    while (!playerOne.gameOver() && !playerTwo.gameOver()) {
      view.displayBoard(playerOne.name(),
          playerOne.packageBoard(BoardType.USER));
      view.displayBoard(playerTwo.name(),
          playerTwo.packageBoard(BoardType.OPPONENT));
      List<Coord> playerOneShots = playerOne.takeShots();
      List<Coord> playerTwoShots = playerTwo.takeShots();
      List<Coord> playerOneHits = playerTwo.reportDamage(playerOneShots);
      List<Coord> playerTwoHits = playerOne.reportDamage(playerTwoShots);
      playerOne.successfulHits(playerOneHits);
      playerTwo.successfulHits(playerTwoHits);
    }
    view.displayBoard(playerOne.name(),
        playerOne.packageBoard(BoardType.USER));
    view.displayBoard(playerTwo.name(),
        playerTwo.packageBoard(BoardType.USER));
    end();
  }

  private void end() {
    GameResult playerOneResult;
    GameResult playerTwoResult;
    String reason;
    if (playerOne.gameOver() && playerTwo.gameOver()) {
      playerOneResult = GameResult.DRAW;
      playerTwoResult = GameResult.DRAW;
      reason = "both players have sunk the opponent's ships.";
    } else if (playerTwo.gameOver()) {
      playerOneResult = GameResult.WIN;
      playerTwoResult = GameResult.LOSE;
      reason = playerOne.name() + " has sunk all of " + playerTwo.name() + "'s ships.";
    } else {
      playerOneResult = GameResult.LOSE;
      playerTwoResult = GameResult.WIN;
      reason = playerTwo.name() + " has sunk all of " + playerOne.name() + "'s ships.";
    }
    playerOne.endGame(playerOneResult, reason);
    playerTwo.endGame(playerTwoResult, reason);
    view.result(playerOneResult, reason);
  }


}

package cs3500.pa03.view;

import cs3500.pa03.model.GameResult;
import java.io.IOException;
import java.util.Scanner;

/**
 * Class for displaying the BattleSalvo game to the console
 */
public class BattleSalvoConsoleView implements BattleSalvoView {

  private final Scanner scanner;
  private final Appendable appendable;

  /**
   * Instantiates a BattleSalvoConsoleView
   *
   * @param readable a readable source to read from
   * @param appendable an appendable to append the output to
   */
  public BattleSalvoConsoleView(Readable readable, Appendable appendable) {
    this.scanner = new Scanner(readable);
    this.appendable = appendable;
  }

  /**
   * Reads the next line of input from the readable
   *
   * @return the next int
   */
  public String[] read() {
    return scanner.nextLine().split(" ");
  }

  /**
   * Welcomes the user
   */
  @Override
  public void welcome() {
    try {
      appendable.append(System.getProperty("line.separator"));
      appendable.append("Welcome to BattleSalvo, Battleship with a twist!");
      appendable.append(System.getProperty("line.separator"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Prompts the user for board dimensions
   */
  @Override
  public void dimensions() {
    try {
      appendable.append("Please enter a valid height and width below:");
      appendable.append(System.getProperty("line.separator"));
      separator();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Re-prompts the user if the dimensions entered were invalid
   */
  @Override
  public void invalidDimensions() {
    try {
      appendable.append("You entered invalid dimensions.");
      appendable.append(System.getProperty("line.separator"));
      appendable.append("Board dimensions must be between 6 and 15 (inclusive).");
      appendable.append(System.getProperty("line.separator"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Prompts the user to select a fleet
   *
   * @param maxSize the maximum fleet size
   */
  @Override
  public void fleetSelect(int maxSize) {
    try {
      appendable.append("Please enter your fleet in the order "
          + "[Carrier, Battleship, Destroyer, Submarine].");
      appendable.append(System.getProperty("line.separator"));
      appendable.append("Remember, your fleet may not exceed ").append(String.valueOf(maxSize));
      appendable.append(System.getProperty("line.separator"));
      separator();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Re-prompts the user if the fleet entered was invalid
   *
   * @param maxSize the maximum fleet size
   */
  @Override
  public void invalidFleet(int maxSize) {
    try {
      appendable.append("You entered an invalid fleet.");
      appendable.append(System.getProperty("line.separator"));
      fleetSelect(maxSize);
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Prompts the user to enter valid shots
   *
   * @param numShots the number of shots they can enter
   */
  @Override
  public void shots(int numShots) {
    try {
      appendable.append("Please enter ").append(String.valueOf(numShots)).append(" shots.");
      appendable.append(System.getProperty("line.separator"));
      separator();
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Re-prompts the user if they enter an invalid shot
   *
   * @param numShots the number of shots they can enter
   */
  @Override
  public void invalidShots(int numShots) {
    try {
      appendable.append("The previous shot was invalid.");
      appendable.append(System.getProperty("line.separator"));
      appendable.append("Please enter ").append(String.valueOf(numShots)).append(" more shots.");
      appendable.append(System.getProperty("line.separator"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Displays a player's board
   *
   * @param player the player to display
   * @param board  the player's board
   */
  @Override
  public void displayBoard(String player, String board) {
    try {
      separator();
      appendable.append(player).append("'s board:");
      appendable.append(System.getProperty("line.separator"));
      appendable.append(System.getProperty("line.separator"));
      appendable.append(board);
      appendable.append(System.getProperty("line.separator"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Displays the result of the game
   *
   * @param result the result of the game
   * @param reason the reason for the result
   */
  @Override
  public void result(GameResult result, String reason) {
    try {
      separator();
      appendable.append(System.getProperty("line.separator"));
      appendable.append("Game over!");
      appendable.append(System.getProperty("line.separator"));
      appendable.append("You ").append(result.toString()).append(" because: ").append(reason);
      appendable.append(System.getProperty("line.separator"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

  /**
   * Displays a line separator
   */
  private void separator() {
    try {
      appendable.append("-----------------------------------------------------");
      appendable.append(System.getProperty("line.separator"));
    } catch (IOException e) {
      throw new RuntimeException(e);
    }
  }

}

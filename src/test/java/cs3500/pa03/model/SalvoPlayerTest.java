package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertFalse;
import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Collections;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing SalvoPlayer and its associated methods
 */
class SalvoPlayerTest extends SalvoTest {

  // color constants for output text
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_CYAN = "\u001B[36m";
  private static final String ANSI_RED = "\u001B[31m";
  private static final String ANSI_YELLOW = "\033[0;33m";

  private SalvoPlayer consolePlayer;
  private List<Ship> output;

  /**
   * Instantiates the test data
   */
  @BeforeEach
  public void setup() {
    consolePlayer = new ConsolePlayer("User", new Random(1),
        new ConsolePlayerDependencies());
    HashMap<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    output = consolePlayer.setup(6, 6, specifications);
    consolePlayer.opponentBoard[0][0].setType(CoordType.SHIP);
    consolePlayer.opponentBoard[0][1].setType(CoordType.SHIP);
    consolePlayer.opponentBoard[0][2].setType(CoordType.SHIP);
  }

  /**
   * Tests the name method
   */
  @Test
  void testName() {
    assertEquals("User", consolePlayer.name());
  }

  /**
   * Tests the setup method
   */
  @Test
  void testSetup() {
    for (int i = 0; i < output.size(); i++) {
      assertEquals(ships.get(i), output.get(i));
    }
  }

  /**
   * Tests the getShotCount method
   */
  @Test
  void testGetShotCount() {
    assertEquals(4, consolePlayer.getShotCount());
    consolePlayer.ships.get(0).sink();
    assertEquals(3, consolePlayer.getShotCount());
  }

  /**
   * Tests the reportDamage method
   */
  @Test
  void reportDamage() {
    List<Coord> damage = consolePlayer.reportDamage(carrierCoords);
    assertTrue(Coord.sameCoords(carrierCoords, damage));
    List<Coord> withMiss = new ArrayList<>();
    withMiss.add(battleshipCoordOne);
    withMiss.add(consolePlayer.userBoard[3][3]);
    List<Coord> expected = new ArrayList<>(Collections.singletonList(battleshipCoordOne));
    List<Coord> report = consolePlayer.reportDamage(withMiss);
    assertTrue(Coord.sameCoords(expected, report));
  }

  /**
   * Tests the successful hits method
   */
  @Test
  void successfulHits() {
    consolePlayer.currentTurnShots = new ArrayList<>(
        Arrays.asList(consolePlayer.opponentBoard[0][0], consolePlayer.opponentBoard[0][1],
        consolePlayer.opponentBoard[2][3]));
    assertEquals(CoordType.SHIP, consolePlayer.opponentBoard[0][0].getType());
    assertEquals(CoordType.SHIP, consolePlayer.opponentBoard[0][1].getType());
    assertEquals(CoordType.EMPTY, consolePlayer.opponentBoard[2][3].getType());
    List<Coord> successful = new ArrayList<>(
        Arrays.asList(consolePlayer.opponentBoard[0][0], consolePlayer.opponentBoard[0][1]));
    consolePlayer.successfulHits(successful);
    assertEquals(CoordType.HIT, consolePlayer.opponentBoard[0][0].getType());
    assertEquals(CoordType.HIT, consolePlayer.opponentBoard[0][1].getType());
    assertEquals(CoordType.MISS, consolePlayer.opponentBoard[2][3].getType());
  }

  /**
   * Tests the gameOver method
   */
  @Test
  void testGameOver() {
    assertFalse(consolePlayer.gameOver());
    for (Ship s : consolePlayer.ships) {
      s.sink();
    }
    assertTrue(consolePlayer.gameOver());
  }

  /**
   * Tests the endGame method
   */
  @Test
  void endGame() {
  }

  /**
   * Tests the packageBoard method
   */
  @Test
  void packageBoard() {
    ArrayList<Coord> shots = new ArrayList<>();
    shots.add(this.consolePlayer.userBoard[0][0]);
    shots.add(this.consolePlayer.userBoard[0][1]);
    this.consolePlayer.reportDamage(shots);
    this.consolePlayer.successfulHits(shots);
    String userBoard =
        ANSI_RED + "H" + ANSI_RESET + " " + ANSI_YELLOW + "M" + ANSI_RESET + " "
            + ANSI_CYAN + "S" + ANSI_RESET + " " + ANSI_CYAN
        + "S" + ANSI_RESET + " " + ANSI_CYAN + "S" + ANSI_RESET + " * "
            + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " " + ANSI_CYAN + "B" + ANSI_RESET + " * * * * "
        + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " " + ANSI_CYAN + "B" + ANSI_RESET + " * * * * "
            + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " " + ANSI_CYAN + "B" + ANSI_RESET + " * * * * "
        + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " " + ANSI_CYAN + "B" + ANSI_RESET + " "
        + ANSI_CYAN + "D" + ANSI_RESET + " " + ANSI_CYAN + "D" + ANSI_RESET
            + " " + ANSI_CYAN + "D" + ANSI_RESET + " " + ANSI_CYAN + "D" + ANSI_RESET + " "
            + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " " + ANSI_CYAN + "B" + ANSI_RESET + " * * * * "
        + System.getProperty("line.separator");
    String opponentBoard =
        ANSI_RED + "H" + ANSI_RESET + " " + ANSI_YELLOW + "M" + ANSI_RESET + " * * * * "
        + System.getProperty("line.separator")
        + "* * * * * * " + System.getProperty("line.separator")
        + "* * * * * * " + System.getProperty("line.separator")
        + "* * * * * * " + System.getProperty("line.separator")
        + "* * * * * * " + System.getProperty("line.separator")
        + "* * * * * * " + System.getProperty("line.separator").replaceAll(
        "\\n|\\r\\n", System.getProperty("line.separator"));
    assertEquals(userBoard, consolePlayer.packageBoard(BoardType.USER));
    assertEquals(opponentBoard, consolePlayer.packageBoard(BoardType.OPPONENT));
  }
}
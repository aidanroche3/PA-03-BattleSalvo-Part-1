package cs3500.pa03.view;

import static org.junit.jupiter.api.Assertions.assertArrayEquals;
import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import cs3500.pa03.model.GameResult;
import java.io.StringReader;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

class BattleSalvoConsoleViewTest {

  // color constants for output text
  public static final String ANSI_RESET = "\u001B[0m";
  public static final String ANSI_CYAN = "\u001B[36m";

  private Readable input;
  private StringBuilder output;
  private BattleSalvoConsoleView battleSalvoConsoleView;
  private BattleSalvoConsoleView mockConsoleView;

  /**
   * Instantiates the test data
   */
  @BeforeEach
  public void setup() {
    input = new StringReader("");
    output = new StringBuilder();
    MockAppendable mockAppendable = new MockAppendable();
    battleSalvoConsoleView = new BattleSalvoConsoleView(input, output);
    mockConsoleView = new BattleSalvoConsoleView(input, mockAppendable);
  }

  /**
   * Tests the read method
   */
  @Test
  void testRead() {
    input = new StringReader("hello world");
    battleSalvoConsoleView = new BattleSalvoConsoleView(input, output);
    String[] expectedOutput = {"hello", "world"};
    String[] readOutput = battleSalvoConsoleView.read();
    assertArrayEquals(expectedOutput, readOutput);
  }

  /**
   * Tests the welcome method
   */
  @Test
  void testWelcome() {
    String welcome = """
        
        Welcome to BattleSalvo, Battleship with a twist!
        """.replaceAll(
        "\\n|\\r\\n", System.getProperty("line.separator"));
    battleSalvoConsoleView.welcome();
    assertEquals(welcome, output.toString());
    assertThrows(RuntimeException.class, () -> mockConsoleView.welcome());
  }

  /**
   * Tests the dimensions method
   */
  @Test
  void testDimensions() {
    String dimensions = """
    Please enter a valid height and width below:
    -----------------------------------------------------
        """.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
    battleSalvoConsoleView.dimensions();
    assertEquals(dimensions, output.toString());
    assertThrows(RuntimeException.class, () -> mockConsoleView.dimensions());
  }

  /**
   * Tests the invalid dimensions method
   */
  @Test
  void testInvalidDimensions() {
    String invalidDimensions = """
    You entered invalid dimensions.
    Board dimensions must be between 6 and 15 (inclusive).
        """.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
    battleSalvoConsoleView.invalidDimensions();
    assertEquals(invalidDimensions, output.toString());
    assertThrows(RuntimeException.class, () -> mockConsoleView.invalidDimensions());
  }

  /**
   * Tests the fleet select method
   */
  @Test
  void testFleetSelect() {
    String fleetSelect = """
        Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
        Remember, your fleet may not exceed 8
        -----------------------------------------------------
        """.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
    battleSalvoConsoleView.fleetSelect(8);
    assertEquals(fleetSelect, output.toString());
    assertThrows(RuntimeException.class, () -> mockConsoleView.fleetSelect(8));
  }

  /**
   * Tests the invalidFleet method
   */
  @Test
  void testInvalidFleet() {
    String invalidFleet = """
        You entered an invalid fleet.
        Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
        Remember, your fleet may not exceed 10
        -----------------------------------------------------
        """.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
    battleSalvoConsoleView.invalidFleet(10);
    assertEquals(invalidFleet, output.toString());
    assertThrows(RuntimeException.class, () -> mockConsoleView.invalidFleet(10));
  }

  /**
   * Tests the shots method
   */
  @Test
  void testShots() {
    String shots = """
        Please enter 7 shots.
        -----------------------------------------------------
        """.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
    battleSalvoConsoleView.shots(7);
    assertEquals(shots, output.toString());
    assertThrows(RuntimeException.class, () -> mockConsoleView.shots(7));
  }

  /**
   * Tests the invalidShots method
   */
  @Test
  void testInvalidShots() {
    String invalidShots = """
        The previous shot was invalid.
        Please enter 3 more shots.
        """.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
    battleSalvoConsoleView.invalidShots(3);
    assertEquals(invalidShots, output.toString());
    assertThrows(RuntimeException.class, () -> mockConsoleView.invalidShots(3));
  }

  /**
   * Tests the displayBoard method
   */
  @Test
  void testDisplayBoard() {
    String board =
        ANSI_CYAN + "C" + ANSI_RESET + " " + ANSI_CYAN + "B B B B B " + ANSI_RESET
            + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " * * * * * "
            + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " * " + ANSI_CYAN + "D" + ANSI_RESET + " * * * "
            + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " * " + ANSI_CYAN + "D" + ANSI_RESET + " * * * "
            + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " * " + ANSI_CYAN + "D" + ANSI_RESET + " "
        + ANSI_CYAN + "S S S " + ANSI_RESET + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " * " + ANSI_CYAN + "D" + ANSI_RESET + " * * * ";
    String boardFormatted = "-----------------------------------------------------"
        + System.getProperty("line.separator")
        + "User's board:"
        + System.getProperty("line.separator")
        + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " " + ANSI_CYAN + "B B B B B " + ANSI_RESET
        + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " * * * * * "
        + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " * " + ANSI_CYAN + "D" + ANSI_RESET + " * * * "
        + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " * " + ANSI_CYAN + "D" + ANSI_RESET + " * * * "
        + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " * " + ANSI_CYAN + "D" + ANSI_RESET + " "
        + ANSI_CYAN + "S S S " + ANSI_RESET + System.getProperty("line.separator")
        + ANSI_CYAN + "C" + ANSI_RESET + " * " + ANSI_CYAN + "D" + ANSI_RESET + " * * * "
        + System.getProperty("line.separator");
    battleSalvoConsoleView.displayBoard("User", board);
    assertEquals(boardFormatted, output.toString());
    assertThrows(RuntimeException.class, () -> mockConsoleView.displayBoard("User", board));
  }

  /**
   * Tests the result method
   */
  @Test
  void testResult() {
    String resultOutput =
        "-----------------------------------------------------"
            + System.getProperty("line.separator")
            + System.getProperty("line.separator")
        + "Game over!" + System.getProperty("line.separator")
        + "You WIN because:  User sunk all of Computer's ships."
        + System.getProperty("line.separator");
    GameResult result = GameResult.WIN;
    String reason = " User sunk all of Computer's ships.";
    battleSalvoConsoleView.result(result, reason);
    assertEquals(resultOutput, output.toString());
    assertThrows(RuntimeException.class, () -> mockConsoleView.result(result, reason));
  }

}
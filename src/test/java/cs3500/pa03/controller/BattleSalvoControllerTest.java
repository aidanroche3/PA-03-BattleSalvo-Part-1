package cs3500.pa03.controller;

import static org.junit.jupiter.api.Assertions.assertEquals;

import cs3500.pa03.model.ComputerPlayer;
import cs3500.pa03.model.ConsolePlayer;
import cs3500.pa03.model.ConsolePlayerDependencies;
import cs3500.pa03.model.SalvoPlayer;
import cs3500.pa03.view.BattleSalvoConsoleView;
import cs3500.pa03.view.BattleSalvoView;
import java.io.StringReader;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Tests the BattleSalvoController test and its associated methods
 */
class BattleSalvoControllerTest {

  private BattleSalvoController winController;
  private Appendable winOutput;

  /**
   * Instantiates the test data
   */
  @BeforeEach
  void setup() {
    String winInputs = """
        hello hello hello
        6 6 6
        18 -1
        -1 18
        6 6
        -1 2 3 4
        1 2 3 4 5 5
        1 2 3
        2 3 4 5
        1 1 1 1
        0 0
        1 1
        2 2
        3 3
        0 1
        0 2
        0 3
        0 4
        0 5
        1 0
        2 0
        3 0
        4 0
        5 0
        2 1
        3 1
        hello
        -1 0
        2 1 2
        18 18
        0 0
        4 1
        5 1
        2 4
        2 5
        1 4
        4 3
        4 4
        5 3
        4 2
        4 5
        5 4
        5 5
        """.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
    Readable winInput = new StringReader(winInputs);
    winOutput = new StringBuilder();
    BattleSalvoView winView = new BattleSalvoConsoleView(winInput, winOutput);
    ConsolePlayer winPlayerOne = new ConsolePlayer("User", new Random(1),
        new ConsolePlayerDependencies());
    SalvoPlayer winPlayerTwo = new ComputerPlayer(new Random(1));
    winController = new BattleSalvoController(winView, winPlayerOne, winPlayerTwo);
  }

  /**
   * Tests the run method with an integration test
   */
  @Test
  void testRunWin() {
    String expected = """
                
        Welcome to BattleSalvo, Battleship with a twist!
        Please enter a valid height and width below:
        -----------------------------------------------------
        You entered invalid dimensions.
        Board dimensions must be between 6 and 15 (inclusive).
        You entered invalid dimensions.
        Board dimensions must be between 6 and 15 (inclusive).
        You entered invalid dimensions.
        Board dimensions must be between 6 and 15 (inclusive).
        Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
        Remember, your fleet may not exceed 6
        -----------------------------------------------------
        You entered an invalid fleet.
        Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
        Remember, your fleet may not exceed 6
        -----------------------------------------------------
        You entered an invalid fleet.
        Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
        Remember, your fleet may not exceed 6
        -----------------------------------------------------
        You entered an invalid fleet.
        Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
        Remember, your fleet may not exceed 6
        -----------------------------------------------------
        You entered an invalid fleet.
        Please enter your fleet in the order [Carrier, Battleship, Destroyer, Submarine].
        Remember, your fleet may not exceed 6
        -----------------------------------------------------
        -----------------------------------------------------
        User's board:
                
        [36mC[0m * [36mS[0m [36mS[0m [36mS[0m *\s
        [36mC[0m [36mB[0m * * * *\s
        [36mC[0m [36mB[0m * * * *\s
        [36mC[0m [36mB[0m * * * *\s
        [36mC[0m [36mB[0m [36mD[0m [36mD[0m [36mD[0m [36mD[0m\s
        [36mC[0m [36mB[0m * * * *\s
                
        -----------------------------------------------------
        Computer's board:
                
        * * * * * *\s
        * * * * * *\s
        * * * * * *\s
        * * * * * *\s
        * * * * * *\s
        * * * * * *\s
                
        Please enter 4 shots.
        -----------------------------------------------------
        -----------------------------------------------------
        User's board:
                
        [36mC[0m * [36mS[0m [36mS[0m [36mS[0m *\s
        [36mC[0m [36mB[0m * * * *\s
        [36mC[0m [36mB[0m * * * *\s
        [36mC[0m [36mB[0m [0;33mM[0m * * *\s
        [36mC[0m [31mH[0m [36mD[0m [36mD[0m [36mD[0m [36mD[0m\s
        [36mC[0m [36mB[0m * [0;33mM[0m * [0;33mM[0m\s
                        
        -----------------------------------------------------
        Computer's board:
                
        [31mH[0m * * * * *\s
        * [31mH[0m * * * *\s
        * * [0;33mM[0m * * *\s
        * * * [0;33mM[0m * *\s
        * * * * * *\s
        * * * * * *\s
                
        Please enter 4 shots.
        -----------------------------------------------------
        -----------------------------------------------------
        User's board:
                
        [36mC[0m * [36mS[0m [36mS[0m [36mS[0m [0;33mM[0m\s
        [36mC[0m [36mB[0m * * [0;33mM[0m *\s
        [31mH[0m [36mB[0m * * * *\s
        [31mH[0m [36mB[0m [0;33mM[0m * * *\s
        [36mC[0m [31mH[0m [36mD[0m [36mD[0m [36mD[0m [36mD[0m\s
        [36mC[0m [36mB[0m * [0;33mM[0m * [0;33mM[0m\s
                        
        -----------------------------------------------------
        Computer's board:
                
        [31mH[0m [0;33mM[0m [31mH[0m [31mH[0m [31mH[0m *\s
        * [31mH[0m * * * *\s
        * * [0;33mM[0m * * *\s
        * * * [0;33mM[0m * *\s
        * * * * * *\s
        * * * * * *\s
                
        Please enter 4 shots.
        -----------------------------------------------------
        -----------------------------------------------------
        User's board:
                
        [36mC[0m * [36mS[0m [36mS[0m [36mS[0m [0;33mM[0m\s
        [36mC[0m [36mB[0m * [0;33mM[0m [0;33mM[0m *\s
        [31mH[0m [36mB[0m * * * *\s
        [31mH[0m [36mB[0m [0;33mM[0m * * *\s
        [36mC[0m [31mH[0m [36mD[0m [36mD[0m [36mD[0m [36mD[0m\s
        [31mH[0m [36mB[0m * [0;33mM[0m [0;33mM[0m [0;33mM[0m\s
                
        -----------------------------------------------------
        Computer's board:
                
        [31mH[0m [0;33mM[0m [31mH[0m [31mH[0m [31mH[0m [0;33mM[0m\s
        [31mH[0m [31mH[0m * * * *\s
        [31mH[0m * [0;33mM[0m * * *\s
        [31mH[0m * * [0;33mM[0m * *\s
        * * * * * *\s
        * * * * * *\s
                
        Please enter 4 shots.
        -----------------------------------------------------
        -----------------------------------------------------
        User's board:
                
        [36mC[0m * [36mS[0m [36mS[0m [36mS[0m [0;33mM[0m\s
        [36mC[0m [36mB[0m * [0;33mM[0m [0;33mM[0m *\s
        [31mH[0m [36mB[0m [0;33mM[0m * * [0;33mM[0m\s
        [31mH[0m [31mH[0m [0;33mM[0m * * *\s
        [36mC[0m [31mH[0m [36mD[0m [36mD[0m [36mD[0m [36mD[0m\s
        [31mH[0m [36mB[0m * [0;33mM[0m [0;33mM[0m [0;33mM[0m\s
                
        -----------------------------------------------------
        Computer's board:
                
        [31mH[0m [0;33mM[0m [31mH[0m [31mH[0m [31mH[0m [0;33mM[0m\s
        [31mH[0m [31mH[0m * * * *\s
        [31mH[0m [31mH[0m [0;33mM[0m * * *\s
        [31mH[0m [31mH[0m * [0;33mM[0m * *\s
        [31mH[0m * * * * *\s
        [31mH[0m * * * * *\s
                
        Please enter 4 shots.
        -----------------------------------------------------
        The previous shot was invalid.
        Please enter 4 more shots.
        The previous shot was invalid.
        Please enter 4 more shots.
        The previous shot was invalid.
        Please enter 4 more shots.
        The previous shot was invalid.
        Please enter 4 more shots.
        The previous shot was invalid.
        Please enter 4 more shots.
        -----------------------------------------------------
        User's board:
                
        [36mC[0m * [36mS[0m [31mH[0m [36mS[0m [0;33mM[0m\s
        [36mC[0m [36mB[0m * [0;33mM[0m [0;33mM[0m *\s
        [31mH[0m [36mB[0m [0;33mM[0m * * [0;33mM[0m\s
        [31mH[0m [31mH[0m [0;33mM[0m * * *\s
        [31mH[0m [31mH[0m [36mD[0m [36mD[0m [36mD[0m [36mD[0m\s
        [31mH[0m [36mB[0m * [0;33mM[0m [0;33mM[0m [0;33mM[0m\s
                
        -----------------------------------------------------
        Computer's board:
                
        [31mH[0m [0;33mM[0m [31mH[0m [31mH[0m [31mH[0m [0;33mM[0m\s
        [31mH[0m [31mH[0m * * * *\s
        [31mH[0m [31mH[0m [0;33mM[0m * [0;33mM[0m [0;33mM[0m\s
        [31mH[0m [31mH[0m * [0;33mM[0m * *\s
        [31mH[0m [31mH[0m * * * *\s
        [31mH[0m [31mH[0m * * * *\s
                
        Please enter 4 shots.
        -----------------------------------------------------
        -----------------------------------------------------
        User's board:
                
        [31mH[0m * [36mS[0m [31mH[0m [36mS[0m [0;33mM[0m\s
        [36mC[0m [36mB[0m * [0;33mM[0m [0;33mM[0m *\s
        [31mH[0m [36mB[0m [0;33mM[0m * * [0;33mM[0m\s
        [31mH[0m [31mH[0m [0;33mM[0m * * *\s
        [31mH[0m [31mH[0m [36mD[0m [36mD[0m [36mD[0m [36mD[0m\s
        [31mH[0m [36mB[0m * [0;33mM[0m [0;33mM[0m [0;33mM[0m\s
                
        -----------------------------------------------------
        Computer's board:
                
        [31mH[0m [0;33mM[0m [31mH[0m [31mH[0m [31mH[0m [0;33mM[0m\s
        [31mH[0m [31mH[0m * * [0;33mM[0m *\s
        [31mH[0m [31mH[0m [0;33mM[0m * [0;33mM[0m [0;33mM[0m\s
        [31mH[0m [31mH[0m * [0;33mM[0m * *\s
        [31mH[0m [31mH[0m * [31mH[0m [31mH[0m *\s
        [31mH[0m [31mH[0m * [0;33mM[0m * *\s
                
        Please enter 4 shots.
        -----------------------------------------------------
        -----------------------------------------------------
        User's board:
                
        [31mH[0m * [36mS[0m [31mH[0m [36mS[0m [0;33mM[0m\s
        [36mC[0m [31mH[0m * [0;33mM[0m [0;33mM[0m *\s
        [31mH[0m [36mB[0m [0;33mM[0m * * [0;33mM[0m\s
        [31mH[0m [31mH[0m [0;33mM[0m * * *\s
        [31mH[0m [31mH[0m [36mD[0m [36mD[0m [36mD[0m [36mD[0m\s
        [31mH[0m [36mB[0m * [0;33mM[0m [0;33mM[0m [0;33mM[0m\s
                
        -----------------------------------------------------
        Computer's board:
                
        [31mH[0m [0;33mM[0m [31mH[0m [31mH[0m [31mH[0m [0;33mM[0m\s
        [31mH[0m [31mH[0m * * [0;33mM[0m *\s
        [31mH[0m [31mH[0m [0;33mM[0m * [0;33mM[0m [0;33mM[0m\s
        [31mH[0m [31mH[0m * [0;33mM[0m * *\s
        [31mH[0m [31mH[0m [31mH[0m [31mH[0m [31mH[0m [31mH[0m\s
        [31mH[0m [31mH[0m * [0;33mM[0m [0;33mM[0m [0;33mM[0m\s
                
        -----------------------------------------------------
                
        Game over!
        You WIN because: User has sunk all of Computer's ships.
        """.replaceAll("\\n|\\r\\n", System.getProperty("line.separator"));
    winController.run();
    assertEquals(expected, winOutput.toString());

  }
}
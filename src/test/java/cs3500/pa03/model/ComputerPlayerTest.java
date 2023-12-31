package cs3500.pa03.model;

import static org.junit.jupiter.api.Assertions.assertTrue;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashMap;
import java.util.List;
import java.util.Random;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;

/**
 * Class for testing ComputerPlayer and its associated methods
 */
class ComputerPlayerTest {

  private ComputerPlayer player;

  /**
   * Instantiates the test data
   */
  @BeforeEach
  void setup() {
    player = new ComputerPlayer(new Random(1));
    HashMap<ShipType, Integer> specifications = new HashMap<>();
    specifications.put(ShipType.CARRIER, 1);
    specifications.put(ShipType.BATTLESHIP, 1);
    specifications.put(ShipType.DESTROYER, 1);
    specifications.put(ShipType.SUBMARINE, 1);
    player.setup(6, 6, specifications);
  }


  /**
   * Tests the takeShots method
   */
  @Test
  void takeShots() {
    Coord one = new Coord(4, 1, CoordType.EMPTY);
    Coord two = new Coord(5, 5, CoordType.EMPTY);
    Coord three = new Coord(3, 2, CoordType.EMPTY);
    Coord four = new Coord(5, 3, CoordType.EMPTY);
    List<Coord> expected = new ArrayList<>(Arrays.asList(one, two, three, four));
    List<Coord> shots = player.takeShots();
    assertTrue(Coord.sameCoords(expected, shots));
  }
}
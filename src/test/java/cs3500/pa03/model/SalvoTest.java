package cs3500.pa03.model;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.List;

/**
 * Abstract class for holding Salvo test data
 */
public abstract class SalvoTest {

  protected Coord carrierCoordOne = new Coord(0, 0, CoordType.SHIP);
  protected Coord carrierCoordTwo = new Coord(1, 0, CoordType.SHIP);
  protected Coord carrierCoordThree = new Coord(2, 0, CoordType.SHIP);
  protected Coord carrierCoordFour = new Coord(3, 0, CoordType.SHIP);
  protected Coord carrierCoordFive = new Coord(4, 0, CoordType.SHIP);
  protected Coord carrierCoordSix = new Coord(5, 0, CoordType.SHIP);
  protected ArrayList<Coord> carrierCoords =
      new ArrayList<>(Arrays.asList(carrierCoordOne, carrierCoordTwo, carrierCoordThree,
          carrierCoordFour, carrierCoordFive, carrierCoordSix));
  protected Ship carrier = new Ship(ShipType.CARRIER, carrierCoords);
  protected Coord battleshipCoordOne = new Coord(1, 1, CoordType.SHIP);
  protected Coord battleshipCoordTwo = new Coord(2, 1, CoordType.SHIP);
  protected Coord battleshipCoordThree = new Coord(3, 1, CoordType.SHIP);
  protected Coord battleshipCoordFour = new Coord(4, 1, CoordType.SHIP);
  protected Coord battleshipCoordFive = new Coord(5, 1, CoordType.SHIP);
  protected ArrayList<Coord> battleshipCoords =
      new ArrayList<>(Arrays.asList(battleshipCoordOne, battleshipCoordTwo, battleshipCoordThree,
          battleshipCoordFour, battleshipCoordFive));
  protected Ship battleship = new Ship(ShipType.BATTLESHIP, battleshipCoords);
  protected Coord destroyerCoordOne = new Coord(4, 2, CoordType.SHIP);
  protected Coord destroyerCoordTwo = new Coord(4, 3, CoordType.SHIP);
  protected Coord destroyerCoordThree = new Coord(4, 4, CoordType.SHIP);
  protected Coord destroyerCoordFour = new Coord(4, 5, CoordType.SHIP);
  protected ArrayList<Coord> destroyerCoords =
      new ArrayList<>(Arrays.asList(destroyerCoordOne, destroyerCoordTwo, destroyerCoordThree,
          destroyerCoordFour));
  protected Ship destroyer = new Ship(ShipType.DESTROYER, destroyerCoords);
  protected Coord submarineCoordOne = new Coord(0, 2, CoordType.SHIP);
  protected Coord submarineCoordTwo = new Coord(0, 3, CoordType.SHIP);
  protected Coord submarineCoordThree = new Coord(0, 4, CoordType.SHIP);
  protected ArrayList<Coord> submarineCoords =
      new ArrayList<>(Arrays.asList(submarineCoordOne, submarineCoordTwo, submarineCoordThree));
  protected Ship submarine = new Ship(ShipType.SUBMARINE, submarineCoords);
  protected List<Ship> ships
      = new ArrayList<>(Arrays.asList(carrier, battleship, destroyer, submarine));

}

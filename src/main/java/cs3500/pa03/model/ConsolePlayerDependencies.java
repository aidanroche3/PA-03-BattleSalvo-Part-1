package cs3500.pa03.model;

import cs3500.pa03.view.BattleSalvoView;
import java.util.ArrayList;
import java.util.List;

/**
 * Dependency class for handling console user shot inputs
 */
public class ConsolePlayerDependencies {

  private final ArrayList<Coord> currentTurn = new ArrayList<>();
  private final ArrayList<Coord> allShots = new ArrayList<>();
  private final BattleSalvoView view;

  /**
   * Instantiates a ConsolePlayerDependencies
   *
   * @param view a BattleSalvoView
   */
  public ConsolePlayerDependencies(BattleSalvoView view) {
    this.view = view;
  }


  public void addCoord(Coord c) {
    this.currentTurn.add(c);
  }

  public void clearShots() {
    this.allShots.addAll(currentTurn);
    this.currentTurn.clear();
  }

  public List<Coord> getCurrentTurn() {
    return List.copyOf(currentTurn);
  }

  public List<Coord> getAllShots() {
    return List.copyOf(allShots);
  }

  public BattleSalvoView getView() {
    return this.view;
  }

}

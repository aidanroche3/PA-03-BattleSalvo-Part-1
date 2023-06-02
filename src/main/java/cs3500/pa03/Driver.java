package cs3500.pa03;

import cs3500.pa03.controller.BattleSalvoController;
import cs3500.pa03.model.ComputerPlayer;
import cs3500.pa03.model.ConsolePlayer;
import cs3500.pa03.model.ConsolePlayerDependencies;
import cs3500.pa03.model.SalvoPlayer;
import cs3500.pa03.view.BattleSalvoConsoleView;
import cs3500.pa03.view.BattleSalvoView;
import java.io.InputStreamReader;
import java.util.Random;

/**
 * This is the main driver of this project.
 */
public class Driver {
  /**
   * Project entry point
   *
   * @param args - no command line args required
   */
  public static void main(String[] args) {
    BattleSalvoView view = new BattleSalvoConsoleView(new InputStreamReader(System.in), System.out);
    ConsolePlayer playerOne = new ConsolePlayer("User",
        new Random(), new ConsolePlayerDependencies());
    SalvoPlayer playerTwo = new ComputerPlayer(new Random());
    new BattleSalvoController(view, playerOne, playerTwo).run();
  }
}
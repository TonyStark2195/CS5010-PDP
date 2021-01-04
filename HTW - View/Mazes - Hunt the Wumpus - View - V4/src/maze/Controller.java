package maze;

import java.util.Scanner;

/** This class serves as a controller for the interactive Hunt the Wumpus game. */
public class Controller {

  Maze maze;

  /**
   * The default constructor of the controller class.
   *
   * @param generatedMaze the generated maze as input
   */
  public Controller(Maze generatedMaze) {
    this.maze = generatedMaze;
  }

  /** The method where the interaction of the game with the user is handled. */
  public void playGame() {
    String str;
    boolean done = false;
    while (!done) {
      System.out.println("You are currently in: ");
      System.out.println(maze.getCurrentRoom().toString());
      System.out.println("\ns (Shoot) or m (Move): ");
      Scanner sc = new Scanner(System.in);
      str = sc.nextLine();

      switch (str.toLowerCase()) {
        case "s":
          System.out.println("Enter the direction you want to shoot your arrow: ");
          System.out.println(maze.getLegalActions().toString());
          Scanner ar = new Scanner(System.in);
          str = ar.nextLine();

          switch (str.toLowerCase()) {
            case "s":
            case "south":
              str = "South";
              break;
            case "n":
            case "north":
              str = "North";
              break;
            case "e":
            case "east":
              str = "East";
              break;
            case "w":
            case "west":
              str = "West";
              break;
            default:
              System.out.println("Wrong Input given!");
              break;
          }

          System.out.println("Enter the distance your arrow should travel: (integer)");
          int limit = ar.nextInt();
          done = maze.moveArrow(str, limit);
          break;
        case "m":
          System.out.println("Enter your choice of actions!");
          System.out.println(maze.getLegalActions().toString());
          Scanner scan = new Scanner(System.in);
          str = scan.nextLine();

          switch (str.toLowerCase()) {
            case "s":
            case "south":
              str = "South";
              break;
            case "n":
            case "north":
              str = "North";
              break;
            case "e":
            case "east":
              str = "East";
              break;
            case "w":
            case "west":
              str = "West";
              break;
            default:
              System.out.println("Wrong Input given!");
              break;
          }
          done = maze.movePlayer(str);
          break;
        default:
          System.out.println("Wrong Input given!");
          break;
      }
    }
    System.out.println(maze.getCurrentRoom().toString());
  }
}

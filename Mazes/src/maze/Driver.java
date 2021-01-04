package maze;

import java.util.Scanner;

/**
 * This class serves as a lightweight demo to try and run the outputs. Contains the main method that
 * runs the following code of playing an interactive maze game.
 */
public class Driver {
  /**
   * The main class where the demo code is written.
   *
   * @param args the input arguments from the user
   * @throws IllegalArgumentException if there are any incorrect arguments
   */
  public static void main(String[] args) throws IllegalArgumentException {
    Maze maze;
    int r;
    int c;
    int w;

    System.out.println("\n Enter the type of Maze to Play: (String) \n Perfect\n Non Perfect\n");
    Scanner sc = new Scanner(System.in);
    String str = sc.nextLine();

    if (str.equals("Perfect")) {
      System.out.println("You have selected Perfect Maze\n");
      System.out.println(
          "\n Enter the Sub-type of Maze to Play: (String) \n Wrapping\n " + "Non Wrapping\n");
      str = sc.nextLine();

      if (str.equals("Wrapping")) {
        System.out.println("You have selected Wrapping Maze\n");
        System.out.println(
            "\n Enter the number of rows: r (Integer) and number of columns: c (Integer) \n");
        r = sc.nextInt();
        c = sc.nextInt();

        maze = new WrappingMaze(r, c);
      } else {
        System.out.println("You have selected Non Wrapping Maze\n");
        System.out.println(
            "\n Enter the number of rows: r (Integer) and number of columns: c (Integer) \n");
        r = sc.nextInt();
        c = sc.nextInt();

        maze = new NonWrappingMaze(r, c);
      }

    } else {
      System.out.println("You have selected Non Perfect Maze\n");
      System.out.println(
          "\n Enter the Sub-type of Maze to Play: (String) \n Wrapping\n " + "Non Wrapping\n");
      str = sc.nextLine();

      if (str.equals("Wrapping")) {
        System.out.println("You have selected Wrapping Maze\n");
        System.out.println(
            "\n Enter the number of rows: r (Integer) and number of columns: c (Integer) \n");
        r = sc.nextInt();
        c = sc.nextInt();
        System.out.println("\n Enter the number of remaining walls: w (Integer) \n");
        w = sc.nextInt();

        maze = new WrappingMaze(r, c, w);
      } else {
        System.out.println(
            "\n Enter the number of rows: r (Integer) and number of columns: c (Integer) \n");
        r = sc.nextInt();
        c = sc.nextInt();
        System.out.println("\n Enter the number of remaining walls: w (Integer) \n");
        w = sc.nextInt();

        maze = new NonWrappingMaze(r, c, w);
      }
    }

    System.out.println("Maze will be generated! \n");
    maze.generate();
    System.out.println("Maze generated! \n");

    System.out.println(
        "\n Enter the Staring Location Co-ordinates: X (between 0 to "
            + (r - 1)
            + ") and Y (between 0 to "
            + (c - 1)
            + ") (Integers) \n");

    int x;
    int y;

    x = sc.nextInt();
    y = sc.nextInt();
    maze.setStartLoc(x, y);

    System.out.println(
        "\n Enter the Goal Location Co-ordinates: X (between 0 to "
            + (r - 1)
            + ") and Y (between 0 to "
            + (c - 1)
            + ") (Integers) \n");
    x = sc.nextInt();
    y = sc.nextInt();
    maze.setGoalLoc(x, y);

    System.out.println("Maze Objects will be initialized! \n");
    maze.generateMazeObjects();
    System.out.println("Maze Objects initialized! \n");
    System.out.println("Walls exist between:\n" + maze.getWalls());
    System.out.println("The Maze:\n" + maze.toString());

    while (!maze.isGoal()) {
      System.out.println("You are currently in: ");
      System.out.println(maze.getCurrentRoom().toString());
      System.out.println("Enter your choice of actions!");
      System.out.println(maze.getLegalActions().toString());
      str = sc.nextLine();
      maze.movePlayer(str);
    }
    System.out.println("Game Over!");
    System.out.println("The Game ended with following stats: ");
    System.out.println(maze.getCurrentRoom().toString());
  }
}

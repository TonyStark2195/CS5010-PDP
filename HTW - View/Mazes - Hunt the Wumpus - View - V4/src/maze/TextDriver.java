package maze;

import java.util.Scanner;

/**
 * This class serves as a lightweight demo to try and run the outputs. Contains the main method that
 * runs the following code of playing an interactive Hunt the Wumpus game.
 */
public class TextDriver {
  /**
   * The main class where the demo code is written.
   *
   * @throws IllegalArgumentException if there are any incorrect arguments
   */
  public static void main() throws IllegalArgumentException {
    int r = 4;
    int c = 3;
    Maze maze = new NonWrappingMaze(r, c);
    int w;
    Scanner sc = new Scanner(System.in);
    String str;

    boolean out = true;
    while (out) {
      System.out.println(
          "\n Enter the type of Maze to Play: (String) \n p (Perfect)\n np (Non Perfect)\n");
      str = sc.nextLine();
      if (str.equals("p")) {
        System.out.println("\nYou have selected Perfect Maze\n");
        System.out.println(
            "\n Enter the Sub-type of Maze to Play: (String) \n w (Wrapping)\n "
                + "nw (Non Wrapping)\n");
        str = sc.nextLine();

        boolean done = true;
        while (done) {
          if (str.equals("w")) {
            System.out.println("\nYou have selected Wrapping Maze\n");
            System.out.println(
                "\n Enter the number of rows: r (Integer) and number of columns: c (Integer) \n");
            r = sc.nextInt();
            c = sc.nextInt();

            maze = new WrappingMaze(r, c);
            done = false;
          } else if (str.equals("nw")) {
            System.out.println("\nYou have selected Non Wrapping Maze\n");
            System.out.println(
                "\n Enter the number of rows: r (Integer) and number of columns: c (Integer) \n");
            r = sc.nextInt();
            c = sc.nextInt();

            maze = new NonWrappingMaze(r, c);
            done = false;
          } else {
            System.out.println("Wrong Input given!");
            System.out.println(
                "\n Enter the Sub-type of Maze to Play: (String) \n w (Wrapping)\n "
                    + "nw (Non Wrapping)\n");
            str = sc.nextLine();
          }
        }
        out = false;
      } else if (str.equals("np")) {
        System.out.println("\nYou have selected Non Perfect Maze\n");
        System.out.println(
            "\n Enter the Sub-type of Maze to Play: (String) \n w (Wrapping)\n "
                + "nw (Non Wrapping)\n");
        str = sc.nextLine();

        boolean done = true;
        while (done) {
          if (str.equals("w")) {
            System.out.println("\nYou have selected Wrapping Maze\n");
            System.out.println(
                "\n Enter the number of rows: r (Integer) and number of columns: c (Integer) \n");
            r = sc.nextInt();
            c = sc.nextInt();
            System.out.println("\n Enter the number of remaining walls: w (Integer) \n");
            w = sc.nextInt();

            maze = new WrappingMaze(r, c, w);
            done = false;
          } else if (str.equals("nw")) {
            System.out.println(
                "\n Enter the number of rows: r (Integer) and number of columns: c (Integer) \n");
            r = sc.nextInt();
            c = sc.nextInt();
            System.out.println("\n Enter the number of remaining walls: w (Integer) \n");
            w = sc.nextInt();

            maze = new NonWrappingMaze(r, c, w);
            done = false;
          } else {
            System.out.println("Wrong Input given!");
            System.out.println(
                "\n Enter the Sub-type of Maze to Play: (String) \n w (Wrapping)\n "
                    + "nw (Non Wrapping)\n");
            str = sc.nextLine();
          }
        }
        out = false;
      } else {
        System.out.println("Wrong Input given!");
      }
    }

    System.out.println("\nWumpus game will be generated! \n");
    maze.generate();
    System.out.println("Wumpus game generated!");

    System.out.println(
        "\n Enter the percentage of Super Bats (0 - 40) "
            + "and Percentage of Bottomless pits (0 - 30)\n");
    int pb = sc.nextInt();
    int pp = sc.nextInt();

    System.out.println("\nWumpus game Objects will be initialized! \n");

    maze.generateMazeObjects(0, pb, pp);
    System.out.println("Wumpus game Objects initialized! \n");

    System.out.println(maze.toString());

    Controller controller = new Controller(maze);
    controller.playGame();
  }
}

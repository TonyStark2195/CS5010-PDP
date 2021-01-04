package maze;

import java.io.IOException;
import java.util.function.Consumer;

public class MVCController implements Consumer<String> {
  private Maze model;
  private View view;
  private mazeView htwView;

  //  /**
  //   * Constructor.
  //   *
  //   * @param m the model to use
  //   */
  //  public MVCController(Maze m) {
  //    model = m;
  //  }

  /**
   * Mutator for the view.
   *
   * @param v the view to use
   */
  public void setView(View v) {
    view = v;
    // give the feature callbacks to the view
    view.setFeatures(this);
  }

  //  @Override
  public void processInput(
      String perfect, String wrapping, int row, int col, int walls, int bats, int pits)
      throws IOException {
    System.out.println(perfect);
    System.out.println(wrapping);
    if (perfect.equals("p") && wrapping.equals("w")) {
      System.out.println("Perfect Wrapping Maze");
      model = new WrappingMaze(row, col);
    } else if (perfect.equals("p") && wrapping.equals("nw")) {
      System.out.println("Perfect Non-Wrapping Maze");
      model = new NonWrappingMaze(row, col);
    } else if (perfect.equals("np") && wrapping.equals("w")) {
      System.out.println("Non Perfect Wrapping Maze");
      model = new WrappingMaze(row, col, walls);
    } else if (perfect.equals("np") && wrapping.equals("nw")) {
      System.out.println("Non Perfect Non Wrapping Maze");
      model = new WrappingMaze(row, col, walls);
    }

    System.out.println("\nMaze will be generated! \n");
    model.generate();
    System.out.println("Maze generated!");

    System.out.println("\nWumpus game will be generated! \n");
    model.generateMazeObjects(0, bats, pits);
    System.out.println("Wumpus game generated!");

    System.out.println(model.toString());

    htwView = new mazeView("Hunt The Wumpus Game!", this);

    //    htwView.setFeatures(this);
    go();

    //    model.setString(text);
    //
    //    // clear input text field
    //    view.clearInputString();
    //    // finally echo the string in view
    //    view.setEchoOutput(model.getString());
    //
    //    // set focus back to main frame so that keyboard events work
    //    view.resetFocus();
  }

  //  @Override
  public void go() {
    // set this as the command callback
    this.htwView.setCommandCallback(s -> accept(s));
  }

  @Override
  public void accept(String s) {
    String[] inputArgs = s.split(" ");
    boolean gameStatus = false;
    String command = inputArgs[1];
    command = moveParser(command);

    if (inputArgs.length == 2 && inputArgs[0].equals("MOVE")) {

      if (command != null) {
        System.out.println(s);
        gameStatus = playGame(command);
      }

      System.out.println(model.getCurrentRoom().toString());

      //      try {
      //        htwView.refresh(model, gameStatus, inputArgs[0], command);
      //      } catch (IOException e) {
      //        e.printStackTrace();
      //      }

    } else if (inputArgs.length == 3 && inputArgs[0].equals("SHOOT")) {
      //      String command = inputArgs[1];
      //      command = moveParser(command);

      int dist;
      try {
        dist = Integer.parseInt(String.valueOf(inputArgs[2]));
      } catch (Exception e) {
        dist = 1;
      }

      if (command != null) {
        System.out.println(s);
        gameStatus = shootArrow(command, dist);
      }

      System.out.println(model.getCurrentRoom().toString());
    }

    try {
      htwView.refresh(model, gameStatus, inputArgs[0], command);
    } catch (IOException e) {
      e.printStackTrace();
    }

    if (gameStatus) {
      exitProgram();
    }
  }

  private String moveParser(String inputCommand) {
    String str;
    switch (inputCommand.toLowerCase()) {
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
        str = "";
        break;
    }
    return str;
  }

  public boolean playGame(String move) {
    return model.movePlayer(move);
  }

  public boolean shootArrow(String move, int dist) {
    return model.moveArrow(move, dist);
  }

  public Maze getModel() {
    return this.model;
  }

  public void exitProgram() {
    System.exit(0);
  }
}

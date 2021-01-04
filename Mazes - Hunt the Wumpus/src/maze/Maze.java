package maze;

import java.util.HashMap;
import java.util.Set;

/** This interface contains all the operations that all types of mazes should support. */
public interface Maze {

  /**
   * This abstract method needs to be implemented in the class that inherits. It is used to generate
   * the maze based on the specifications given. This method only generates the maze layout.
   *
   * @return the maze in a graph like data structure, with key as a room and its value as its
   *     neighbors
   */
  abstract HashMap<Room, Set<Room>> generate();

  //  /**
  //   * This method is used to set the co-ordinates of the starting point location in the maze.
  // This is
  //   * the location where a player starts their game.
  //   *
  //   * @param x the x co-ordinate of the starting point location in the maze
  //   * @param y the y co-ordinate of the starting point location in the maze
  //   * @throws IllegalArgumentException if the co-ordinate specified is not in the maze layout or
  //   *     invalid values (like negatives).
  //   */
  //  void setStartLoc(int x, int y);
  //
  //  /**
  //   * This method is used to set the co-ordinates of the goal point location in the maze. This is
  // the
  //   * location where a player finishes their game when reached.
  //   *
  //   * @param x the x co-ordinate of the goal point location in the maze
  //   * @param y the y co-ordinate of the goal point location in the maze
  //   * @throws IllegalArgumentException if the co-ordinate specified is not in the maze layout or
  //   *     invalid values (like negatives).
  //   */
  //  void setGoalLoc(int x, int y);

  /**
   * This method is used to generate the objects in the maze. The objects include the characters:
   * player and thieves, and the treasure: gold. The player is created and appears in the starting
   * location of the maze. The thieves appear randomly in the maze, and number of thieves that
   * appear is equal to 10 percent of the number of rooms in the maze. The gold coins appear
   * randomly in the maze, and number of gold coins that appear is equal to 20 percent of the number
   * of rooms in the maze.
   */
  void generateMazeObjects(long seed, int percentBats, int percentPits);

  /**
   * This method is used to list out the legal actions that the player can take in a given room. The
   * legal actions are doors that lead to the neighbor rooms. The actions include {'East', 'West',
   * 'North', 'South'}.
   *
   * @return the legal actions
   */
  Set<String> getLegalActions();

  /**
   * This method tells the player whether they have reached the goal location or not.
   *
   * @return true if the goal is reached, false otherwise
   */
  boolean isGoal();

  /**
   * This method is used to get the current room in which the player is present.
   *
   * @return the current room in which the player is present
   */
  Room getCurrentRoom();

  /**
   * This method is used to move the player to another room in the direction they specified.
   *
   * @throws IllegalArgumentException if the player gives an invalid move (illegal moves)
   */
  boolean movePlayer(String action);

  /**
   * This method is used to move the player to another room in the direction they specified.
   *
   * @throws IllegalArgumentException if the player gives an invalid move (illegal moves)
   */
  boolean moveArrow(String action, int limit);

  /**
   * This method is used to print the list of walls present in the maze.
   *
   * @return the string that consists of the summary of walls present in the maze
   */
  String getWalls();
}

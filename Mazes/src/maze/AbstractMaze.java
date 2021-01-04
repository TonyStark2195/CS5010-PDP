package maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Random;
import java.util.Set;

/**
 * This abstract class contains all the operations that all types of mazes should support. This
 * serves as an encapsulation for the sub maze types and enables ease of code refactoring.
 */
public abstract class AbstractMaze implements Maze {

  protected int numColumns;
  protected int numRows;
  protected int numRemainingWalls;
  protected Character player;

  protected Room currentRoom;
  protected Room startRoom;
  protected Room goalRoom;
  protected Room[][] mazeRooms;
  protected HashMap<Room, Set<Room>> graphMaze;

  protected Set<Set<Room>> wallList;
  protected Set<Set<Room>> savedList;
  protected Set<Set<Room>> disjointSet;

  /**
   * This constructor is used to instantiate a maze object that will be used in the game.
   *
   * @param numRows the number of rows in the maze
   * @param numColumns the number of columns in the maze
   * @param numRemainingWalls the number of remaining walls in the maze
   * @throws IllegalArgumentException if invalid input values are given
   */
  protected AbstractMaze(int numRows, int numColumns, int numRemainingWalls) {

    if (numRows <= 0 & numColumns <= 0 & numRemainingWalls <= 0) {
      throw new IllegalArgumentException("Invalid Input values!");
    } else {
      this.numRows = numRows;
      this.numColumns = numColumns;
      this.numRemainingWalls = numRemainingWalls;
      this.mazeRooms = new Room[this.numRows][this.numColumns];
      wallList = new HashSet<>();
      savedList = new HashSet<>();
      disjointSet = new HashSet<>();
      graphMaze = new HashMap<>();
      player = new Character("Player", "The one who knocks!", 0);
    }
  }

  /**
   * This abstract method implements the modified version of Kruskal's algorithm which serves as a
   * helper method in maze generation.
   *
   * @param numOfWalls the total number of walls present initially in the maze
   */
  public void kruskal(int numOfWalls) {

    List<Set<Room>> wall = new ArrayList<>(this.wallList);
    Collections.shuffle(wall, new Random(7));

    for (int num = 0; num < numOfWalls; num++) {
      List<Room> roomsList = new ArrayList<>(wall.get(0));
      if (find(roomsList.get(0), roomsList.get(1))) {
        this.savedList.add(wall.get(0));
      } else {
        union(roomsList.get(0), roomsList.get(1));
      }
      this.wallList.remove(wall.get(0));
      wall.remove(0);
    }
  }

  /**
   * This method is used to find if two elements are in the same set or not.
   *
   * @param one the room on one side of the wall
   * @param two the room on other side of the wall
   * @return true is both the elements are in the same set, else false
   */
  public boolean find(Room one, Room two) {

    for (Set<Room> obj : this.disjointSet) {
      if (obj.contains(one)) {
        if (obj.contains(two)) {
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method is used to union (combine) two sets into one.
   *
   * @param one the room on one side of the wall
   * @param two the room on other side of the wall
   */
  public void union(Room one, Room two) {

    Set<Room> temp1 = new HashSet<>();
    Set<Room> temp2 = new HashSet<>();

    for (Set<Room> obj : this.disjointSet) {
      if (obj.contains(one)) {
        temp1 = obj;
      }
      if (obj.contains(two)) {
        temp2 = obj;
      }
    }
    this.disjointSet.remove(temp1);
    this.disjointSet.remove(temp2);
    temp1.addAll(temp2);
    this.disjointSet.add(temp1);
  }

  @Override
  public abstract HashMap<Room, Set<Room>> generate();

  @Override
  public void setStartLoc(int x, int y) throws IllegalArgumentException {

    if (x < 0 || y < 0 || x >= this.numRows || y >= this.numColumns) {
      throw new IllegalArgumentException("Illegal Co-ordinates given!");
    } else {
      this.startRoom = mazeRooms[x][y];
      System.out.println(this.startRoom.toString());
    }
  }

  @Override
  public void setGoalLoc(int x, int y) throws IllegalArgumentException {

    if (x < 0 || y < 0 || x >= this.numRows || y >= this.numColumns) {
      throw new IllegalArgumentException("Illegal Co-ordinates given!");
    } else if (x == this.startRoom.getXCoordinate() && y == this.startRoom.getYCoordinate()) {
      throw new IllegalArgumentException("Start and Goal cannot be the same Location!");
    } else {
      this.goalRoom = mazeRooms[x][y];
      System.out.println(this.goalRoom.toString());
    }
  }

  @Override
  public void generateMazeObjects() throws IllegalArgumentException {

    int numGoldRooms = (int) Math.ceil((numRows * numColumns) * 0.2);
    int numThiefRooms = (int) Math.ceil(0.1 * (numRows * numColumns));

    int[] goldX = new Random().ints(0, numRows).distinct().limit(numGoldRooms).toArray();
    int[] goldY = new Random().ints(0, numColumns).distinct().limit(numGoldRooms).toArray();
    int[] goldPoints = new Random().ints(5, 11).limit(numGoldRooms).toArray();
    int[] thiefX = new Random().ints(0, numRows).distinct().limit(numThiefRooms).toArray();
    int[] thiefY = new Random().ints(0, numColumns).distinct().limit(numThiefRooms).toArray();
    int[] thiefHP = new Random().ints(0, 8).limit(numThiefRooms).toArray();

    int i = 0;
    int j = 0;

    while (i < numGoldRooms) {
      if ((goldX[i] != this.startRoom.getXCoordinate()
              || goldY[i] != this.startRoom.getYCoordinate())
          && (goldX[i] != this.goalRoom.getXCoordinate()
              || goldY[i] != this.goalRoom.getYCoordinate())) {
        this.mazeRooms[goldX[i]][goldY[i]].addTreasure(new Treasure("Gold", goldPoints[i]));
        i++;
      } else {
        goldX = new Random().ints(0, numRows).distinct().limit(numGoldRooms).toArray();
        goldY = new Random().ints(0, numColumns).distinct().limit(numGoldRooms).toArray();
      }
    }

    while (j < numThiefRooms) {
      if ((thiefX[j] != this.startRoom.getXCoordinate()
              || thiefY[j] != this.startRoom.getYCoordinate())
          && (thiefX[j] != this.goalRoom.getXCoordinate()
              || thiefY[j] != this.goalRoom.getYCoordinate())) {
        this.mazeRooms[thiefX[j]][thiefY[j]].addCharacter(
            new Character("Thief", "Steals " + thiefHP[j] + " gold coins!", -thiefHP[j]));
        j++;
      } else {
        thiefX = new Random().ints(0, numRows).distinct().limit(numThiefRooms).toArray();
        thiefY = new Random().ints(0, numColumns).distinct().limit(numThiefRooms).toArray();
      }
    }

    this.mazeRooms[this.startRoom.getXCoordinate()][this.startRoom.getYCoordinate()].addCharacter(
        this.player);

    this.currentRoom = this.startRoom;
  }

  @Override
  public void movePlayer(String action) {
    Character[] thief;
    Treasure[] nextTreasure;

    Set<String> legalActions = this.currentRoom.getLegalActions();

    if (legalActions.contains(action)) {
      this.currentRoom.removeCharacter(this.player);
      this.currentRoom = this.currentRoom.getNeighbors().get(action);
      nextTreasure = this.currentRoom.getTreasures();
      if (nextTreasure.length > 0) {
        player.setGoldCoins(nextTreasure[0].getValue());
        this.currentRoom.removeTreasure(nextTreasure[0]);
      }
      thief = this.currentRoom.getCharacters();
      if (thief.length > 0) {
        this.player.setGoldCoins(thief[0].getGoldCoins());
        this.currentRoom.removeCharacter(thief[0]);
      }
      this.currentRoom.addCharacter(this.player);
    }
  }

  @Override
  public boolean isGoal() {
    return (this.currentRoom.getXCoordinate() == this.goalRoom.getXCoordinate()
        && this.currentRoom.getYCoordinate() == this.goalRoom.getYCoordinate());
  }

  @Override
  public Set<String> getLegalActions() {
    return this.currentRoom.getLegalActions();
  }

  @Override
  public Room getCurrentRoom() {
    return this.currentRoom;
  }

  @Override
  public String getWalls() {
    StringBuilder sb = new StringBuilder();
    sb.append("{\n");
    for (Set<Room> roomSet : this.savedList) {
      List<Room> roomArr = new ArrayList<>(roomSet);
      sb.append(
          "{" + roomArr.get(0).getDescription() + " | " + roomArr.get(1).getDescription() + "}\n");
    }
    sb.append(" }\n");
    return sb.toString();
  }

  /**
   * This method is used to override the default toString method and this is used to display the
   * summary of the stats of the maze.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (int x = 0; x < numRows; x++) {
      for (int y = 0; y < numColumns; y++) {
        sb.append(mazeRooms[x][y].toString() + '\n');
      }
    }
    return sb.toString();
  }
}

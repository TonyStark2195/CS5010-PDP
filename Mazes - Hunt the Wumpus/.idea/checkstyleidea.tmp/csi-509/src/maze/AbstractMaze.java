package maze;

import java.util.ArrayList;
import java.util.Collections;
import java.util.HashMap;
import java.util.HashSet;
import java.util.LinkedList;
import java.util.List;
import java.util.Queue;
import java.util.Random;
import java.util.Set;
import java.util.Stack;

/**
 * This abstract class contains all the operations that all types of mazes should support. This
 * serves as an encapsulation for the sub maze types and enables ease of code refactoring.
 */
public abstract class AbstractMaze implements Maze {

  protected int numColumns;
  protected int numRows;
  protected int numRemainingWalls;
  protected Character player;
  protected Character wumpus;

  protected Room currentRoom;
  protected Room startRoom;
  protected Room goalRoom;
  protected Room[][] mazeRooms;
  protected HashMap<Room, Set<Room>> graphMaze;

  protected Set<Set<Room>> wallList;
  protected Set<Set<Room>> savedList;
  protected Set<Set<Room>> disjointSet;

  protected List<List<Integer>> totalListCaves = new ArrayList<>();

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
      this.mazeRooms = new Room[this.numColumns][this.numRows];
      wallList = new HashSet<>();
      savedList = new HashSet<>();
      disjointSet = new HashSet<>();
      graphMaze = new HashMap<>();
      player = new Character(CharacterType.PLAYER, "The one who knocks!", 0);
      wumpus = new Character(CharacterType.WUMPUS, "The monster that eats!", 0);
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
  public void generateMazeObjects(long seed, int percentBats, int percentPits)
      throws IllegalArgumentException {

    int numCaves = 0;
    List<List<Integer>> listCaves = new ArrayList<>();

    for (int x = 0; x < this.numColumns; x++) {
      for (int y = 0; y < this.numRows; y++) {
        List<Integer> tempSet = new ArrayList<>();
        tempSet.add(x);
        tempSet.add(y);
        if (this.mazeRooms[x][y].getRoomType() == RoomType.CAVE) {
          numCaves += 1;
          listCaves.add(tempSet);
          totalListCaves.add(tempSet);
        }
      }
    }

    int[] playerWumpusIndex =
        new Random(seed).ints(0, listCaves.size()).distinct().limit(2).toArray();

    List<Integer> playerLoc = listCaves.get(playerWumpusIndex[0]);
    List<Integer> wumpusLoc = listCaves.get(playerWumpusIndex[1]);

    this.startRoom = mazeRooms[playerLoc.get(0)][playerLoc.get(1)];

    listCaves.remove(playerLoc);
    listCaves.remove(wumpusLoc);

    int numSuperBats = (int) Math.ceil(percentBats * numCaves / 100.0);
    int numPits = (int) Math.ceil(percentPits * numCaves / 100.0);

    int[] bats =
        new Random(seed).ints(0, listCaves.size()).distinct().limit(numSuperBats).toArray();

    int[] pits = new Random(seed).ints(0, listCaves.size()).distinct().limit(numPits).toArray();

    for (int batInd : bats) {
      this.mazeRooms[listCaves.get(batInd).get(0)][listCaves.get(batInd).get(1)].addCharacter(
          new Character(CharacterType.SUPER_BAT, "Teleports", 0));
    }

    for (int pitInd : pits) {
      this.mazeRooms[listCaves.get(pitInd).get(0)][listCaves.get(pitInd).get(1)].addCharacter(
          new Character(CharacterType.PIT, "Bottomless Pit", 0));
      bfs(
          this.mazeRooms[listCaves.get(pitInd).get(0)][listCaves.get(pitInd).get(1)],
          this.mazeRooms[listCaves.get(pitInd).get(0)][listCaves.get(pitInd).get(1)]
              .getNeighbors()
              .values()
              .size(),
          "draft");
    }

    this.mazeRooms[wumpusLoc.get(0)][wumpusLoc.get(1)].addCharacter(this.wumpus);

    bfs(
        this.mazeRooms[wumpusLoc.get(0)][wumpusLoc.get(1)],
        this.mazeRooms[wumpusLoc.get(0)][wumpusLoc.get(1)].getNeighbors().values().size(),
        "stench");

    this.mazeRooms[playerLoc.get(0)][playerLoc.get(1)].addCharacter(this.player);

    this.currentRoom = this.startRoom;
  }

  /**
   * This method is used to perform Breadth-First Search on the environment.
   *
   * @param currentRoom the current room from where the BFS needs to be done
   * @param stenchDraft the string to input stench for wumpus and draft for pit
   * @param limit the depth of the BFS
   */
  protected void bfs(Room currentRoom, int limit, String stenchDraft) {
    Queue<Room> queueRoom;
    queueRoom = new LinkedList<>(currentRoom.getNeighbors().values());

    List<Room> visitedRooms = new ArrayList<>();
    visitedRooms.add(currentRoom);

    int count = 0;
    Room temp;

    while (!queueRoom.isEmpty() && count < limit) {
      temp = queueRoom.poll();
      if (stenchDraft.equalsIgnoreCase("stench")) {
        temp.setStench(true);
      } else if (stenchDraft.equalsIgnoreCase("draft")) {
        temp.setDraft(true);
      }
      if (!visitedRooms.contains(temp)) {
        if (temp.getRoomType() == RoomType.CAVE) {
          visitedRooms.add(temp);
          count += 1;
        } else {
          queueRoom.addAll(temp.getNeighbors().values());
        }
      }
    }
  }

  /**
   * This method is used to perform Depth-First Search on the environment.
   *
   * @param currentRoom the current room from where the DFS needs to be done
   * @param action the action to be taken
   * @param limit the depth of the DFS
   * @return the destination room (cave) where an object need to be
   */
  protected Room dfsObject(Room currentRoom, String action, int limit) {
    Stack<Room> stackRoom = new Stack<>();
    stackRoom.push(currentRoom.getNeighbors().get(action));

    List<Room> visitedRooms = new ArrayList<>();
    visitedRooms.add(currentRoom);

    int count = 0;
    Room temp;

    while (!stackRoom.isEmpty() && count < limit) {
      temp = stackRoom.pop();
      if (!visitedRooms.contains(temp)) {
        visitedRooms.add(temp);
        if (temp.getRoomType() == RoomType.CAVE) {
          currentRoom = temp;
          count += 1;
        } else {
          stackRoom.addAll(temp.getNeighbors().values());
        }
      }
    }
    return currentRoom;
  }

  /**
   * This method is used to perform Depth-First Search for an arrow on the environment.
   *
   * @param currentRoom the current room from where the DFS needs to be done
   * @param action the action to be taken
   * @param limit the depth of the DFS
   * @return the destination room (cave) where an object need to be
   */
  protected Room dfsArrow(Room currentRoom, String action, int limit) {
    Stack<Room> stackRoom = new Stack<>();
    stackRoom.push(currentRoom.getNeighbors().get(action));

    List<Room> visitedRooms = new ArrayList<>();
    visitedRooms.add(currentRoom);

    int count = 0;
    Room temp;

    while (!stackRoom.isEmpty() && count < limit) {
      temp = stackRoom.pop();
      if (!visitedRooms.contains(temp)) {
        visitedRooms.add(temp);
        if (temp.getRoomType() == RoomType.CAVE) {
          stackRoom.push(temp.getNeighbors().get(action));
          currentRoom = temp;
          count += 1;
        } else {
          stackRoom.addAll(temp.getNeighbors().values());
        }
      }
    }
    return currentRoom;
  }

  /**
   * This method is used to move the player given the destination room.
   *
   * @param destination the destination room
   * @return true if the game is over, false otherwise
   */
  protected boolean movePlayerToRoom(Room destination) {

    int seed = 0;

    this.currentRoom.removeCharacter(this.player);
    this.currentRoom = destination;
    this.currentRoom.addCharacter(this.player);

    Character[] characters;
    characters = this.currentRoom.getCharacters();

    if (characters.length > 0) {
      for (Character ch : characters) {
        if (ch.getName() == CharacterType.SUPER_BAT) {
          Random random = new Random(seed);
          if (random.nextDouble() < 0.5) {
            System.out.println("Super Bat missed to pick you up!");
          } else {
            System.out.println("Super Bat carries you to...");
            int[] newIndex =
                new Random(seed).ints(0, totalListCaves.size()).distinct().limit(1).toArray();

            List<Integer> destLoc = totalListCaves.get(newIndex[0]);

            destination = mazeRooms[destLoc.get(0)][destLoc.get(1)];
            System.out.println("Teleported to: " + destination.toString());
            return movePlayerToRoom(destination);
          }
        } else if (ch.getName() == CharacterType.PIT) {
          System.out.println("Player fell into a bottomless pit...Game Over!");
          return true;
        } else if (ch.getName() == CharacterType.WUMPUS) {
          System.out.println("Player got eaten by the Wumpus...Game Over!");
          return true;
        }
      }
    }
    return false;
  }

  /**
   * This method is used to move the arrow given the destination room.
   *
   * @param destination the destination room
   * @return true if the game is over, false otherwise
   */
  protected boolean moveArrowToRoom(Room destination) {

    int seed = 0;

    this.currentRoom.removeCharacter(this.player);
    this.currentRoom = destination;
    this.currentRoom.addCharacter(this.player);

    Character[] characters;
    characters = this.currentRoom.getCharacters();

    if (characters.length > 0) {
      for (Character ch : characters) {
        if (ch.getName() == CharacterType.SUPER_BAT) {
          Random random = new Random(seed);
          if (random.nextDouble() < 0.5) {
            System.out.println("Super Bat missed to pick you up!");
          } else {
            System.out.println("Super Bat carries you...");
            int[] newIndex =
                new Random(seed).ints(0, totalListCaves.size()).distinct().limit(1).toArray();

            List<Integer> destLoc = totalListCaves.get(newIndex[0]);

            destination = mazeRooms[destLoc.get(0)][destLoc.get(1)];
            System.out.println("Teleported to: " + destination.toString());
            return movePlayerToRoom(destination);
          }
        } else if (ch.getName() == CharacterType.PIT) {
          System.out.println("Player fell into a bottomless pit...Game Over!");
          return true;
        } else if (ch.getName() == CharacterType.WUMPUS) {
          System.out.println("Player got eaten by the Wumpus...Game Over!");
          return true;
        }
      }
    }
    return false;
  }

  @Override
  public boolean movePlayer(String action) {
    Room destination;

    Set<String> legalActions = this.currentRoom.getLegalActions();

    if (legalActions.contains(action)) {
      destination = dfsObject(this.currentRoom, action, 1);
      return movePlayerToRoom(destination);
    }
    return false;
  }

  @Override
  public boolean moveArrow(String action, int limit) {
    Room destination;

    Set<String> legalActions = this.currentRoom.getLegalActions();

    if (legalActions.contains(action)) {
      destination = dfsArrow(this.currentRoom, action, limit);
      Character[] characters = destination.getCharacters();
      for (Character ch : characters) {
        if (ch.getName() == CharacterType.WUMPUS) {
          System.out.println("Huray! You killed the Wumpus.");
          return true;
        }
      }
      System.out.println("Arrow missed the Wumpus!");
      Character[] players = this.currentRoom.getCharacters();
      for (Character pl : players) {
        if (pl.getName() == CharacterType.PLAYER) {
          pl.updateWeapons();
        }
      }
      return false;
    }
    return false;
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
      sb.append("{")
          .append(roomArr.get(0).getDescription())
          .append(" | ")
          .append(roomArr.get(1).getDescription())
          .append("}\n");
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
    for (int x = 0; x < numColumns; x++) {
      for (int y = 0; y < numRows; y++) {
        sb.append(mazeRooms[x][y].toString()).append('\n');
      }
    }
    return sb.toString();
  }
}

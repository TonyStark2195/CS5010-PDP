import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import maze.Character;
import maze.CharacterType;
import maze.Room;
import maze.Treasure;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests related to the Room class. Here any functionality related
 * to the Room class are tested.
 */
public class RoomTest {

  Room room;
  Room roomNeighbor;
  Treasure gold;
  Character player;
  Character thief;

  /** This is where the objects of the Room class that needs to be tested is instantiated. */
  @Before
  public void setUp() throws Exception {
    room = new Room("Room 1", 0, 0);
    roomNeighbor = new Room("Room 2", 0, 1);
    gold = new Treasure("Gold", 10);
    player = new Character(CharacterType.PLAYER, "", 0);
    thief = new Character(CharacterType.PLAYER, "Robber", -3);
  }

  /** This is a JUnit test to see if the addCharacter method is working correctly. */
  @Test
  public void testAddCharacter() {
    room.addCharacter(player);
    assertEquals(
        "Room 1: { Characters: Player (Current Gold = 0); Treasures: None }", room.toString());
  }

  /** This is a JUnit test to see if the removeCharacter method is working correctly. */
  @Test
  public void testRemoveCharacter() {
    room.addCharacter(player);
    room.removeCharacter(player);
    assertEquals("Room 1: { Characters: None; Treasures: None }", room.toString());
  }

  /** This is a JUnit test to see if the getCharacter method is working correctly. */
  @Test
  public void testGetCharacters() {
    room.addCharacter(player);
    room.addCharacter(thief);
    assertEquals(
        "Room 1: { Characters: Player (Current Gold = 0);Thief (Current Gold = -3); "
            + "Treasures: None }",
        room.toString());
  }

  /** This is a JUnit test to see if the addTreasure method is working correctly. */
  @Test
  public void testAddTreasure() {
    room.addTreasure(gold);
    assertEquals("Room 1: { Characters: None; Treasures: Gold (value = 10) }", room.toString());
  }

  /** This is a JUnit test to see if the removeTreasure method is working correctly. */
  @Test
  public void testRemoveTreasure() {
    room.addTreasure(gold);
    room.removeTreasure(gold);
    assertEquals("Room 1: { Characters: None; " + "Treasures: None }", room.toString());
  }

  /** This is a JUnit test to see if the getTreasures method is working correctly. */
  @Test
  public void testGetTreasures() {
    room.addTreasure(gold);
    assertEquals(gold, room.getTreasures()[0]);
  }

  /** This is a JUnit test to see if the neighbors are correctly set. */
  @Test
  public void testGetSetNeighbors() {
    HashMap<String, Room> hashRoom = new HashMap<String, Room>();
    hashRoom.put("Right", roomNeighbor);
    room.setNeighbors(hashRoom);
    assertEquals(hashRoom, room.getNeighbors());
  }

  /** This is a JUnit test to see if the legal actions are correctly set. */
  @Test
  public void testGetSetLegalActions() {
    Set<String> actions = new HashSet<>();
    actions.add("East");
    actions.add("North");
    room.setLegalActions(actions);
    assertEquals(actions, room.getLegalActions());
  }

  /** This is a JUnit test to see if the getXCoordinate method is working correctly. */
  @Test
  public void getXCoordinate() {
    assertEquals(0, room.getXCoordinate());
  }

  /** This is a JUnit test to see if the getYCoordinate method is working correctly. */
  @Test
  public void getYCoordinate() {
    assertEquals(1, roomNeighbor.getYCoordinate());
  }
}

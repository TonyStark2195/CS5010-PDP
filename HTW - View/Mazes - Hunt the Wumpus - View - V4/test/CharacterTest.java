import org.junit.Before;
import org.junit.Test;

import maze.Character;
import maze.CharacterType;
import maze.Treasure;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests related to the Character class. Here any functionality
 * related to the Character class are tested.
 */
public class CharacterTest {
  Character player;
  Character thief;
  Treasure gold;

  /**
   * This is where the objects of the Character and Treasure class that needs to be tested is
   * instantiated.
   */
  @Before
  public void setUp() throws Exception {

    player = new Character(CharacterType.PLAYER, "", 0);
    thief = new Character(CharacterType.PLAYER, "Robber", -5);
    gold = new Treasure("Gold", 10);
  }

  /** This is a JUnit test to see if the character object has been correctly instantiated. */
  @Test
  public void testGetName() {
    assertEquals("Player", player.getName());
  }

  /** This is a JUnit test to see if the character object has been correctly instantiated. */
  @Test
  public void testGetDescription() {
    assertEquals("Robber", thief.getDescription());
  }

  /**
   * This is a JUnit test to see if the character has correctly having the gold coins after a series
   * of picking up and robbing incidents.
   */
  @Test
  public void testGetGoldCoins() {
    player.setGoldCoins(gold.getValue());
    player.setGoldCoins(thief.getGoldCoins());
    assertEquals(5, player.getGoldCoins());
  }

  /** This is a JUnit test to see if the gold value is correctly been set to the character. */
  @Test
  public void testSetGoldCoins() {
    player.setGoldCoins(gold.getValue());
    assertEquals(10, player.getGoldCoins());
  }

  /** This is a JUnit test to see if the toString method is working as expected. */
  @Test
  public void testToString() {
    assertEquals("Player (Current Gold = 0)", player.toString());
  }
}

import org.junit.Before;
import org.junit.Test;

import maze.Treasure;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests related to the Treasure class. Here any functionality
 * related to the treasure class are tested.
 */
public class TreasureTest {

  Treasure t;

  /** This is where the objects of the treasure class that needs to be tested is instantiated. */
  @Before
  public void setUp() throws Exception {
    t = new Treasure("Gold", 5);
  }

  /** This is a JUnit test to see if the treasure object has been correctly instantiated. */
  @Test
  public void testGetDescription() {
    assertEquals("Gold", t.getDescription());
  }

  /** This is a JUnit test to see if the treasure object has the correct value. */
  @Test
  public void testGetValue() {
    assertEquals(5, t.getValue());
  }

  /** This is a JUnit test to see if the toString method is working as expected. */
  @Test
  public void testToString() {
    assertEquals("Gold (value = 5)", t.toString());
  }
}

import org.junit.Before;
import org.junit.Test;

import roleplaying.HitPoint;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests related to the Battle class. Here any functionality
 * related to the game are tested.
 */
public class HitPointTest {

  private HitPoint hitPoint1;
  private HitPoint hitPoint2;

  /** This is where the object of the HitPoint class that needs to be tested is instantiated. */
  @Before
  public void setUp() throws Exception {
    hitPoint1 = new HitPoint(10, 3);
    hitPoint2 = new HitPoint(5, 8);
  }

  /** This JUnit test is used to test if the getAttackPoints is correctly working. */
  @Test
  public void testGetAttackPoints() {
    assertEquals(10, hitPoint1.getAttackPoints());
    assertEquals(5, hitPoint2.getAttackPoints());
  }

  /** This JUnit test is used to test if the getDefensePoints is correctly working. */
  @Test
  public void testGetDefensePoints() {
    assertEquals(3, hitPoint1.getDefensePoints());
    assertEquals(8, hitPoint2.getDefensePoints());
  }

  /**
   * This JUnit test is used to test if 2 HitPoint objects are correctly combined to form a single
   * HitPoint object.
   */
  @Test
  public void testAddHitPoints() {
    assertEquals(
        "Attack Points: 15, Defense Points: 11", hitPoint1.addHitPoints(hitPoint2).toString());
  }
}

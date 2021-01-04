import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.Collections;

import roleplaying.FootGear;
import roleplaying.Gear;
import roleplaying.GearType;
import roleplaying.HandGear;
import roleplaying.HeadGear;
import roleplaying.HitPoint;

import static org.junit.Assert.assertEquals;
import static org.junit.Assert.assertTrue;

/**
 * This class contains all the unit tests related to the Gear interface and its subclasses. Here any
 * functionality related to the gears are tested.
 */
public class GearTest {

  private Gear headGear;
  private Gear handGear;
  private Gear footGear;

  /** This is where the object of the Gear class that needs to be tested is instantiated. */
  @Before
  public void setUp() {
    handGear = new HandGear(new HitPoint(5, 2), "Repulsor Gun");
    headGear = new HeadGear(new HitPoint(0, 5), "Stark Helmet");
    footGear = new FootGear(new HitPoint(6, 4), "Battering Rams");
  }

  /** This JUnit test is used to test if the gear name is correctly assigned. */
  @Test
  public void testGetGearName() {
    assertEquals("Repulsor Gun", handGear.getGearName());
    assertEquals("Stark Helmet", headGear.getGearName());
    assertEquals("Battering Rams", footGear.getGearName());
  }

  /** This JUnit test is used to test if the gear type is correctly assigned. */
  @Test
  public void testGetGearType() {
    assertEquals(GearType.HAND, handGear.getGearType());
    assertEquals(GearType.HEAD, headGear.getGearType());
    assertEquals(GearType.FOOT, footGear.getGearType());
  }

  /**
   * This JUnit test is used to test if the gear of same types are rightly combined to form a new
   * compound gear.
   */
  @Test
  public void testCombineGear() throws Exception {
    Gear combinedHandGear =
        handGear.combineGear(new HandGear(new HitPoint(4, 0), "Heat Seeking Missiles"));
    assertEquals("Repulsor, Heat Seeking Missiles", combinedHandGear.getGearName());

    Gear combinedHeadGear = headGear.combineGear(new HeadGear(new HitPoint(0, 1), "Stark Glasses"));
    assertEquals("Stark, Stark Glasses", combinedHeadGear.getGearName());

    Gear combinedFootGear = footGear.combineGear(new FootGear(new HitPoint(8, 0), "Repulsor Gun"));
    assertEquals("Battering, Repulsor Gun", combinedFootGear.getGearName());
  }

  /**
   * This JUnit test is used to test if the hand gear is combined with a gear of different type and
   * throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCombineHandGear() throws Exception {
    handGear.combineGear(new HeadGear(new HitPoint(0, 1), "Stark Glasses"));
  }

  /**
   * This JUnit test is used to test if the head gear is combined with a gear of different type and
   * throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCombineHeadGear() throws Exception {
    headGear.combineGear(new FootGear(new HitPoint(8, 0), "Repulsor Gun"));
  }

  /**
   * This JUnit test is used to test if the foot gear is combined with a gear of different type and
   * throws an IllegalArgumentException.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidCombineFootGear() throws Exception {
    footGear.combineGear(new HandGear(new HitPoint(4, 0), "Heat Seeking Missiles"));
  }

  /**
   * This JUnit test is used to test if the effective Hit points of the combined gear is the sum of
   * HPs of the individual gears.
   */
  @Test
  public void testGetEffectiveHP() throws Exception {
    Gear combinedHandGear =
        handGear.combineGear(new HandGear(new HitPoint(4, 0), "Heat Seeking Missiles"));
    assertEquals(
        "Attack Points: 9, Defense Points: 2", combinedHandGear.getEffectiveHP().toString());

    Gear combinedHeadGear = headGear.combineGear(new HeadGear(new HitPoint(0, 1), "Stark Glasses"));
    assertEquals(
        "Attack Points: 0, Defense Points: 6", combinedHeadGear.getEffectiveHP().toString());

    Gear combinedFootGear = footGear.combineGear(new FootGear(new HitPoint(8, 0), "Repulsor Gun"));
    assertEquals(
        "Attack Points: 14, Defense Points: 4", combinedFootGear.getEffectiveHP().toString());
  }

  /**
   * This JUnit test is used to test if the name of the combined gear is the combination of that of
   * the individual gears. The logic is the new name is the adjective from one item and the full
   * name from the other.
   */
  @Test
  public void testCombineGearName() throws Exception {
    Gear combinedHandGear =
        handGear.combineGear(new HandGear(new HitPoint(4, 0), "Heat Seeking Missiles"));
    assertEquals("Repulsor, Heat Seeking Missiles", combinedHandGear.getGearName());

    Gear combinedHeadGear = headGear.combineGear(new HeadGear(new HitPoint(0, 1), "Stark Glasses"));
    assertEquals("Stark, Stark Glasses", combinedHeadGear.getGearName());

    Gear combinedFootGear = footGear.combineGear(new FootGear(new HitPoint(8, 0), "Repulsor Gun"));
    assertEquals("Battering, Repulsor Gun", combinedFootGear.getGearName());
  }

  /**
   * This JUnit test is used to test if the combined gears actually has the status whether they are
   * of combined type or not (boolean).
   */
  @Test
  public void testIsCombined() throws Exception {
    Gear combinedHandGear =
        handGear.combineGear(new HandGear(new HitPoint(4, 0), "Heat Seeking Missiles"));
    assertTrue(combinedHandGear.isCombined());

    Gear combinedHeadGear = headGear.combineGear(new HeadGear(new HitPoint(0, 1), "Stark Glasses"));
    assertTrue(combinedHeadGear.isCombined());

    Gear combinedFootGear = footGear.combineGear(new FootGear(new HitPoint(8, 0), "Repulsor Gun"));
    assertTrue(combinedFootGear.isCombined());
  }

  /**
   * This JUnit test is used to check the order of precedence of the gear classes. This is for
   * sorting them in the correct order.
   */
  @Test
  public void testGetClassOrder() {
    assertEquals(2, handGear.getClassOrder());
    assertEquals(1, headGear.getClassOrder());
    assertEquals(3, footGear.getClassOrder());
  }

  /**
   * This JUnit test is used to check if the gears are correctly sorted. First based on the type of
   * class, then when the class is same, gears are arranged in the decreasing order of attack
   * points.
   */
  @Test
  public void testGearSort() {

    ArrayList<Gear> gearList = new ArrayList<>();
    gearList.add(new HandGear(new HitPoint(5, 2), "Repulsor Gun"));
    gearList.add(new HeadGear(new HitPoint(0, 5), "Stark Helmet"));
    gearList.add(new FootGear(new HitPoint(3, 4), "Flight Thrusters"));
    gearList.add(new HandGear(new HitPoint(4, 0), "Heat Seeking Missiles"));
    gearList.add(new FootGear(new HitPoint(6, 3), "Foot Clamps"));
    gearList.add(new HandGear(new HitPoint(7, 1), "Nano-Handblade"));
    gearList.add(new FootGear(new HitPoint(6, 4), "Battering Rams"));
    gearList.add(new HandGear(new HitPoint(1, 7), "Energy Shield"));
    gearList.add(new HeadGear(new HitPoint(0, 1), "Stark Glasses"));
    gearList.add(new FootGear(new HitPoint(8, 0), "Repulsor Gun"));

    StringBuilder before = new StringBuilder();
    for (int i = 0; i < gearList.size(); i++) {
      before.append(i + 1).append(". ").append(gearList.get(i).toString()).append("\n");
    }

    String expectedBefore =
        "1. Gear Name: Repulsor Gun -- Defense Strength: 2; "
            + "Attack Strength: 5; Gear Type: HAND\n"
            + "2. Gear Name: Stark Helmet -- Defense Strength: 5; "
            + "Attack Strength: 0; Gear Type: HEAD\n"
            + "3. Gear Name: Flight Thrusters -- Defense Strength: 4; "
            + "Attack Strength: 3; Gear Type: FOOT\n"
            + "4. Gear Name: Heat Seeking Missiles -- Defense Strength: 0; "
            + "Attack Strength: 4; Gear Type: HAND\n"
            + "5. Gear Name: Foot Clamps -- Defense Strength: 3; "
            + "Attack Strength: 6; Gear Type: FOOT\n"
            + "6. Gear Name: Nano-Handblade -- Defense Strength: 1; "
            + "Attack Strength: 7; Gear Type: HAND\n"
            + "7. Gear Name: Battering Rams -- Defense Strength: 4; "
            + "Attack Strength: 6; Gear Type: FOOT\n"
            + "8. Gear Name: Energy Shield -- Defense Strength: 7; "
            + "Attack Strength: 1; Gear Type: HAND\n"
            + "9. Gear Name: Stark Glasses -- Defense Strength: 1; "
            + "Attack Strength: 0; Gear Type: HEAD\n"
            + "10. Gear Name: Repulsor Gun -- Defense Strength: 0; "
            + "Attack Strength: 8; Gear Type: FOOT\n";

    Collections.sort(gearList);

    assertEquals(expectedBefore, before.toString());

    StringBuilder after = new StringBuilder();
    for (int i = 0; i < gearList.size(); i++) {
      after.append(i + 1).append(". ").append(gearList.get(i).toString()).append("\n");
    }

    String expectedAfter =
        "1. Gear Name: Stark Helmet -- Defense Strength: 5; "
            + "Attack Strength: 0; Gear Type: HEAD\n"
            + "2. Gear Name: Stark Glasses -- Defense Strength: 1; "
            + "Attack Strength: 0; Gear Type: HEAD\n"
            + "3. Gear Name: Nano-Handblade -- Defense Strength: 1; "
            + "Attack Strength: 7; Gear Type: HAND\n"
            + "4. Gear Name: Repulsor Gun -- Defense Strength: 2; "
            + "Attack Strength: 5; Gear Type: HAND\n"
            + "5. Gear Name: Heat Seeking Missiles -- Defense Strength: 0; "
            + "Attack Strength: 4; Gear Type: HAND\n"
            + "6. Gear Name: Energy Shield -- Defense Strength: 7; "
            + "Attack Strength: 1; Gear Type: HAND\n"
            + "7. Gear Name: Repulsor Gun -- Defense Strength: 0; "
            + "Attack Strength: 8; Gear Type: FOOT\n"
            + "8. Gear Name: Foot Clamps -- Defense Strength: 3; "
            + "Attack Strength: 6; Gear Type: FOOT\n"
            + "9. Gear Name: Battering Rams -- Defense Strength: 4; "
            + "Attack Strength: 6; Gear Type: FOOT\n"
            + "10. Gear Name: Flight Thrusters -- Defense Strength: 4; "
            + "Attack Strength: 3; Gear Type: FOOT\n";

    assertEquals(expectedAfter, after.toString());
  }
}

import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;

import roleplaying.Character;
import roleplaying.CharacterType;
import roleplaying.FootGear;
import roleplaying.Gear;
import roleplaying.HandGear;
import roleplaying.HeadGear;
import roleplaying.HitPoint;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests related to the character class. Here any functionality
 * related to the characters are tested.
 */
public class CharacterTest {

  private Character char1;
  private Character char2;
  private ArrayList<Gear> gearList1;

  /**
   * This is where the object of the character class that needs to be tested is instantiated. A
   * character is defined by following properties: 1) Character Name, 2) Character Type, 3)
   * Character's Hit points.
   */
  @Before
  public void setUp() throws Exception {
    char1 = new Character(new HitPoint(9, 8), CharacterType.TECH, "Tony Stark");
    char2 = new Character(new HitPoint(13, 10), CharacterType.SUPER_BEING, "Thanos");

    gearList1 = new ArrayList<>();
    gearList1.add(new HandGear(new HitPoint(5, 2), "Repulsor Gun"));
    gearList1.add(new HeadGear(new HitPoint(0, 5), "Stark Helmet"));
    gearList1.add(new FootGear(new HitPoint(3, 4), "Flight Thrusters"));
    gearList1.add(new HandGear(new HitPoint(4, 0), "Heat Seeking Missiles"));
    gearList1.add(new FootGear(new HitPoint(6, 3), "Foot Clamps"));
    gearList1.add(new HandGear(new HitPoint(7, 1), "Nano-Handblade"));
    gearList1.add(new FootGear(new HitPoint(6, 4), "Battering Rams"));
    gearList1.add(new HandGear(new HitPoint(1, 7), "Energy Shield"));
    gearList1.add(new HeadGear(new HitPoint(0, 1), "Stark Glasses"));
    gearList1.add(new FootGear(new HitPoint(8, 0), "Repulsor Gun"));

    char1.equipGears(gearList1);
  }

  /** This JUnit test is used to test if the character name is as expected. */
  @Test
  public void testGetCharName() {
    assertEquals("Tony Stark", char1.getCharName());
  }

  /** This JUnit test is used to test if the character type is as expected. */
  @Test
  public void testGetCharType() {
    assertEquals(CharacterType.TECH, char1.getCharType());
  }

  /** This JUnit test is used to test if the characters are correctly equipped with the gears. */
  @Test
  public void testGetGearList() throws Exception {
    char1.equipGears(gearList1);
    String expected =
        "[Gear Name: Stark, Stark Glasses -- Defense Strength: 6; "
            + "Attack Strength: 0; Gear Type: HEAD, Gear Name: Nano-Handblade, "
            + "Repulsor Gun -- Defense Strength: 3; Attack Strength: 12; "
            + "Gear Type: HAND, Gear Name: Heat, Energy Shield -- Defense Strength: 7; "
            + "Attack Strength: 5; Gear Type: HAND, Gear Name: Repulsor, "
            + "Foot Clamps -- Defense Strength: 3; Attack Strength: 14; "
            + "Gear Type: FOOT, Gear Name: Battering, Flight Thrusters -- Defense Strength: 8; "
            + "Attack Strength: 9; Gear Type: FOOT]";
    assertEquals(expected, char1.getGearList().toString());
  }

  /**
   * This JUnit test is used to test if the effective attack points after the character has equipped
   * with all the necessary gears is as expected.
   */
  @Test
  public void testGetEffectiveAttack() {
    System.out.println(char1.toString());
    assertEquals(49, char1.getEffectiveAttack());
  }

  /**
   * This JUnit test is used to test if the effective defense points after the character has
   * equipped with all the necessary gears is as expected.
   */
  @Test
  public void testGetEffectiveDefense() {
    System.out.println(char1.toString());
    assertEquals(35, char1.getEffectiveDefense());
  }

  /** This JUnit test is used to test if a gear is successfully added to a character or not. */
  @Test
  public void testAddGearToChar() throws Exception {
    char2.addGearToChar(new HandGear(new HitPoint(10, 10), "Infinity Gauntlet"));
    assertEquals(1, char2.getGearList().size());
  }

  /**
   * This JUnit test is used to test if an IllegalStateException is thrown if more gear than the
   * capacity is added to the character.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidAddGearToChar() throws Exception {
    char1.addGearToChar(new HandGear(new HitPoint(10, 10), "Infinity Gauntlet"));
  }

  /**
   * This JUnit test is used to test if the method replaceGear is working as expected. This method
   * is used to take an existing uncombined gear from the character's equipped gear list and combine
   * it with the gear that is passed to it (it should be of same type and uncombined). The combined
   * gear will take the place of the gear which was originally present.
   */
  @Test
  public void testReplaceGear() throws Exception {
    char2.addGearToChar(new HandGear(new HitPoint(10, 10), "Infinity Gauntlet"));
    Gear newGear = new HandGear(new HitPoint(7, 0), "Power Stone");
    char2.replaceGear(newGear, 0);
    String expected =
        "[Gear Name: Infinity, Power Stone -- Defense Strength: 10; "
            + "Attack Strength: 17; Gear Type: HAND]";
    assertEquals(expected, char2.getGearList().toString());
  }

  /**
   * This JUnit test is used to test if the method replaceGear is throwing an
   * IllegalArgumentException when an incorrect index in the character's gear list is given to
   * replace.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidReplaceGear1() throws Exception {
    char2.addGearToChar(new HandGear(new HitPoint(10, 10), "Infinity Gauntlet"));
    Gear newGear = new HandGear(new HitPoint(7, 0), "Power Stone");
    char2.replaceGear(newGear, 3);
  }

  /**
   * This JUnit test is used to test if the method replaceGear is throwing an
   * IllegalArgumentException when an incorrect gear Type (not matching with the one present in the
   * given location) is given to replace.
   */
  @Test(expected = IllegalArgumentException.class)
  public void testInvalidReplaceGear2() throws Exception {
    char2.addGearToChar(new HandGear(new HitPoint(10, 10), "Infinity Gauntlet"));
    Gear newGear = new HeadGear(new HitPoint(0, 6), "Asgardian Helmet");
    char2.replaceGear(newGear, 0);
  }

  /**
   * This JUnit test is used to test if the character has equipped with all the gears possible from
   * a list of gears given to it.
   */
  @Test
  public void testEquipGears() throws Exception {
    ArrayList<Gear> gearList2 = new ArrayList<>();
    gearList2.add(new HandGear(new HitPoint(10, 10), "Infinity Gauntlet"));
    gearList2.add(new HeadGear(new HitPoint(0, 6), "Asgardian Helmet"));
    gearList2.add(new FootGear(new HitPoint(4, 2), "Battle Stomper"));
    gearList2.add(new HandGear(new HitPoint(3, 3), "Loki's Sceptre"));
    gearList2.add(new FootGear(new HitPoint(2, 6), "SHIELD's portal"));
    gearList2.add(new HandGear(new HitPoint(1, 6), "Tesseract"));
    gearList2.add(new FootGear(new HitPoint(6, 4), "Battering Rams"));
    gearList2.add(new HandGear(new HitPoint(7, 0), "Power Stone"));
    gearList2.add(new HeadGear(new HitPoint(0, 5), "Magneto's Helmet"));
    gearList2.add(new FootGear(new HitPoint(4, 1), "Space diver"));

    char2.equipGears(gearList2);

    String expected =
        "[Gear Name: Asgardian, Magneto's Helmet -- Defense Strength: 11; "
            + "Attack Strength: 0; Gear Type: HEAD, Gear Name: Infinity, "
            + "Power Stone -- Defense Strength: 10; Attack Strength: 17; "
            + "Gear Type: HAND, Gear Name: Loki's, Tesseract -- Defense Strength: 9; "
            + "Attack Strength: 4; Gear Type: HAND, Gear Name: Battering, "
            + "Battle Stomper -- Defense Strength: 6; Attack Strength: 10; "
            + "Gear Type: FOOT, Gear Name: Space, SHIELD's portal -- Defense Strength: 7; "
            + "Attack Strength: 6; Gear Type: FOOT]";

    assertEquals(expected, char2.getGearList().toString());
    assertEquals(5, char2.getGearList().size());
  }

  /** This JUnit test is used to test the toString method of the character class. */
  @Test
  public void testToString() {
    String expected =
        "Tony Stark has Base Attack Points: 9 and Base Defense Points: 8. \n"
            + " \n"
            + "Tony Stark's Arsenal: \n"
            + "1. Gear Name: Stark, Stark Glasses -- Defense Strength: 6; "
            + "Attack Strength: 0; Gear Type: HEAD\n"
            + "2. Gear Name: Nano-Handblade, Repulsor Gun -- Defense Strength: 3; "
            + "Attack Strength: 12; Gear Type: HAND\n"
            + "3. Gear Name: Heat, Energy Shield -- Defense Strength: 7; "
            + "Attack Strength: 5; Gear Type: HAND\n"
            + "4. Gear Name: Repulsor, Foot Clamps -- Defense Strength: 3; "
            + "Attack Strength: 14; Gear Type: FOOT\n"
            + "5. Gear Name: Battering, Flight Thrusters -- Defense Strength: 8; "
            + "Attack Strength: 9; Gear Type: FOOT\n"
            + "\n";
    assertEquals(expected, char1.toString());
  }
}

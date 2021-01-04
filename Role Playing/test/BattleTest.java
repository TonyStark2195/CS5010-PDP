import org.junit.Test;
import org.junit.Before;

import java.util.ArrayList;

import roleplaying.Battle;
import roleplaying.Character;
import roleplaying.CharacterType;
import roleplaying.Gear;
import roleplaying.HandGear;
import roleplaying.HitPoint;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests related to the Battle class. Here any functionality
 * related to the game are tested.
 */
public class BattleTest {

  private Battle endGame;
  private Battle infinityWar;

  /**
   * This is where the objects of the battle class that needs to be tested is instantiated. A battle
   * object can have no arguments (uses default constructor) or takes 4 arguments: 1) Character 1,
   * 2) Character 2, 3) Gear List 1, 4) Gear List 2.
   */
  @Before
  public void setUp() {
    endGame = new Battle();

    Character char1 = new Character(new HitPoint(9, 8), CharacterType.TECH, "Tony Stark");

    Character char2 = new Character(new HitPoint(13, 10), CharacterType.SUPER_BEING, "Thanos");

    ArrayList<Gear> gearList1 = new ArrayList<>();

    ArrayList<Gear> gearList2 = new ArrayList<>();

    infinityWar = new Battle(char1, char2, gearList1, gearList2);
  }

  /**
   * This is a JUnit test to see if the user can add gears to the gear list 1 that can be utilized
   * by the character 1.
   */
  @Test
  public void testAddGearToList1() throws Exception {
    infinityWar.addGearToList1(new HandGear(new HitPoint(5, 2), "Repulsor Gun"));
    assertEquals(1, infinityWar.getGearList1Size());
  }

  /**
   * This is a JUnit test to see if the user can add gears to the gear list 2 that can be utilized
   * by the character 2.
   */
  @Test
  public void testAddGearToList2() {
    infinityWar.addGearToList2(new HandGear(new HitPoint(5, 2), "Repulsor Gun"));
    assertEquals(1, infinityWar.getGearList2Size());
  }

  /**
   * This is a JUnit test to see if the user cannot add more than 10 gears to the gear list 1 and
   * throws an IllegalStateException.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidAddGearToList1() throws Exception {
    endGame.addGearToList1(new HandGear(new HitPoint(5, 2), "Repulsor Gun"));
  }

  /**
   * This is a JUnit test to see if the user cannot add more than 10 gears to the gear list 2 and
   * throws an IllegalStateException.
   */
  @Test(expected = IllegalStateException.class)
  public void testInvalidAddGearToList2() {
    endGame.addGearToList2(new HandGear(new HitPoint(5, 2), "Repulsor Gun"));
  }

  /**
   * This is a JUnit test to see if the getGearList1Size method outputs the expected number of gears
   * in the gear list 1.
   */
  @Test
  public void testGetGearList1Size() {
    assertEquals(10, endGame.getGearList1Size());
  }

  /**
   * This is a JUnit test to see if the getGearList2Size method outputs the expected number of gears
   * in the gear list 2.
   */
  @Test
  public void testGetGearList2Size() {
    assertEquals(10, endGame.getGearList2Size());
  }

  /**
   * This is a JUnit test to check if the characters are equipped with correct number of gears from
   * the list of gears they were provided with.
   */
  @Test
  public void testPrepareRound() throws Exception {
    endGame.prepareRound();

    String expected1 =
        "[Gear Name: Stark, Stark Glasses -- Defense Strength: 6; Attack Strength: 0; "
            + "Gear Type: HEAD, Gear Name: Nano-Handblade, Repulsor Gun -- "
            + "Defense Strength: 3; Attack Strength: 12; Gear Type: HAND, "
            + "Gear Name: Heat, Energy Shield -- Defense Strength: 7; Attack Strength: 5; "
            + "Gear Type: HAND, Gear Name: Repulsor, Foot Clamps -- Defense Strength: 3; "
            + "Attack Strength: 14; Gear Type: FOOT, Gear Name: Battering, Flight Thrusters -- "
            + "Defense Strength: 8; Attack Strength: 9; Gear Type: FOOT]";
    String expected2 =
        "[Gear Name: Asgardian, Magneto's Helmet -- Defense Strength: 11; "
            + "Attack Strength: 0; Gear Type: HEAD, Gear Name: Infinity, Power Stone -- "
            + "Defense Strength: 10; Attack Strength: 17; Gear Type: HAND, "
            + "Gear Name: Loki's, Tesseract -- Defense Strength: 9; Attack Strength: 4; "
            + "Gear Type: HAND, Gear Name: Battering, Battle Stomper -- Defense Strength: 6; "
            + "Attack Strength: 10; Gear Type: FOOT, Gear Name: Space, SHIELD's portal -- "
            + "Defense Strength: 7; Attack Strength: 6; Gear Type: FOOT]";

    assertEquals(expected1, endGame.getChar1().getGearList().toString());
    assertEquals(expected2, endGame.getChar2().getGearList().toString());
  }

  /** This is a JUnit test to check if the game is played properly and the winner is as expected. */
  @Test
  public void testPlayGame() {
    assertEquals("Thanos, Won the battle!", endGame.playGame());
  }

  /** This is a JUnit test to check if the winner of the game is as expected. */
  @Test
  public void testGetWinner() throws Exception {
    infinityWar.addGearToList1(new HandGear(new HitPoint(5, 2), "Repulsor Gun"));
    infinityWar.prepareRound();
    assertEquals("Tony Stark, Won the battle!", infinityWar.playGame());
  }

  /** This JUnit test is used to test the toString method of the battle class. */
  @Test
  public void testToString() throws Exception {
    Battle endGame = new Battle();

    endGame.prepareRound();

    endGame.playGame();

    String expected =
        "Tony Stark has 49 and 35 defense strength. \n"
            + " \n"
            + "Thanos has 50 and 53 defense strength. \n"
            + " \n"
            + "Tony Stark stats: \n"
            + "Tony Stark has Base Attack Points: 9 and Base Defense Points: 8. \n"
            + " \n"
            + "Tony Stark's Arsenal: \n"
            + "1. Gear Name: Stark, Stark Glasses -- Defense Strength: 6; Attack Strength: 0; "
            + "Gear Type: HEAD\n"
            + "2. Gear Name: Nano-Handblade, Repulsor Gun -- Defense Strength: 3; "
            + "Attack Strength: 12; Gear Type: HAND\n"
            + "3. Gear Name: Heat, Energy Shield -- Defense Strength: 7; Attack Strength: 5; "
            + "Gear Type: HAND\n"
            + "4. Gear Name: Repulsor, Foot Clamps -- Defense Strength: 3; Attack Strength: 14; "
            + "Gear Type: FOOT\n"
            + "5. Gear Name: Battering, Flight Thrusters -- Defense Strength: 8; "
            + "Attack Strength: 9; Gear Type: FOOT\n"
            + "\n"
            + "Thanos stats: \n"
            + "Thanos has Base Attack Points: 13 and Base Defense Points: 10. \n"
            + " \n"
            + "Thanos's Arsenal: \n"
            + "1. Gear Name: Asgardian, Magneto's Helmet -- Defense Strength: 11; "
            + "Attack Strength: 0; Gear Type: HEAD\n"
            + "2. Gear Name: Infinity, Power Stone -- Defense Strength: 10; "
            + "Attack Strength: 17; Gear Type: HAND\n"
            + "3. Gear Name: Loki's, Tesseract -- Defense Strength: 9; "
            + "Attack Strength: 4; Gear Type: HAND\n"
            + "4. Gear Name: Battering, Battle Stomper -- Defense Strength: 6; "
            + "Attack Strength: 10; Gear Type: FOOT\n"
            + "5. Gear Name: Space, SHIELD's portal -- Defense Strength: 7; "
            + "Attack Strength: 6; Gear Type: FOOT\n"
            + "\n"
            + "The battle ends with Tony Stark having 15 units of damage "
            + "and Thanos having -4 units of damage. \n"
            + " \n"
            + "Thanos, Won the battle!";

    assertEquals(expected, endGame.toString());
  }
}

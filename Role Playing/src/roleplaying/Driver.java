package roleplaying;

import java.util.ArrayList;

/**
 * This class serves as a lightweight demo to try and run the outputs. Contains the main method that
 * runs the following code of playing a duel between two characters.
 */
public class Driver {
  /**
   * The main class where the demo code is written.
   *
   * @param args the input arguments from the user
   * @throws Exception if there are any incorrect arguments or gear limit reached
   */
  public static void main(String[] args) throws Exception {

    System.out.println("\n Demo 1: \n \n");
    demo1();
    System.out.println("\n Demo 2: \n \n");
    demo2();
    System.out.println("\n Demo 3: \n \n");
    demo3();
  }

  /**
   * This primary demo method is written to run a default game where the characters, gears are
   * automatically instantiated by the battle class. By default the game is played between 'Tony
   * Stark' and 'Thanos'. They have a respective set of gears to equip themselves for the battle.
   */
  public static void demo1() {
    Battle endGame = new Battle();
    try {
      endGame.prepareRound();
    } catch (Exception e) {
      System.out.println("Error in preparing Game: " + e);
    }
    endGame.playGame();

    System.out.println(endGame.toString());
  }

  /**
   * This demo method is written to run a game where the characters, gears are passed by the user
   * who is willing to play the duel with their choice of characters and gears. Here the user has to
   * define all the gears (max of 10 gears for each player to choose from) before the battle is
   * instantiated and these character and gear objects are passed as arguments (Character 1,
   * Character 2, Gear List 1, Gear List 2).
   *
   * @throws Exception if more than ten gears are provided in the battlefield
   */
  public static void demo2() throws Exception {

    ArrayList<Gear> gearList1 = new ArrayList<>();
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

    Character char1 = new Character(new HitPoint(9, 8), CharacterType.TECH, "Tony Stark");

    Character char2 = new Character(new HitPoint(13, 10), CharacterType.SUPER_BEING, "Thanos");

    Battle endGame = new Battle(char1, char2, gearList1, gearList2);
    try {
      endGame.prepareRound();
    } catch (Exception e) {
      System.out.println("Error in preparing Game: " + e);
    }
    endGame.playGame();

    System.out.println(endGame.toString());
  }

  /**
   * This demo method is similar to demo2 and is written to run a game where the characters, gears
   * are passed by the user who is willing to play the duel with their choice of characters and
   * gears. The only difference from previous demo is that the user can provide/add one gear at a
   * time to the battlefield so that characters can equip them and upgrade themselves. Here the user
   * has to define the gears (max of 10 gears can be added for each player to choose from) before
   * the battle is instantiated and these character and empty list of gear objects are passed as
   * arguments (Character 1, Character 2, Gear List 1, Gear List 2). Then the user can add gears to
   * help Character 1 by using the method addGearToList1() and Character 2 by using the method
   * addGearToList2().
   *
   * @throws Exception if more than ten gears are provided in the battlefield
   */
  public static void demo3() throws Exception {
    Character char1 = new Character(new HitPoint(9, 8), CharacterType.TECH, "Tony Stark");

    Character char2 = new Character(new HitPoint(13, 10), CharacterType.SUPER_BEING, "Thanos");

    ArrayList<Gear> gearList1 = new ArrayList<>();

    ArrayList<Gear> gearList2 = new ArrayList<>();

    Battle endGame = new Battle(char1, char2, gearList1, gearList2);
    endGame.addGearToList1(new HandGear(new HitPoint(5, 2), "Repulsor Gun"));
    try {
      endGame.prepareRound();
    } catch (Exception e) {
      System.out.println("Error in preparing Game: " + e);
    }
    endGame.playGame();

    System.out.println(endGame.toString());
  }
}

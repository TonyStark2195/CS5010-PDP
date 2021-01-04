package roleplaying;

import java.util.ArrayList;

/**
 * This class is used to simulate a battle. It instantiates the environments, characters that
 * interact with the environment and objects they can use in the battle.
 */
public class Battle {

  private final Character char1;
  private final Character char2;
  private final ArrayList<Gear> gearList1;
  private final ArrayList<Gear> gearList2;
  private String winner;

  /**
   * This default constructor is used to create the environment for battle. It instantiates 2
   * characters to play and a list of 20 gears (10 x 2) that are available for the characters to
   * choose from.
   */
  public Battle() {
    this.char1 = new Character(new HitPoint(9, 8), CharacterType.TECH, "Tony Stark");

    this.char2 = new Character(new HitPoint(13, 10), CharacterType.SUPER_BEING, "Thanos");

    this.gearList1 = new ArrayList<>();
    this.gearList1.add(new HandGear(new HitPoint(5, 2), "Repulsor Gun"));
    this.gearList1.add(new HeadGear(new HitPoint(0, 5), "Stark Helmet"));
    this.gearList1.add(new FootGear(new HitPoint(3, 4), "Flight Thrusters"));
    this.gearList1.add(new HandGear(new HitPoint(4, 0), "Heat Seeking Missiles"));
    this.gearList1.add(new FootGear(new HitPoint(6, 3), "Foot Clamps"));
    this.gearList1.add(new HandGear(new HitPoint(7, 1), "Nano-Handblade"));
    this.gearList1.add(new FootGear(new HitPoint(6, 4), "Battering Rams"));
    this.gearList1.add(new HandGear(new HitPoint(1, 7), "Energy Shield"));
    this.gearList1.add(new HeadGear(new HitPoint(0, 1), "Stark Glasses"));
    this.gearList1.add(new FootGear(new HitPoint(8, 0), "Repulsor Gun"));

    this.gearList2 = new ArrayList<>();
    this.gearList2.add(new HandGear(new HitPoint(10, 10), "Infinity Gauntlet"));
    this.gearList2.add(new HeadGear(new HitPoint(0, 6), "Asgardian Helmet"));
    this.gearList2.add(new FootGear(new HitPoint(4, 2), "Battle Stomper"));
    this.gearList2.add(new HandGear(new HitPoint(3, 3), "Loki's Sceptre"));
    this.gearList2.add(new FootGear(new HitPoint(2, 6), "SHIELD's portal"));
    this.gearList2.add(new HandGear(new HitPoint(1, 6), "Tesseract"));
    this.gearList2.add(new FootGear(new HitPoint(6, 4), "Battering Rams"));
    this.gearList2.add(new HandGear(new HitPoint(7, 0), "Power Stone"));
    this.gearList2.add(new HeadGear(new HitPoint(0, 5), "Magneto's Helmet"));
    this.gearList2.add(new FootGear(new HitPoint(4, 1), "Space diver"));
  }

  /**
   * This another constructor with objects of the environment passed externally from the user for
   * testing purposes.
   *
   * @param char1 the character/player 1 object
   * @param char2 the character/player 2 object
   * @param gearList1 the list of gears available for character 1
   * @param gearList2 the list of gears available for character 2
   */
  public Battle(
      Character char1, Character char2, ArrayList<Gear> gearList1, ArrayList<Gear> gearList2) {
    this.char1 = char1;
    this.char2 = char2;
    if (gearList1.size() <= 10 && gearList2.size() <= 10) {
      this.gearList1 = gearList1;
      this.gearList2 = gearList2;
    } else {
      throw new IllegalStateException("More number of gears are provided!");
    }
  }

  /**
   * This method is used to add individual gears to the gear list that are available for character
   * 1.
   *
   * @param gear1 the gear to be added
   * @throws IllegalStateException if more gears beyond the capacity is tried to be added
   */
  public void addGearToList1(Gear gear1) {
    if (this.gearList1.size() + 1 < 10) {
      this.gearList1.add(gear1);
    } else {
      throw new IllegalStateException("Can't add more than 10 gears!");
    }
  }

  /**
   * This method is used to add individual gears to the gear list that are available for character
   * 2.
   *
   * @param gear2 the gear to be added
   * @throws IllegalStateException if more gears beyond the capacity is tried to be added
   */
  public void addGearToList2(Gear gear2) {
    if (this.gearList2.size() + 1 < 10) {
      this.gearList2.add(gear2);
    } else {
      throw new IllegalStateException("Can't add more than 10 gears!");
    }
  }

  /**
   * This method is used to get the size of the gear list 1.
   *
   * @return the size of the gear list 1
   */
  public int getGearList1Size() {
    return this.gearList1.size();
  }

  /**
   * This method is used to get the size of the gear list 2.
   *
   * @return the size of the gear list 2
   */
  public int getGearList2Size() {
    return this.gearList2.size();
  }

  /** This method is used to equip the characters with the necessary gears. */
  public void prepareRound() throws Exception {
    this.char1.equipGears(gearList1);
    this.char2.equipGears(gearList2);
  }

  /**
   * This method is to simulate the duel between both the upgraded players.
   *
   * @return the winner of the duel
   */
  public String playGame() {
    int player1Atk = char1.getEffectiveAttack();
    int player1Def = char1.getEffectiveDefense();
    int player2Atk = char2.getEffectiveAttack();
    int player2Def = char2.getEffectiveDefense();

    if ((player2Atk - player1Def) < (player1Atk - player2Def)) {
      this.winner = char1.getCharName() + ", Won the battle!";
    } else if ((player2Atk - player1Def) > (player1Atk - player2Def)) {
      this.winner = char2.getCharName() + ", Won the battle!";
    } else {
      this.winner = "Match Tied!";
    }
    return this.winner;
  }

  /**
   * This method is to return the winner of the battle.
   *
   * @return the winner of the duel
   */
  public String getWinner() {
    return this.winner;
  }

  /**
   * This method is to return the character 1.
   *
   * @return the character 1
   */
  public Character getChar1() {
    return this.char1;
  }

  /**
   * This method is to return the character 1.
   *
   * @return the character 1
   */
  public Character getChar2() {
    return this.char2;
  }

  /**
   * This method is used to print the summary of the battle.
   *
   * @return the string containing the summary of the battle
   */
  @Override
  public String toString() {
    int player1Atk = char1.getEffectiveAttack();
    int player1Def = char1.getEffectiveDefense();
    int player2Atk = char2.getEffectiveAttack();
    int player2Def = char2.getEffectiveDefense();

    return char1.getCharName()
        + " has "
        + player1Atk
        + " and "
        + player1Def
        + " defense strength. \n \n"
        + char2.getCharName()
        + " has "
        + player2Atk
        + " and "
        + player2Def
        + " defense strength. \n \n"
        + char1.getCharName()
        + " stats: \n"
        + char1.toString()
        + char2.getCharName()
        + " stats: \n"
        + char2.toString()
        + "The battle ends with "
        + char1.getCharName()
        + " having "
        + (player2Atk - player1Def)
        + " units of damage and "
        + char2.getCharName()
        + " having "
        + (player1Atk - player2Def)
        + " units of damage. \n \n"
        + this.getWinner();
  }
}

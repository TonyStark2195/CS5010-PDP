package roleplaying;

import java.util.ArrayList;
import java.util.Collections;

/**
 * This class contains all the operations that a character should support. A character is an entity
 * in the game that plays a duel against an opponent (another object of character type). The
 * character can possess gears to enhance its abilities to win the battle.
 */
public class Character {

  private final HitPoint charHP;
  private final CharacterType charType;
  private final String charName;
  private final ArrayList<Gear> gearList;
  private int headGearCount;
  private int handGearCount;
  private int footGearCount;
  private int headCount;
  private int handCount;
  private int footCount;

  /**
   * This constructor is used to instantiate a character object that will be used in the game. We
   * cam also pass directlt the gears that this character can equip.
   *
   * @param charHP the hit points of the character
   * @param charType the type of character
   * @param charName the name of the character
   * @param gearList the list of gears the character can equip
   */
  public Character(
      HitPoint charHP, CharacterType charType, String charName, ArrayList<Gear> gearList) {
    this.charHP = charHP;
    this.charType = charType;
    this.charName = charName;
    this.gearList = gearList;
    this.headGearCount = 0;
    this.handGearCount = 0;
    this.footGearCount = 0;
    this.headCount = 0;
    this.handCount = 0;
    this.footCount = 0;
  }

  /**
   * This constructor is used to instantiate a character object that will be used in the game.
   *
   * @param charHP the hit points of the character
   * @param charType the type of character
   * @param charName the name of the character
   */
  public Character(HitPoint charHP, CharacterType charType, String charName) {
    this.charHP = charHP;
    this.charType = charType;
    this.charName = charName;
    this.gearList = new ArrayList<>();
    this.headGearCount = 0;
    this.handGearCount = 0;
    this.footGearCount = 0;
    this.headCount = 0;
    this.handCount = 0;
    this.footCount = 0;
  }

  /**
   * This method is used to get the effective attack points of the character object.
   *
   * @return the effective attack points of the character object
   */
  public int getEffectiveAttack() {
    int charAttack = this.charHP.getAttackPoints();
    for (Gear gear : gearList) {
      charAttack += gear.getEffectiveHP().getAttackPoints();
    }
    return charAttack;
  }

  /**
   * This method is used to get the effective defense points of the character object.
   *
   * @return the effective defense points of the character object
   */
  public int getEffectiveDefense() {
    int charDefense = this.charHP.getDefensePoints();
    for (Gear gear : gearList) {
      charDefense += gear.getEffectiveHP().getDefensePoints();
    }
    return charDefense;
  }

  /**
   * This method is used to get the name of the character.
   *
   * @return the name of the character
   */
  public String getCharName() {
    return this.charName;
  }

  /**
   * This method is used to get the type of the character.
   *
   * @return the type of the character
   */
  public CharacterType getCharType() {
    return this.charType;
  }

  /**
   * This method is used to get the list of gears the character is equipped with.
   *
   * @return the list of gears
   */
  public ArrayList<Gear> getGearList() {
    return this.gearList;
  }

  /**
   * This method is used to add gear to the character. It checks if the gear addition follows the
   * logic of combining with same gear type and can't be combined more than once.
   *
   * @param gearObj the gear object that needs to be added to the character's gear guild
   * @throws IllegalStateException when more than 5 gears were tried to be equipped
   */
  public void addGearToChar(Gear gearObj) throws Exception {

    switch (gearObj.getGearType()) {
      case FOOT:
        this.footCount += 1;
        break;
      case HAND:
        this.handCount += 1;
        break;
      case HEAD:
        this.headCount += 1;
        break;
      default:
        throw new IllegalStateException("Unexpected value: " + gearObj.getGearType());
    }

    if (this.gearList.size() <= 5
        && this.headCount <= 2
        && this.handCount <= 4
        && this.footCount <= 4) {
      if (this.gearList.size() > 0) {
        if (gearObj.getGearType() == this.gearList.get(this.gearList.size() - 1).getGearType()) {
          if (this.gearList.get(this.gearList.size() - 1).isCombined()) {
            if (this.gearList.size() + 1 <= 5) {
              this.gearList.add(gearObj);
            }
            //            return;
          } else {
            this.replaceGear(gearObj, this.gearList.size() - 1);
            //            return;
          }
        } else {
          if (this.gearList.size() + 1 <= 5) {
            this.gearList.add(gearObj);
          }
          //          return;
        }
      } else {
        if (this.gearList.size() + 1 <= 5) {
          this.gearList.add(gearObj);
        }
        //        return;
      }
    } else {
      throw new IllegalStateException(
          "Can't equip more than 5 overall gears or 1 head gear or 2 hand gears or 2 foot gears!");
    }
  }

  /**
   * This method is used to replace a gear from the character's equipped gear list with a combined
   * gear.
   *
   * @param gearIndex the index in the list where the gear needs to be combined and replaced
   * @param gearObj the gear object that needs to be combined with the existing gear
   * @throws Exception when wrong gear is given as input
   */
  public void replaceGear(Gear gearObj, int gearIndex) throws Exception {
    if (gearIndex >= 0 && gearIndex < this.gearList.size()) {
      Gear popGear = this.gearList.get(gearIndex);
      Gear combinedGear = popGear.combineGear(gearObj);
      this.gearList.remove(popGear);
      this.gearList.add(combinedGear);
    } else {
      throw new IllegalArgumentException("Wrong Gear Index given as input!");
    }
  }

  /**
   * This method is used by the character to equip gears from a list of 10 gears. It takes the list
   * of gears and sorts it based on the gear type and inside the type sorts it based on the
   * decreasing order of attack points.
   *
   * @param givenGearList the gear list from which the character has to choose suitable gears
   * @throws Exception when the gears can't be combined
   */
  public void equipGears(ArrayList<Gear> givenGearList) throws Exception {

    System.out.println("Before Sorting: \n");
    for (int i = 0; i < givenGearList.size(); i++) {
      System.out.println((i + 1) + ". " + givenGearList.get(i).toString());
    }
    System.out.println("\n");
    Collections.sort(givenGearList);
    System.out.println("After Sorting: \n");
    for (int i = 0; i < givenGearList.size(); i++) {
      System.out.println((i + 1) + ". " + givenGearList.get(i).toString());
    }
    System.out.println("\n");

    for (Gear gear : givenGearList) {
      if (this.gearList.size() <= 5) {
        if (gear.getGearType() == GearType.FOOT) {
          if (this.footGearCount + 1 > 4) {
            continue;
          } else {
            this.footGearCount += 1;
            this.addGearToChar(gear);
          }
        } else if (gear.getGearType() == GearType.HAND) {
          if (this.handGearCount + 1 > 4) {
            continue;
          } else {
            this.handGearCount += 1;
            this.addGearToChar(gear);
          }
        } else if (gear.getGearType() == GearType.HEAD) {
          if (this.headGearCount + 1 > 2) {
            continue;
          } else {
            this.headGearCount += 1;
            this.addGearToChar(gear);
          }
        }
      }
    }
  }

  /**
   * This method is used to print the summary of the character.
   *
   * @return the string containing the summary of the character
   */
  @Override
  public String toString() {
    StringBuilder strObj = new StringBuilder();
    for (int i = 0; i < this.getGearList().size(); i++) {
      strObj.append(i + 1).append(". ").append(this.getGearList().get(i).toString()).append("\n");
    }
    return this.getCharName()
        + " has Base Attack Points: "
        + this.charHP.getAttackPoints()
        + " and Base Defense Points: "
        + this.charHP.getDefensePoints()
        + ". \n \n"
        + this.getCharName()
        + "'s Arsenal: \n"
        + strObj
        + "\n";
  }
}

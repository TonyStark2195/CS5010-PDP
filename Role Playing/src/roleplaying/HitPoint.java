package roleplaying;

/**
 * This class has all the functionalities of Hit Points, that any game object would posses. Any
 * object in a game like a gear, or a character should have hit points. Hit points consists of 2
 * things, the attack points and the defense points.
 */
public class HitPoint {
  private final int attackPoints;
  private final int defensePoints;

  /**
   * This constructor is used to set the values of attack points and defense points from the user.*
   *
   * @param attackPoints the attack point field of an hit point object
   * @param defensePoints the defense point field of an hit point object
   */
  public HitPoint(int attackPoints, int defensePoints) {
    this.attackPoints = attackPoints;
    this.defensePoints = defensePoints;
  }

  /**
   * This getter method is used to get the attack points of the calling object.
   *
   * @return the attack points of the calling object
   */
  public int getAttackPoints() {
    return this.attackPoints;
  }

  /**
   * This getter method is used to get the defense points of the calling object.
   *
   * @return the defense points of the calling object
   */
  public int getDefensePoints() {
    return this.defensePoints;
  }

  /**
   * This method is used to add hit points of two objects.
   *
   * @param hp the hit points of the object passed as an argument
   * @return the new hit point which is the sum of hit points of 2 objects
   */
  public HitPoint addHitPoints(HitPoint hp) {
    int newAttack = hp.getAttackPoints() + this.getAttackPoints();
    int newDefense = hp.getDefensePoints() + this.getDefensePoints();
    return new HitPoint(newAttack, newDefense);
  }

  /**
   * This method is used to print the summary of the Hit points.
   *
   * @return the string containing the summary of the Hit points
   */
  @Override
  public String toString() {
    return "Attack Points: "
        + this.getAttackPoints()
        + ", Defense Points: "
        + this.getDefensePoints();
  }
}

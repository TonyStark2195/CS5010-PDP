package roleplaying;

/**
 * This abstract class contains all the operations that all types of gears should support. This
 * serves as an encapsulation for the sub gear types and enables ease of code refactoring.
 */
public abstract class AbstractGear implements Gear {

  private final HitPoint gearHP;
  private final GearType gearType;
  private final String gearName;
  private final boolean isCombined;

  /**
   * This constructor is used to instantiate a gear object that will be used in the game.
   *
   * @param gearHP the hit points of the gear
   * @param gearType the type of gear
   * @param gearName the name of the gear
   */
  protected AbstractGear(HitPoint gearHP, GearType gearType, String gearName) {
    this.gearHP = gearHP;
    this.gearType = gearType;
    this.gearName = gearName;
    this.isCombined = false;
  }

  /**
   * This constructor is used to instantiate a combined gear object that will be used in the game.
   *
   * @param gearHP the hit points of the gear
   * @param gearType the type of gear
   * @param gearName the name of the gear
   * @param isCombined the status of gear whether it is combined or not
   */
  protected AbstractGear(HitPoint gearHP, GearType gearType, String gearName, boolean isCombined) {
    this.gearHP = gearHP;
    this.gearType = gearType;
    this.gearName = gearName;
    this.isCombined = isCombined;
  }

  /**
   * This abstract method needs to be implemented in the class that inherits. It is used to combine
   * two gears of same type to form a new upgraded gear.
   *
   * @param newGear the gear that needs to be combined with the gear object that is calling
   * @return the new combined gear
   */
  public abstract Gear combineGear(Gear newGear) throws Exception;

  /**
   * This method is used to get the effective hit points of the gear object.
   *
   * @return the effective HP of the gear object
   */
  public HitPoint getEffectiveHP() {
    return this.gearHP;
  }

  /**
   * This method is used to get the name of the gear.
   *
   * @return the name of the gear
   */
  public String getGearName() {
    return this.gearName;
  }

  /**
   * This method is used to get the type of the gear.
   *
   * @return the type of the gear
   */
  public GearType getGearType() {
    return this.gearType;
  }

  /**
   * This method is used to combine the names of two gears to form a new name to the newly combined
   * upgraded gear.
   *
   * @param secondGear the gear whose name needs to be combined with that of the existing gear
   *     object that is calling
   * @return the name of the new combined gear
   */
  public String combineGearName(Gear secondGear) {
    String gearName1 = this.getGearName().split(" ")[0];
    String gearName2 = secondGear.getGearName();
    return gearName1 + ", " + gearName2;
  }

  /**
   * This method is used to check if a gear is a combined type or not.
   *
   * @return the status of a gear whether it is combined or not
   */
  public boolean isCombined() {
    return this.isCombined;
  }

  /**
   * This method is used in array sorting of objects. Here the object of type gears are compared
   * against each other and are sorted in the following order. Head Gear > Hand Gear > Foot Gear.
   *
   * @param that the object of interface gear
   * @return {-1, 0, 1} depending upon the values between them
   */
  public int compareClass(Gear that) {
    int thisOrder = this.getClassOrder();
    int thatOrder = that.getClassOrder();

    return Integer.compare(thisOrder, thatOrder);
  }

  /**
   * This method is used in array sorting of objects. Overriding of the default compareTo method of
   * comparable class. It checks if the objects are of same sub class, if yes it will compare their
   * gear attack points to sort them based on that.
   *
   * @param gear the object of interface question
   * @return {-1, 0, 1} depending upon the values between them
   */
  @Override
  public int compareTo(Gear gear) {
    if (gear.getClass().equals(this.getClass())) {
      Integer quesOther = gear.getEffectiveHP().getAttackPoints();
      Integer quesThis = this.getEffectiveHP().getAttackPoints();
      return -quesThis.compareTo(quesOther);
    } else {
      return this.compareClass(gear);
    }
  }

  /**
   * This method is used to print the summary of the gear.
   *
   * @return the string containing the summary of the gear
   */
  @Override
  public String toString() {
    return "Gear Name: "
        + this.getGearName()
        + " -- Defense Strength: "
        + this.getEffectiveHP().getDefensePoints()
        + "; Attack Strength: "
        + this.getEffectiveHP().getAttackPoints()
        + "; Gear Type: "
        + this.getGearType();
  }
}

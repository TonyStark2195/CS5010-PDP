package roleplaying;

/** This child class contains all the operations that the Hand Gear should support. */
public class HandGear extends AbstractGear {

  private final int classOrder;

  /**
   * This constructor is used to instantiate a Hand Gear object that will be used in the game.
   *
   * @param gearHP the hit points of the hand gear
   * @param gearName the name of the gear
   */
  public HandGear(HitPoint gearHP, String gearName) {
    super(gearHP, GearType.HAND, gearName);
    this.classOrder = 2;
  }

  /**
   * This constructor is used to instantiate a combined Hand Gear object that will be used in the
   * game.
   *
   * @param gearHP the hit points of the new combined hand gear
   * @param gearName the name of the gear
   * @param isCombined the status of gear whether it is combined or not
   */
  public HandGear(HitPoint gearHP, String gearName, boolean isCombined) {
    super(gearHP, GearType.HAND, gearName, isCombined);
    this.classOrder = 2;
  }

  @Override
  public Gear combineGear(Gear newGear) throws Exception {
    if (newGear instanceof HandGear) {
      HitPoint combinedGearHP = this.getEffectiveHP().addHitPoints(newGear.getEffectiveHP());
      String combinedGearName = this.combineGearName(newGear);
      Gear combinedGear = new HandGear(combinedGearHP, combinedGearName, true);
      return combinedGear;
    } else {
      throw new IllegalArgumentException("Not a valid type of gear to combine!");
    }
  }

  //  @Override
  //  public int compareTo(Gear o) {
  //    return 0;
  //  }

  /**
   * This method is used to get the class order, which can be used for object sorting.
   *
   * @return the order of class which will be used in sorting (preference in sorting objects)
   */
  public int getClassOrder() {
    return this.classOrder;
  }
}

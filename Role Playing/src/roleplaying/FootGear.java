package roleplaying;

/** This child class contains all the operations that the Foot Gear should support. */
public class FootGear extends AbstractGear {

  private final int classOrder;

  /**
   * This constructor is used to instantiate a Foot Gear object that will be used in the game.
   *
   * @param gearHP the hit points of the foot gear
   * @param gearName the name of the gear
   */
  public FootGear(HitPoint gearHP, String gearName) {
    super(gearHP, GearType.FOOT, gearName);
    this.classOrder = 3;
  }

  /**
   * This constructor is used to instantiate a combined Foot Gear object that will be used in the
   * game.
   *
   * @param gearHP the hit points of the new combined foot gear
   * @param gearName the name of the gear
   * @param isCombined the status of gear whether it is combined or not
   */
  public FootGear(HitPoint gearHP, String gearName, boolean isCombined) {
    super(gearHP, GearType.FOOT, gearName, isCombined);
    this.classOrder = 3;
  }

  @Override
  public Gear combineGear(Gear newGear) throws Exception {
    if (newGear instanceof FootGear) {
      HitPoint combinedGearHP = this.getEffectiveHP().addHitPoints(newGear.getEffectiveHP());
      String combinedGearName = this.combineGearName(newGear);
      Gear combinedGear = new FootGear(combinedGearHP, combinedGearName, true);
      return combinedGear;
    } else {
      throw new IllegalArgumentException("Not a valid type of gear to combine!");
    }
  }

  /**
   * This method is used to get the class order, which can be used for object sorting.
   *
   * @return the order of class which will be used in sorting (preference in sorting objects)
   */
  public int getClassOrder() {
    return this.classOrder;
  }
}

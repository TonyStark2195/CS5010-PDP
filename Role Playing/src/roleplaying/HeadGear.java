package roleplaying;

/** This child class contains all the operations that the Head Gear should support. */
public class HeadGear extends AbstractGear {

  private final int classOrder;

  /**
   * This constructor is used to instantiate a Head Gear object that will be used in the game.
   *
   * @param gearHP the hit points of the head gear
   * @param gearName the name of the gear
   */
  public HeadGear(HitPoint gearHP, String gearName) {
    super(gearHP, GearType.HEAD, gearName);
    this.classOrder = 1;
  }

  /**
   * This constructor is used to instantiate a combined Head Gear object that will be used in the
   * game.
   *
   * @param gearHP the hit points of the new combined head gear
   * @param gearName the name of the gear
   * @param isCombined the status of gear whether it is combined or not
   */
  public HeadGear(HitPoint gearHP, String gearName, boolean isCombined) {
    super(gearHP, GearType.HEAD, gearName, isCombined);
    this.classOrder = 1;
  }

  @Override
  public Gear combineGear(Gear newGear) throws Exception {
    if (newGear instanceof HeadGear) {
      HitPoint combinedGearHP = this.getEffectiveHP().addHitPoints(newGear.getEffectiveHP());
      String combinedGearName = this.combineGearName(newGear);
      Gear combinedGear = new HeadGear(combinedGearHP, combinedGearName, true);
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

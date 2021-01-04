package roleplaying;

/** This interface contains all the operations that all types of gears should support. */
public interface Gear extends Comparable<Gear> {

  /**
   * This method is get the effective hit points of the gear object.
   *
   * @return the effective hit points of the calling gear object
   */
  HitPoint getEffectiveHP();

  /**
   * This method is used to combine two gears of the same type.
   *
   * @param newGear the gear that needs to be combined with the gear object that is calling
   * @return the new combined gear
   * @throws Exception when gears cannot be combined because they are of different types
   */
  Gear combineGear(Gear newGear) throws Exception;

  /**
   * This method is used to get the name of the gear.
   *
   * @return the name of the gear
   */
  String getGearName();

  /**
   * This method is used to get the type of the gear.
   *
   * @return the type of the gear
   */
  GearType getGearType();

  /**
   * This method is used to combine the names of two gears to form a new name to the newly combined
   * upgraded gear.
   *
   * @param secondGear the gear whose name needs to be combined with that of the existing gear
   *     object that is calling
   * @return the name of the new combined gear
   */
  String combineGearName(Gear secondGear);

  /**
   * This method is used to check if a gear is a combined type or not.
   *
   * @return the status of a gear whether it is combined or not
   */
  boolean isCombined();

  /**
   * This method is used to get the class order, which can be used for object sorting.
   *
   * @return the order of class which will be used in sorting (preference in sorting objects)
   */
  int getClassOrder();
}

package birds;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This abstract class represents and encapsulates the Birds. The birds class serve as the
 * definition for the birds in real life. Several types of birds inherit from this class. The
 * subclasses will have different characteristics and that distinguishes them from one another.
 */
abstract class Birds {
  private final String birdType;
  private final boolean isExtinct;
  private final int numWings;
  private final boolean nearWaterBody;
  private HashMap<String, String> characteristics;
  private HashMap<String, String> facialFeatures;
  private final String uniqueID;

  /**
   * The class constructor helps to initialize the required parameters for a bird object.
   *
   * @param birdType
   * @param isExtinct
   * @param numWings
   * @param nearWaterBody
   * @param uniqueID
   */
  public Birds(
      String birdType, boolean isExtinct, int numWings, boolean nearWaterBody, String uniqueID) {
    this.birdType = birdType;
    this.isExtinct = isExtinct;
    this.numWings = numWings;
    this.nearWaterBody = nearWaterBody;
    this.uniqueID = uniqueID;
  }

  /**
   * Gets the unique ID of the birds.
   *
   * @return unique ID of the birds
   */
  public String getUniqueID() {
    return this.uniqueID;
  }

  /**
   * Sets the Hashmap of bird's characteristics.
   *
   * @return nothing
   */
  public void setCharacteristics(HashMap characteristics) {
    this.characteristics = characteristics;
  }

  /**
   * Sets the Hashmap of bird's facial features.
   *
   * @return nothing
   */
  public void setFacialFeatures(HashMap facialFeatures) {
    this.facialFeatures = facialFeatures;
  }

  /**
   * Gets the Hashmap of bird's facial features.
   *
   * @return nothing
   */
  public HashMap<String, String> getFacialFeatures() {
    return this.facialFeatures;
  }

  /**
   * Gets the Hashmap of bird's characteristics.
   *
   * @return nothing
   */
  public HashMap<String, String> getCharacteristics() {
    return this.characteristics;
  }

  /**
   * Gets the water body status.
   *
   * @return boolean
   */
  public boolean getWaterBodyStatus() {
    return this.nearWaterBody;
  }

  /**
   * Gets the type/class of bird.
   *
   * @return the type/class of bird
   */
  public String getBirdType() {
    return this.birdType;
  }

  /**
   * Implemented in the child class. Gets what foods the respective type of birds prefer.
   *
   * @return the food items that the bird type consumes
   */
  public abstract ArrayList<String> getFoodItems();

  /**
   * Implemented in the child class. Gets what water body is nearby if any.
   *
   * @return the water body nearby
   */
  public abstract String getWaterBody();
}

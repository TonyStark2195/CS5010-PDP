package maze;

/** This Class is used to represent a Treasures (Gold) to be added in a maze. */
public class Treasure {

  private final String description;
  private final int value;

  /**
   * This constructor is used to create a Treasure object.
   *
   * @param description the description of the treasure
   * @param value the value of the treasure
   */
  public Treasure(String description, int value) {
    this.description = description;
    this.value = value;
  }

  /**
   * This method is used to get the description of the treasure.
   *
   * @return the description of the treasure
   */
  public String getDescription() {
    return description;
  }

  /**
   * This method is used to get the value of the treasure.
   *
   * @return the value of the treasure.
   */
  public int getValue() {
    return value;
  }

  /**
   * This method is used to override the default toString method and this is used to display the
   * summary of the stats of this treasure.
   */
  @Override
  public String toString() {
    return String.format("%s (value = %d)", description, value);
  }
}

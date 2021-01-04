package maze;

/** This Class is used to represent a Character to be added in a maze. */
public class Character {

  private final String name;
  private final String description;
  private int goldCoins;

  /**
   * This constructor is used to create a Character object.
   *
   * @param name the name of the character
   * @param description the description of the character
   * @param goldCoins the number of gold coins the character has (Player will have a positive value
   *     and a thief will have a negative value which means that a thief will steal the gold coins)
   */
  public Character(String name, String description, int goldCoins) {
    this.name = name;
    this.description = description;
    this.goldCoins = goldCoins;
  }

  /**
   * This method is used to get the name of the character.
   *
   * @return the name of the character
   */
  public String getName() {
    return name;
  }

  /**
   * This method is used to get the description of the character.
   *
   * @return the description of the character
   */
  public String getDescription() {
    return description;
  }

  /**
   * This method is used to get the number of gold coins the character has.
   *
   * @return the number of gold coins the character has
   */
  public int getGoldCoins() {
    return this.goldCoins;
  }

  /**
   * This method is used to set the number of gold coins the character has.
   *
   * @param goldCoins the number of gold coins
   */
  public void setGoldCoins(int goldCoins) {
    if (this.goldCoins + goldCoins < 0) {
      this.goldCoins = 0;
    } else {
      this.goldCoins += goldCoins;
    }
  }

  /**
   * This method is used to override the default toString method and this is used to display the
   * summary of the stats of this character.
   */
  @Override
  public String toString() {
    return String.format("%s (Current Gold = %d)", name, goldCoins);
  }
}

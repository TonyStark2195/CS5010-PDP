package enigma;

/**
 * This class is used to represent a character - frequency map of the intermediate frequency table
 * constructed to help with the encoding.
 */
public class EncoderNode {
  private final String character;
  private Integer frequency;

  /**
   * The default constructor of a encoder node.
   *
   * @param character the character/symbol present in the message
   * @param frequency the frequency of the character/symbol present in the message
   */
  public EncoderNode(String character, Integer frequency) {
    this.character = character;
    this.frequency = frequency;
  }

  /**
   * This method is used to update the frequency of a character.
   *
   * @param val the count that needs to be added to the frequency of a character
   * @return the encoder node with updated frequency
   */
  public EncoderNode addFreq(Integer val) {
    this.frequency += val;
    return this;
  }

  /**
   * This method is used to get the character in this node.
   *
   * @return the character in this node
   */
  public String getCharacter() {
    return this.character;
  }

  /**
   * This method is used to get the frequency of the character in this node.
   *
   * @return the frequency of the character in this node
   */
  public Integer getFrequency() {
    return this.frequency;
  }

  @Override
  public String toString() {
    return "[ " + character + ", " + frequency + " ]";
  }
}

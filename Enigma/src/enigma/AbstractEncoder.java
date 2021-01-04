package enigma;

import java.util.HashMap;
import java.util.Set;

/**
 * This abstract class contains all the operations that an Encoder should support. This serves as an
 * encapsulation for the sub types of encoders and enables the ease of code refactoring.
 */
public abstract class AbstractEncoder implements Encoder {

  private final String message;
  private String encodedMessage;
  private HashMap<String, String> codeDict;

  /**
   * This default constructor is used to instantiate an encoder object. This constructor takes only
   * the input message.
   *
   * @param message the input message in the form of String
   * @throws IllegalArgumentException if invalid input values are given
   */
  private AbstractEncoder(String message) throws IllegalArgumentException {
    if (message.length() > 0) {
      this.message = message;
    } else {
      throw new IllegalArgumentException("Invalid Message given!");
    }
  }

  /**
   * This additional constructor is used to instantiate an encoder object. This constructor takes
   * the input message and the code set. This encoder will have to build the coding scheme to encode
   * the message.
   *
   * @param message the input message in the form of String
   * @param codeSet the coding set which suggests what the code should consists of
   * @throws IllegalArgumentException if invalid input values are given
   */
  public AbstractEncoder(String message, Set<String> codeSet) {
    this(message);
    CodingScheme scheme = new CodingScheme(this.message, codeSet);
    this.codeDict = scheme.generateCodingScheme();
  }

  /**
   * This constructor is used to instantiate an easy encoder object. This constructor takes the
   * input message and the corresponding symbol prefix code (coding scheme).
   *
   * @param message the input message in the form of String
   * @param codeDict the coding scheme or the symbol -> code dictionary
   * @throws IllegalArgumentException if invalid input values are given
   */
  public AbstractEncoder(String message, HashMap<String, String> codeDict) {
    this(message);
    this.codeDict = codeDict;
  }

  @Override
  public void encode() {
    StringBuilder sb = new StringBuilder();
    for (char ch : message.toCharArray()) {
      if (this.codeDict.containsKey(String.valueOf(ch))) {
        sb.append(this.codeDict.get(String.valueOf(ch)));
      } else {
        System.out.println(
            "Can't encode this character -> " + ch + ", as character not found in prefix!");
        sb.append(ch);
      }
    }

    this.encodedMessage = sb.toString();
  }

  @Override
  public HashMap<String, String> getScheme() {
    return this.codeDict;
  }

  @Override
  public String getEncodedMessage() {
    return this.encodedMessage;
  }
}

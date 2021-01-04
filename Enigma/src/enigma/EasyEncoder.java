package enigma;

import java.util.HashMap;

/**
 * This class contains all the operations that an Easy Encoder should support. An easy encoder is a
 * straightforward encoder, which takes the message and converts them into encoded message based on
 * the symbol to code dictionary.
 */
public class EasyEncoder extends AbstractEncoder {

  /**
   * This constructor is used to instantiate an easy encoder object. This constructor takes the
   * input message and the corresponding symbol prefix code (coding scheme).
   *
   * @param message the input message in the form of String
   * @param codeDict the coding scheme or the symbol -> code dictionary
   * @throws IllegalArgumentException if invalid input values are given
   */
  public EasyEncoder(String message, HashMap<String, String> codeDict)
      throws IllegalArgumentException {
    super(message, codeDict);
  }
}

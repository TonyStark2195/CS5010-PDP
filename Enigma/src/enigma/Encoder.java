package enigma;

import java.util.HashMap;

/** This interface contains all the operations that an Encoder should support. */
public interface Encoder {

  /**
   * This method is used to encode the given text/message into an encoded text using the coding
   * scheme.
   */
  void encode();

  /**
   * This method is used to get the encoded message.
   *
   * @return the encoded message in string format
   */
  String getEncodedMessage();

  /**
   * This method is used to get the symbol to code dictionary.
   *
   * @return the the symbol to code dictionary
   */
  HashMap<String, String> getScheme();
}

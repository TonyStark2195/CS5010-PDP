package enigma;

import java.util.Set;

/**
 * This class contains all the operations that a Generic Encoder should support. A Generic encoder
 * is a template to encode a message with a set of given code literals. It takes the message and
 * converts them into encoded message based on the code set given. The encoded message will only
 * contains code symbols from the code set.
 */
public class GenericEncoder extends AbstractEncoder {

  /**
   * This constructor is used to instantiate an Generic encoder object. This constructor takes the
   * input message and the code set. This encoder will have to build the coding scheme to encode the
   * message.
   *
   * @param message the input message in the form of String
   * @param codeSet the coding set which suggests what the code should consists of
   * @throws IllegalArgumentException if invalid input values are given
   */
  public GenericEncoder(String message, Set<String> codeSet) {
    super(message, codeSet);
  }
}

package enigma;

import java.util.HashSet;
import java.util.Set;

/**
 * This class contains all the operations that a Binary Huffman Encoder should support. This
 * encoding generates a custom coding scheme for the message to be encoded, such that the length of
 * the encoded message (in terms of number of symbols) is minimized.
 */
public class HuffmanEncoderBinary extends GenericEncoder {

  private static final Set<String> codeSet = new HashSet<>();

  static {
    codeSet.add("0");
    codeSet.add("1");
  }

  /**
   * This constructor is used to instantiate an Huffman Binary encoder object. This constructor
   * takes the input message and the binary code set {0, 1}. This encoder will have to build the
   * coding scheme to encode the message.
   *
   * @param message the input message in the form of String
   * @throws IllegalArgumentException if invalid input values are given
   */
  public HuffmanEncoderBinary(String message) {
    super(message, codeSet);
  }
}

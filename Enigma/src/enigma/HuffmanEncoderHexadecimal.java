package enigma;

import java.util.HashSet;
import java.util.Set;

/**
 * This class contains all the operations that a Hexadecimal Huffman Encoder should support. This
 * encoding generates a custom coding scheme for the message to be encoded, such that the length of
 * the encoded message (in terms of number of symbols) is minimized.
 */
public class HuffmanEncoderHexadecimal extends GenericEncoder {

  private static final Set<String> codeSet = new HashSet<>();

  static {
    codeSet.add("0");
    codeSet.add("1");
    codeSet.add("2");
    codeSet.add("3");
    codeSet.add("4");
    codeSet.add("5");
    codeSet.add("6");
    codeSet.add("7");
    codeSet.add("8");
    codeSet.add("9");
    codeSet.add("a");
    codeSet.add("b");
    codeSet.add("c");
    codeSet.add("d");
    codeSet.add("e");
    codeSet.add("f");
  }

  /**
   * This constructor is used to instantiate an Huffman Hexadecimal encoder object. This constructor
   * takes the input message and the hexadecimal code set {0, 1, 2, 3, 4, 5, 6, 7, 8, 9, a, b, c, d,
   * e, f}. This encoder will have to build the coding scheme to encode the message.
   *
   * @param message the input message in the form of String
   * @throws IllegalArgumentException if invalid input values are given
   */
  public HuffmanEncoderHexadecimal(String message) {
    super(message, codeSet);
  }
}

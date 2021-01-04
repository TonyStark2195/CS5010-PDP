import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;

import enigma.Decoder;
import enigma.DecoderImpl;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests related to the Decoder interface. Here any functionality
 * related to the Encoder classes are tested.
 */
public class DecoderTest {
  private Decoder decoder;

  /** This is where the objects of the Decoder classes that needs to be tested is instantiated. */
  @Before
  public void setUp() {
    String encodedMessage = "1100101110101110000010111";
    HashMap<String, String> prefixEncoding = new HashMap<>();
    prefixEncoding.put(" ", "1010");
    prefixEncoding.put("a", "1011");
    prefixEncoding.put("s", "11");
    prefixEncoding.put("e", "00");
    prefixEncoding.put("h", "100");
    prefixEncoding.put("l", "01");
    decoder = new DecoderImpl(encodedMessage, prefixEncoding);
  }

  /** This is a JUnit test to see if the decoding is done correctly. */
  @Test
  public void testGenerateDecoderTree() {
    decoder.generateDecoderTree();
    assertEquals(
        "{ 0 : { 0 :  : e  }\n"
            + "{ 1 :  : l  }\n"
            + " }\n"
            + "{ 1 : { 0 : { 0 :  : h  }\n"
            + "{ 1 : { 0 :  :    }\n"
            + "{ 1 :  : a  }\n"
            + " }\n"
            + " }\n"
            + "{ 1 :  : s  }\n"
            + " }\n",
        decoder.getDecoderTree().toString());
  }

  /** This is a JUnit test to see if the decoded message is as expected. */
  @Test
  public void testDecodeMessage() {
    decoder.generateDecoderTree();
    decoder.decodeMessage();
    assertEquals("sea shells", decoder.getDecodedMessage());
  }
}

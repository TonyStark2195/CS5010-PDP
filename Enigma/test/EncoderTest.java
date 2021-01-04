import org.junit.Before;
import org.junit.Test;

import java.util.HashMap;
import java.util.HashSet;
import java.util.Set;

import static org.junit.Assert.assertEquals;

import enigma.EasyEncoder;
import enigma.Encoder;
import enigma.HuffmanEncoderBinary;
import enigma.HuffmanEncoderHexadecimal;
import enigma.GenericEncoder;

/**
 * This class contains all the unit tests related to the Encoder interface. Here any functionality
 * related to the Encoder classes are tested.
 */
public class EncoderTest {
  private Encoder easyEncoder;
  private Encoder huffmanBinary;
  private Encoder huffmanHexadecimal;
  private Encoder genericEncoder;

  /** This is where the objects of the Encoder classes that needs to be tested is instantiated. */
  @Before
  public void setUp() {
    String message = "sea shells";

    HashMap<String, String> codeDict = new HashMap<>();
    codeDict.put("a", "A");
    codeDict.put("s", "S");
    codeDict.put("e", "E");
    codeDict.put("h", "H");
    codeDict.put("l", "L");
    codeDict.put(" ", "_");

    Set<String> codeSet = new HashSet<>();
    codeSet.add("x");
    codeSet.add("y");
    codeSet.add("z");

    easyEncoder = new EasyEncoder(message, codeDict);

    huffmanBinary = new HuffmanEncoderBinary(message);

    huffmanHexadecimal = new HuffmanEncoderHexadecimal(message);

    genericEncoder = new GenericEncoder(message, codeSet);
  }

  /** This is a JUnit test to see if the encoding is done correctly. */
  @Test
  public void testGetEncodedMessage() {
    easyEncoder.encode();
    assertEquals("SEA_SHELLS", easyEncoder.getEncodedMessage());
  }

  /** This is a JUnit test to see if the get scheme method is correctly working. */
  @Test
  public void testGetScheme() {
    huffmanBinary.encode();
    assertEquals("{ =1010, a=1011, s=11, e=00, h=100, l=01}", huffmanBinary.getScheme().toString());
  }

  /** This is a JUnit test to see if the encoding of the easy encoder class is working correctly. */
  @Test
  public void testEncodeEasy() {
    easyEncoder.encode();
    assertEquals("{ =_, a=A, s=S, e=E, h=H, l=L}", easyEncoder.getScheme().toString());
    assertEquals("SEA_SHELLS", easyEncoder.getEncodedMessage());
  }

  /**
   * This is a JUnit test to see if the encoding of the Huffman binary encoder class is working
   * correctly.
   */
  @Test
  public void testEncodeHuffmanBinary() {
    huffmanBinary.encode();
    assertEquals("{ =1010, a=1011, s=11, e=00, h=100, l=01}", huffmanBinary.getScheme().toString());
    assertEquals("1100101110101110000010111", huffmanBinary.getEncodedMessage());
  }

  /**
   * This is a JUnit test to see if the encoding of the Huffman hexadecimal encoder class is working
   * correctly.
   */
  @Test
  public void testEncodeHuffmanHexadecimal() {
    huffmanHexadecimal.encode();
    assertEquals("{ =a, a=b, s=f, e=d, h=c, l=e}", huffmanHexadecimal.getScheme().toString());
    assertEquals("fdbafcdeef", huffmanHexadecimal.getEncodedMessage());
  }

  /**
   * This is a JUnit test to see if the encoding of the generic encoder class is working correctly.
   */
  @Test
  public void testEncodeGeneric() {
    genericEncoder.encode();
    assertEquals("{ =yzx, a=yzy, s=x, e=yx, h=yzz, l=yy}", genericEncoder.getScheme().toString());
    assertEquals("xyxyzyyzxxyzzyxyyyyx", genericEncoder.getEncodedMessage());
  }
}

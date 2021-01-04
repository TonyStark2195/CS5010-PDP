import org.junit.Before;
import org.junit.Test;

import enigma.Controller;

import static org.junit.Assert.assertEquals;

/**
 * This class contains all the unit tests related to the Controller interface. Here any
 * functionality related to the Controller class is tested.
 */
public class ControllerTest {

  private Controller binaryControl;
  private Controller hexadecimalControl;

  @Before
  public void setUp() {
    String message = "sea shells";

    binaryControl = new Controller();
    hexadecimalControl = new Controller();

    binaryControl.huffman(message, "binary");
    hexadecimalControl.huffman(message, "hexadecimal");
  }

  /**
   * This is a JUnit test to see if the binary huffman encoding can be converted to hexadecimal
   * encoding.
   */
  @Test
  public void testGetEncodedHexadecimal() {
    assertEquals("1100101110101110000010111", binaryControl.getEncodedMessage());
    assertEquals("1975c17", binaryControl.getEncodedHexadecimal());
  }

  /**
   * This is a JUnit test to see if the binary hexadecimal encoding can be converted to binary
   * encoding.
   */
  @Test
  public void testGetEncodedBinary() {
    assertEquals("fdbafcdeef", hexadecimalControl.getEncodedMessage());
    assertEquals("1111110110111010111111001101111011101111", hexadecimalControl.getEncodedBinary());
  }
}

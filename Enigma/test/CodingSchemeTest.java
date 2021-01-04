import org.junit.Before;
import org.junit.Test;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Objects;
import java.util.PriorityQueue;
import java.util.Set;

import static org.junit.Assert.assertEquals;

import enigma.CodingScheme;
import enigma.EncoderComparator;
import enigma.EncoderNode;

/**
 * This class contains all the unit tests related to the Coding Scheme class. Here any functionality
 * related to the Coding Scheme class are tested.
 */
public class CodingSchemeTest {

  private CodingScheme scheme;
  private HashMap<String, String> encoderDict;
  private HashMap<String, EncoderNode> freqDict;

  /**
   * This is where the objects of the Coding Scheme class that needs to be tested is instantiated.
   */
  @Before
  public void setUp() {
    String message = "sea shells";
    Set<String> codeSet = new HashSet<>();
    codeSet.add("0");
    codeSet.add("1");
    scheme = new CodingScheme(message, codeSet);
    freqDict = new HashMap<>();
    encoderDict = scheme.generateCodingScheme();
  }

  /** This is a JUnit test to see if the frequency table is correctly created. */
  @Test
  public void testGenerateFreqTable() {
    assertEquals(
        "{ =[  , 1 ], a=[ a, 1 ], s=[ s, 3 ], e=[ e, 2 ], h=[ h, 1 ], l=[ l, 2 ]}",
        scheme.getFreqTable().toString());
  }

  /** This is a JUnit test to see if the priority queue is correctly created. */
  @Test
  public void testPQ() {

    freqDict = scheme.getFreqTable();
    int pqSize = this.freqDict.keySet().size();
    PriorityQueue<EncoderNode> priorityQueue = new PriorityQueue<>(pqSize, new EncoderComparator());

    for (String key : this.freqDict.keySet()) {
      priorityQueue.add(this.freqDict.get(key));
    }

    List<String> testPQ = new ArrayList<>();
    testPQ.add("[  , 1 ]");
    testPQ.add("[ a, 1 ]");
    testPQ.add("[ h, 1 ]");
    testPQ.add("[ e, 2 ]");
    testPQ.add("[ l, 2 ]");
    testPQ.add("[ s, 3 ]");

    int size = priorityQueue.size();
    for (int i = 0; i < size; i++) {
      assertEquals(testPQ.get(i), Objects.requireNonNull(priorityQueue.poll()).toString());
    }
  }

  /** This is a JUnit test to see if the coding scheme is correctly generated. */
  @Test
  public void testGenerateCodingScheme() {
    assertEquals("{ =1010, a=1011, s=11, e=00, h=100, l=01}", encoderDict.toString());
  }
}

import org.junit.Before;
import org.junit.Test;

import java.util.Objects;
import java.util.PriorityQueue;

import static org.junit.Assert.assertEquals;

import enigma.EncoderComparator;
import enigma.EncoderNode;

/**
 * This class contains all the unit tests related to the EncoderComparator class. Here any
 * functionality related to the EncoderComparator class are tested.
 */
public class EncoderComparatorTest {
  private PriorityQueue<EncoderNode> priorityQueue;

  /** This is where the objects that needs to be tested is instantiated. */
  @Before
  public void setUp() {
    priorityQueue = new PriorityQueue<>(10, new EncoderComparator());
  }

  /** This is a JUnit test to see if the priority queue is correctly created. */
  @Test
  public void testPQ() {
    priorityQueue.add(new EncoderNode("A", 6));
    priorityQueue.add(new EncoderNode("B", 5));
    priorityQueue.add(new EncoderNode("C", 4));
    priorityQueue.add(new EncoderNode("H", 3));
    priorityQueue.add(new EncoderNode("E", 2));

    assertEquals("[ E, 2 ]", Objects.requireNonNull(priorityQueue.poll()).toString());
    assertEquals("[ H, 3 ]", Objects.requireNonNull(priorityQueue.poll()).toString());
    assertEquals("[ C, 4 ]", Objects.requireNonNull(priorityQueue.poll()).toString());
    assertEquals("[ B, 5 ]", Objects.requireNonNull(priorityQueue.poll()).toString());
    assertEquals("[ A, 6 ]", Objects.requireNonNull(priorityQueue.poll()).toString());
  }
}

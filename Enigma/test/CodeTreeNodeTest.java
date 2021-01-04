import org.junit.Before;
import org.junit.Test;

import java.util.LinkedList;
import java.util.Queue;

import static org.junit.Assert.assertEquals;

import enigma.CodeTreeNode;
import enigma.ElementNode;

/**
 * This class contains all the unit tests related to the CodeTreeNode interface. Here any
 * functionality related to the Element Node and Leaf Node classes are tested.
 */
public class CodeTreeNodeTest {
  private CodeTreeNode decoderTree;
  private Queue<String> q;

  /** This is where the objects that needs to be tested is instantiated. */
  @Before
  public void setUp() {

    q = new LinkedList<>();

    q.add("0");
    q.add("1");
    q.add("0");

    decoderTree = new ElementNode();
  }

  /** This is a JUnit test to see if the add method is correctly working. */
  @Test
  public void testAdd() {
    decoderTree = decoderTree.add("A", q);
    assertEquals("{ 0 : { 1 : { 0 :  : A  }\n" + " }\n" + " }\n", decoderTree.toString());
  }

  /** This is a JUnit test to see if the getChildren method is correctly returning the children. */
  @Test
  public void testGetChildren() {
    decoderTree = decoderTree.add("A", q);
    assertEquals("{0={ 1 : { 0 :  : A  }\n" + " }\n" + "}",
            decoderTree.getChildren().toString());
  }

  /**
   * This is a JUnit test to see if the getData method is correctly returning the data in that node.
   */
  @Test
  public void testGetData() {
    Queue<String> queue = new LinkedList<>(q);
    decoderTree = decoderTree.add("A", q);

    int size = queue.size();

    for (int i = 0; i < size; i++) {
      decoderTree = decoderTree.getChildren().get(queue.poll());
    }

    assertEquals("A", decoderTree.getData());
  }
}

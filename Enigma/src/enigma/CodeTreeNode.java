package enigma;

import java.util.HashMap;
import java.util.Queue;

/** Interface the represents a node in the Coding Tree. */
public interface CodeTreeNode {

  /**
   * This method is used to add the character/symbol to the tree based on the encoding code it is
   * assigned to.
   *
   * @param leaf the character to be added to the tree
   * @param queue the encoding code maintained in the form of queue for traversing sequentially
   * @return the tree with added character at their corresponding leaf
   */
  CodeTreeNode add(String leaf, Queue<String> queue);

  /**
   * This method is used to get the children of the current node.
   *
   * @return the children of this current node
   */
  HashMap<String, CodeTreeNode> getChildren();

  /**
   * This method is used to get the data present in the current node.
   *
   * @return the data present in this current node
   */
  String getData();
}

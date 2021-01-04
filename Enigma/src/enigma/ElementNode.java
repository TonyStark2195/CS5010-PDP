package enigma;

import java.util.HashMap;
import java.util.Queue;

/**
 * Representation of an internal node (non empty node) in the tree. The element node contains no
 * data and has only children. Every edge from the parent to child is an encoding literal.
 */
public class ElementNode implements CodeTreeNode {

  private final HashMap<String, CodeTreeNode> children;
  private final String data;

  /** This default constructor is used to instantiate a element node object. */
  public ElementNode() {
    this.children = new HashMap<>();
    this.data = null;
  }

  @Override
  public CodeTreeNode add(String leaf, Queue<String> queue) {
    String index = queue.poll();
    if (queue.isEmpty()) {
      children.put(index, new LeafNode().add(leaf, queue));
    } else {
      if (this.children.containsKey(index)) {
        CodeTreeNode old = children.get(index);
        old.add(leaf, queue);
      } else {
        children.put(index, new ElementNode().add(leaf, queue));
      }
    }
    return this;
  }

  @Override
  public HashMap<String, CodeTreeNode> getChildren() {
    return this.children;
  }

  @Override
  public String getData() {
    return this.data;
  }

  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder();
    for (String key : this.children.keySet()) {
      sb.append("{ ")
          .append(key)
          .append(" : ")
          .append(this.children.get(key).toString())
          .append(" }\n");
    }

    return sb.toString();
  }
}

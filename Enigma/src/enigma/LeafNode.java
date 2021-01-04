package enigma;

import java.util.HashMap;
import java.util.Queue;

/**
 * Representation of a leaf node for the tree. The leaf node contains only data and has no children.
 * This is where the symbol or the character exists.
 */
public class LeafNode implements CodeTreeNode {

  private final HashMap<String, CodeTreeNode> children;
  private String data;

  /** This default constructor is used to instantiate a leaf node object. */
  public LeafNode() {
    this.data = "";
    this.children = null;
  }

  @Override
  public CodeTreeNode add(String leaf, Queue<String> queue) {
    this.data = leaf;
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
    return " : " + this.data + " ";
  }
}

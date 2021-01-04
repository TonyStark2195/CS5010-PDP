package enigma;

import java.util.HashMap;
import java.util.LinkedList;
import java.util.Queue;

/**
 * This implementation class contains all the operations that a Decoder should support. A decoder is
 * used to decrypt the encoded message back to its original form. This decoder uses an internally
 * built tree structure and operates on it.
 */
public class DecoderImpl implements Decoder {

  private final HashMap<String, String> codeDict;

  private CodeTreeNode decoderTree;
  private final String encoded;
  private StringBuilder decodedMessage;

  /**
   * This constructor is used to instantiate a decoder object. It takes the encoded message and the
   * prefix encoding dictionary as input.
   *
   * @param encodedMessage the encoded message
   * @param codeDict the prefix encoding / symbol to code dictionary
   */
  public DecoderImpl(String encodedMessage, HashMap<String, String> codeDict) {

    this.codeDict = codeDict;
    this.encoded = encodedMessage;
    this.decodedMessage = new StringBuilder();
  }

  @Override
  public void generateDecoderTree() {

    Queue<String> q;

    this.decoderTree = new ElementNode();

    for (String key : this.codeDict.keySet()) {
      q = new LinkedList<>();
      for (char ch : this.codeDict.get(key).toCharArray()) {
        q.add(String.valueOf(ch));
      }
      this.decoderTree = this.decoderTree.add(key, q);
    }
  }

  @Override
  public void decodeMessage() {

    HashMap<String, CodeTreeNode> temp = this.decoderTree.getChildren();

    for (char ch : this.encoded.toCharArray()) {
      if (temp.get(String.valueOf(ch)) instanceof LeafNode) {
        this.decodedMessage.append(temp.get(String.valueOf(ch)).getData());
        temp = this.decoderTree.getChildren();
      } else {
        temp = temp.get(String.valueOf(ch)).getChildren();
      }
    }
  }

  @Override
  public CodeTreeNode getDecoderTree() {
    return this.decoderTree;
  }

  @Override
  public String getDecodedMessage() {
    return this.decodedMessage.toString();
  }
}

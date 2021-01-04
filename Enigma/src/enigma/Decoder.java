package enigma;

/** This interface contains all the operations that a Decoder should support. */
public interface Decoder {

  /**
   * This method is used to generate the decoder tree from the prefix encoding dictionary. This tree
   * will be used to decode an encoded message to its original form.
   */
  void generateDecoderTree();

  /**
   * This method is used to decode the encoded text/message into its original text using the
   * decoding tree.
   */
  void decodeMessage();

  /**
   * This method is used to get the decrypt key in the form of tree for the encrypted message.
   *
   * @return the decoder tree
   */
  CodeTreeNode getDecoderTree();

  /**
   * This method is used to get the original decrypted message.
   *
   * @return the decoded original text/message
   */
  String getDecodedMessage();
}

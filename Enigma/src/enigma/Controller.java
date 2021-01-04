package enigma;

import java.io.File;
import java.io.FileNotFoundException;
import java.io.PrintWriter;
import java.math.BigInteger;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This is the controller class/module for this project. It decides how the input is passed to the
 * model and how it is given back to the user.
 */
public class Controller {

  private Encoder encoder;
  private String inputMessage;
  private String decodedMessage;

  /**
   * This method is used to get some text from a file in the disk and return in the form of a
   * string.
   *
   * @param fileLocation the location of the file
   * @return the content in the file as string
   * @throws FileNotFoundException when the file location specified is incorrect
   */
  public String fromDisk(String fileLocation) throws FileNotFoundException {
    StringBuilder content = new StringBuilder(new String());
    File file = new File("res/runs/" + fileLocation);
    Scanner sc = new Scanner(file);

    while (sc.hasNextLine()) {
      content.append(sc.nextLine());
    }
    return content.toString();
  }

  /**
   * This method is used to get the prefix dictionary text from a file in the disk and return in the
   * form of HashMap(String, String).
   *
   * @param fileLocation the location of the file
   * @return the content in the file as HashMap(String, String)
   * @throws FileNotFoundException when the file location specified is incorrect
   */
  public HashMap<String, String> prefixFromDisk(String fileLocation) throws FileNotFoundException {
    File file = new File("res/runs/" + fileLocation);
    Scanner sc = new Scanner(file);
    HashMap<String, String> readDict = new HashMap<>();
    String[] temp;

    while (sc.hasNextLine()) {
      temp = sc.nextLine().split(":|=|,|->|-");
      readDict.put(temp[0], temp[1]);
    }
    return readDict;
  }

  /**
   * This method is used to save the prefix dictionary in the form of HashMap(String, String) to a
   * text file.
   *
   * @param fileLocation the location of the file
   * @param delimiter the delimiter used to separate the key and value pair for easy human
   *     readability
   * @throws FileNotFoundException when the file location specified is incorrect
   */
  public void prefixToDisk(
      String fileLocation, HashMap<String, String> prefixEncoding, String delimiter)
      throws FileNotFoundException {

    StringBuilder codeDictString = new StringBuilder(new String());
    for (String key : prefixEncoding.keySet()) {
      codeDictString.append(key).append(delimiter).append(prefixEncoding.get(key)).append("\n");
    }

    PrintWriter out = new PrintWriter("res/runs/" + fileLocation);
    out.println(codeDictString.toString().stripTrailing());
    out.close();
  }

  /**
   * This method is used to get input from the user.
   *
   * @return the input from the user in form of string
   */
  public String fromUser() {
    Scanner sc = new Scanner(System.in);
    return sc.nextLine();
  }

  /** This method is used to print output to the user. */
  public void toUser(String content) {
    System.out.println(content);
  }

  /**
   * This method is used to save any content in string form to disk.
   *
   * @param fileLocation the location of the file
   * @param content the content that needs to be saved
   * @throws FileNotFoundException when the file location specified is incorrect
   */
  public void toDisk(String fileLocation, String content) throws FileNotFoundException {
    PrintWriter out = new PrintWriter("res/runs/" + fileLocation);
    out.println(content);
    out.close();
  }

  /**
   * This controller method is used to perform default decoding of an encoded text, given the prefix
   * encoding.
   *
   * @param encodedMessage the encoded message
   * @param prefixEncoding the prefix encoding dictionary
   */
  public void decodeDefault(String encodedMessage, HashMap<String, String> prefixEncoding) {

    Decoder decoder = new DecoderImpl(encodedMessage, prefixEncoding);
    decoder.generateDecoderTree();
    decoder.decodeMessage();
    this.decodedMessage = decoder.getDecodedMessage();
  }

  /**
   * This controller method is used to perform default encoding of an input text, given the prefix
   * encoding.
   *
   * @param inputMessage the input message
   * @param prefixEncoding the prefix encoding dictionary
   */
  public void encodeDefault(String inputMessage, HashMap<String, String> prefixEncoding) {
    this.inputMessage = inputMessage;
    this.encoder = new EasyEncoder(inputMessage, prefixEncoding);
    this.encoder.encode();
  }

  /**
   * This controller method is used to perform generic encoding of an input text, given the coding
   * set.
   *
   * @param inputMessage the input message
   * @param codeSet the coding set
   */
  public void encodeGeneric(String inputMessage, Set<String> codeSet) {
    this.inputMessage = inputMessage;
    this.encoder = new GenericEncoder(inputMessage, codeSet);
    this.encoder.encode();
  }

  /**
   * This controller method is used to perform huffman encoding of an input text, given the encoding
   * type.
   *
   * @param inputMessage the input message
   * @param encodingType the type of huffman encoding ("binary", "hexadecimal")
   */
  public void huffman(String inputMessage, String encodingType) {
    this.inputMessage = inputMessage;
    switch (encodingType) {
      case "binary":
        this.encoder = new HuffmanEncoderBinary(inputMessage);
        break;
      case "hexadecimal":
        this.encoder = new HuffmanEncoderHexadecimal(inputMessage);
        break;
      default:
        Set<String> codeSet = new HashSet<>();
        codeSet.add("0");
        codeSet.add("1");
        this.encoder = new GenericEncoder(inputMessage, codeSet);
        break;
    }
    this.encoder.encode();
  }

  /**
   * This method is used to get the prefix encoding dictionary.
   *
   * @return the prefix encoding dictionary
   */
  public HashMap<String, String> getPrefixEncoding() {
    return this.encoder.getScheme();
  }

  /**
   * This method is used to get the input message.
   *
   * @return the input message
   */
  public String getInputMessage() {
    return this.inputMessage;
  }

  /**
   * This method is used to get the encoded message.
   *
   * @return the encoded message
   */
  public String getEncodedMessage() {
    return this.encoder.getEncodedMessage();
  }

  /**
   * This method is used to get the decoded message.
   *
   * @return the decoded message
   */
  public String getDecodedMessage() {
    return this.decodedMessage;
  }

  /**
   * This method is used to convert the encoded binary message into hexadecimal encoding.
   *
   * @return the encoded hexadecimal message in the form of String
   * @throws IllegalArgumentException if invalid string is given for parsing
   */
  public String getEncodedHexadecimal() throws IllegalArgumentException {
    try {
      int integerValue = Integer.parseInt(this.getEncodedMessage(), 2);
      return Integer.toString(integerValue, 16);
    } catch (final NumberFormatException e) {
      throw new IllegalArgumentException("Number can't be parsed!");
    }
  }

  /**
   * This method is used to convert the encoded hexadecimal message into binary encoding.
   *
   * @return the encoded binary message in the form of String
   * @throws IllegalArgumentException if invalid string is given for parsing
   */
  public String getEncodedBinary() throws IllegalArgumentException {
    try {
      return new BigInteger(this.getEncodedMessage(), 16).toString(2);
    } catch (final NumberFormatException e) {
      throw new IllegalArgumentException("Number can't be parsed!");
    }
  }
}

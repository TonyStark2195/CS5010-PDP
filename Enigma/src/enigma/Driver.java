package enigma;

import java.io.FileNotFoundException;
import java.util.HashMap;
import java.util.HashSet;
import java.util.Scanner;
import java.util.Set;

/**
 * This class serves as a lightweight demo to try and run the outputs. Contains the main method that
 * runs the following code of the Enigma - The code breaker!.
 */
public class Driver {
  /**
   * The main class where the demo code is written.
   *
   * @param args the input arguments from the user
   * @throws IllegalArgumentException if there are any incorrect arguments
   */
  public static void main(String[] args) throws IllegalArgumentException, FileNotFoundException {

    System.out.println(
        "\n Enter the type of demo you want to try (in integer format): "
            + "\n1. Read from User \n2. Read from File "
            + "\n3. Save to File \n4. Print to Screen \n5. Encode Decode "
            + "\n6. Default Easy Encoder \n7. Custom Encoder \n8. Default Decoder"
            + "\n0. Exit");
    Scanner sc = new Scanner(System.in);
    int choice = sc.nextInt();
    while (choice != 0) {
      switch (choice) {
        case 1:
          demoReadFromUserToUser();
          break;
        case 2:
          demoReadFromFileToFile();
          break;
        case 3:
          demoReadFromUserToFile();
          break;
        case 4:
          demoReadFromFileToUser();
          break;
        case 5:
          demoEncodeDecode();
          break;
        case 6:
          defaultEasyEncoder();
          break;
        case 7:
          customEncoder();
          break;
        case 8:
          defaultDecoder();
          break;
        default:
          System.out.println("Wrong Input given!");
          break;
      }

      System.out.println(
          "\n Enter the type of demo you want to try (in integer format): "
              + "\n1. Read from User \n2. Read from File "
              + "\n3. Save to File \n4. Print to Screen \n5. Encode Decode "
              + "\n6. Default Easy Encoder \n7. Custom Encoder \n8. Default Decoder"
              + "\n0. Exit");
      choice = sc.nextInt();
    }
  }

  /**
   * This demo method is used to get an input message from the user and display the prefix encoding
   * dictionary and the encoded message back to the user.
   */
  public static void demoReadFromUserToUser() {
    Controller control = new Controller();
    System.out.println("\n Enter the input message to be encoded: \n");
    String inputMessage = control.fromUser();

    System.out.println(
        "\n Enter the type of huffman encoding (in String Format): \n1. Binary \n2. Hexadecimal");
    String encodingType = control.fromUser();
    control.huffman(inputMessage, encodingType.toLowerCase());

    control.toUser("The encoded Message: \n" + control.getEncodedMessage());
    control.toUser("The encoded scheme: \n" + control.getPrefixEncoding());
  }

  /**
   * This demo method is used to get an input message from a file and display the prefix encoding
   * dictionary and the encoded message back to the user.
   */
  public static void demoReadFromFileToUser() throws FileNotFoundException {
    Controller control = new Controller();
    System.out.println(
        "\n Enter the location of the input message to be encoded: (Eg. filename.txt)\n");
    String fileLoc = control.fromUser();
    String inputMessage = control.fromDisk(fileLoc);

    System.out.println(
        "\n Enter the type of huffman encoding (in String Format): \n1. Binary \n2. Hexadecimal");
    String encodingType = control.fromUser();
    control.huffman(inputMessage, encodingType.toLowerCase());

    System.out.println("Input Message: " + control.getInputMessage());

    control.toUser("The encoded Message: \n" + control.getEncodedMessage());
    control.toUser("The encoded scheme: \n" + control.getPrefixEncoding());
  }

  /**
   * This demo method is used to get an input message from the user and save the encoded message to
   * a file.
   */
  public static void demoReadFromUserToFile() throws FileNotFoundException {
    Controller control = new Controller();
    System.out.println("\n Enter the input message to be encoded: \n");
    String inputMessage = control.fromUser();

    System.out.println(
        "\n Enter the type of huffman encoding (in String Format): \n1. Binary \n2. Hexadecimal");
    String encodingType = control.fromUser();
    control.huffman(inputMessage, encodingType.toLowerCase());

    System.out.println("\n Enter the location to store the file: (Eg. filename.txt)\n");
    String fileLoc = control.fromUser();
    control.toDisk(fileLoc, control.getEncodedMessage());
    System.out.println("\n Encoded message saved to: res/runs/" + fileLoc);
  }

  /**
   * This demo method is used to get an input message from the file and save the encoded message to
   * a file.
   */
  public static void demoReadFromFileToFile() throws FileNotFoundException {
    Controller control = new Controller();
    System.out.println(
        "\n Enter the location of the input message to be encoded: (Eg. filename.txt)\n");
    String fileLoc = control.fromUser();
    String inputMessage = control.fromDisk(fileLoc);

    System.out.println("Input Message: " + inputMessage);

    System.out.println(
        "\n Enter the type of huffman encoding (in String Format): \n1. Binary \n2. Hexadecimal");
    String encodingType = control.fromUser();
    control.huffman(inputMessage, encodingType.toLowerCase());

    System.out.println(
        "\n Enter the location of the encoded message to be saved: (Eg. filename.txt)\n");
    String saveFileLoc = control.fromUser();
    control.toDisk(saveFileLoc, control.getEncodedMessage());
    System.out.println("\n Encoded message saved to: res/runs/" + saveFileLoc);
  }

  /**
   * This demo method is used to perform encoding and successive decoding to get back the original
   * message.
   */
  public static void demoEncodeDecode() throws FileNotFoundException {
    Controller control = new Controller();

    System.out.println("\n Enter the input message to be encoded: \n");
    String inputMessage = control.fromUser();

    System.out.println(
        "\n Enter the type of huffman encoding (in String Format): \n1. Binary \n2. Hexadecimal");
    String encodingType = control.fromUser();
    control.huffman(inputMessage, encodingType.toLowerCase());

    System.out.println("Encoded message: " + control.getEncodedMessage());

    System.out.println("Writing the encoded message to disk...at res/runs/encoded_text.txt");
    control.toDisk("encoded_text.txt", control.getEncodedMessage());

    System.out.println("Reading the encoded message from disk...res/runs/encoded_text.txt");
    final String encodedMessage = control.fromDisk("encoded_text.txt");

    System.out.println("Prefix encoding dictionary: " + control.getPrefixEncoding());

    System.out.println(
        "Writing the prefix encoding dictionary to disk...at res/runs/prefix_encoding.txt");
    control.prefixToDisk("prefix_encoding.txt", control.getPrefixEncoding(), ":");

    System.out.println(
        "Reading the prefix encoding dictionary from disk...res/runs/prefix_encoding.txt");
    HashMap<String, String> prefix = control.prefixFromDisk("prefix_encoding.txt");

    System.out.println("Prefix encoding dictionary from disk: " + prefix);

    control.decodeDefault(encodedMessage, prefix);
    control.toUser("Decoded Message: " + control.getDecodedMessage());
  }

  /**
   * This demo method is used to show the working of the straight forward encoder, which takes the
   * input message from the user and takes the prefix dictionary from a file and display the encoded
   * message to the user.
   */
  public static void defaultEasyEncoder() throws FileNotFoundException {
    Controller control = new Controller();

    System.out.println("\n Enter the input message to be encoded: \n");
    String inputMessage = control.fromUser();

    System.out.println(
        "\n Enter the location of the prefix encoding dictionary: (Eg. filename.txt)\n");
    String fileLoc = control.fromUser();

    System.out.println("Reading the prefix encoding dictionary from disk...res/runs/" + fileLoc);
    HashMap<String, String> prefix = control.prefixFromDisk(fileLoc);

    control.encodeDefault(inputMessage, prefix);

    System.out.println("Encoded message: " + control.getEncodedMessage());
  }

  /**
   * This demo method is used to show the working of the custom encoder, which takes the input
   * message from the user and takes the code set also from the user and displays the encoded
   * message along with the prefix encoding to the user.
   */
  public static void customEncoder() {
    Controller control = new Controller();

    System.out.println("\n Enter the input message to be encoded: \n");
    String inputMessage = control.fromUser();

    System.out.println("\n Enter the number of elements your code set should consist of: \n");
    Scanner sc = new Scanner(System.in);
    int codeSetNum = sc.nextInt();
    Set<String> codeSet = new HashSet<>();

    while (codeSetNum != 0) {
      System.out.println(
          "\n Enter the singleton code/character that should belong in your code set: \n");
      String codeChar = control.fromUser();
      codeSet.add(codeChar);
      codeSetNum -= 1;
    }

    control.encodeGeneric(inputMessage, codeSet);

    System.out.println("Prefix Encoding: " + control.getPrefixEncoding());
    System.out.println("Encoded message: " + control.getEncodedMessage());
  }

  /**
   * This demo method is used to show the working of the default decoder, which takes the encoded
   * message from a file and takes the prefix dictionary also from a file and displays the decoded
   * message to the user.
   */
  public static void defaultDecoder() throws FileNotFoundException {
    Controller control = new Controller();

    System.out.println("\n Enter the location of the encoded message: (Eg. filename.txt)\n");
    String encodedFileLoc = control.fromUser();
    final String encodedMessage = control.fromDisk(encodedFileLoc);

    System.out.println(
        "\n Enter the location of the prefix encoding dictionary: (Eg. filename.txt)\n");
    String fileLoc = control.fromUser();

    System.out.println("Reading the prefix encoding dictionary from disk..." + fileLoc);
    HashMap<String, String> prefix = control.prefixFromDisk(fileLoc);

    System.out.println("Prefix encoding dictionary: " + prefix);

    control.decodeDefault(encodedMessage, prefix);

    control.toUser("Decoded Message: " + control.getDecodedMessage());
  }
}

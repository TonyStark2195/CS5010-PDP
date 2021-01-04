package enigma;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.List;
import java.util.PriorityQueue;
import java.util.Set;

/** This class is used to create the coding scheme for an input message. */
public class CodingScheme {

  private final String message;
  private final Set<String> codeSet;
  private HashMap<String, EncoderNode> freqDict;
  private PriorityQueue<EncoderNode> priorityQueue;

  /**
   * This default constructor is used to instantiate a coding scheme object. This constructor takes
   * the input message and the code set.
   *
   * @param message the input message in the form of String
   * @param codeSet the input message in the form of String
   * @throws IllegalArgumentException if invalid input values are given
   */
  public CodingScheme(String message, Set<String> codeSet) throws IllegalArgumentException {
    if (message.length() > 0) {
      this.message = message;
    } else {
      throw new IllegalArgumentException("Invalid Message given!");
    }
    this.codeSet = codeSet;
  }

  /**
   * This private method is used to generate the frequency of each characters in the input message.
   */
  private void generateFreqTable() {
    freqDict = new HashMap<>();

    for (char ch : this.message.toCharArray()) {
      if (this.freqDict.containsKey(String.valueOf(ch))) {
        this.freqDict.put(String.valueOf(ch), this.freqDict.get(String.valueOf(ch)).addFreq(1));
      } else {
        this.freqDict.put(String.valueOf(ch), new EncoderNode(String.valueOf(ch), 1));
      }
    }
  }

  /**
   * This private method is used to generate the priority queue consisting of characters sorted
   * based on their increasing order of frequency.
   */
  private void generatePQ() {
    int pqSize = this.freqDict.keySet().size();
    priorityQueue = new PriorityQueue<>(pqSize, new EncoderComparator());

    for (String key : this.freqDict.keySet()) {
      priorityQueue.add(this.freqDict.get(key));
    }
  }

  /**
   * This method is used to generate the coding scheme for the input message.
   *
   * @return the coding scheme for the input message
   */
  public HashMap<String, String> generateCodingScheme() {

    HashMap<String, String> codeDict = new HashMap<>();
    List<String> codeSetList = new ArrayList<>(this.codeSet);
    int popSize;

    generateFreqTable();
    generatePQ();

    // When the characters to be encoded is less than the code set length.
    popSize = Math.min(this.freqDict.keySet().size(), codeSetList.size());

    while (priorityQueue.size() != 1) {
      StringBuilder tempChar = new StringBuilder();
      Integer tempFreq = 0;
      EncoderNode tempEncode;
      HashMap<String, String> tempMap = new HashMap<>();

      for (int i = 0; i < popSize; i++) {
        tempEncode = priorityQueue.poll();

        if (tempEncode != null) {
          tempMap.put(tempEncode.getCharacter(), codeSetList.get(i));
          tempChar.append(tempEncode.getCharacter());
          tempFreq += tempEncode.getFrequency();
        }
        if (priorityQueue.size() == 0) {
          break;
        }
      }

      priorityQueue.add(new EncoderNode(tempChar.toString(), tempFreq));

      for (String key : tempMap.keySet()) {
        for (char ch : key.toCharArray()) {
          if (codeDict.containsKey(String.valueOf(ch))) {
            String codeExisting = codeDict.get(String.valueOf(ch));
            codeDict.put(String.valueOf(ch), tempMap.get(key) + codeExisting);
          } else {
            codeDict.put(String.valueOf(ch), tempMap.get(key));
          }
        }
      }
    }

    return codeDict;
  }

  /**
   * This method is used to get the frequency table.
   *
   * @return the frequency table of the input message
   */
  public HashMap<String, EncoderNode> getFreqTable() {
    return this.freqDict;
  }

  /**
   * This method is used to get the priority queue.
   *
   * @return the priority queue
   */
  public PriorityQueue<EncoderNode> getPriorityQueue() {
    return this.priorityQueue;
  }
}

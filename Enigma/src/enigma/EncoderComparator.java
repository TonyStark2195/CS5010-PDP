package enigma;

import java.util.Comparator;

/**
 * This class is used to override the comparator to compare the encoder node based on its frequency
 * value. This is used by priority queue to sort and store the elements.
 */
public class EncoderComparator implements Comparator<EncoderNode> {

  @Override
  public int compare(EncoderNode o1, EncoderNode o2) {
    Integer x1 = o1.getFrequency();
    Integer x2 = o2.getFrequency();
    if (x1 < x2) {
      return -1;
    } else if (x1 > x2) {
      return 1;
    } else {
      return Integer.compare(o1.getCharacter().compareTo(o2.getCharacter()), 0);
    }
  }
}

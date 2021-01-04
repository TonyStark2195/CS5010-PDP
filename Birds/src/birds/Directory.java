package birds;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.TreeMap;
import java.util.Iterator;

/** This Directory class contains different representations of the data. */
public class Directory {
  private final ConservatoryInterface conservatory;
  private HashMap<String, String> birdIDAviary = new HashMap();
  private HashMap<String, ArrayList<String>> directory = new HashMap();

  /**
   * This constructor creates a hashmap with key as bird's unique ID and aviary location as value.
   */
  public Directory(ConservatoryInterface conservatory) {
    this.conservatory = conservatory;
    ArrayList<Aviaries> aviaryList = this.conservatory.getAviaryList();
    for (int x = 0; x < aviaryList.size(); x++) {
      ArrayList<Birds> birdList = aviaryList.get(x).getBirds();
      for (int y = 0; y < birdList.size(); y++) {
        this.birdIDAviary.put(birdList.get(y).getUniqueID(), aviaryList.get(x).getAviaryLocation());
      }
    }
  }

  /**
   * User queries the aviary location of a bird.
   *
   * @return location of aviary
   */
  public String getAviaryFromBird(String birdIDString) {
    return this.birdIDAviary.get(birdIDString);
  }

  /**
   * User queries info about an aviary.
   *
   * @return the description of the birds it houses and any interesting information that it may have
   *     about that animal.
   */
  public String toString(Aviaries aviary) {
    String aviaryChar = new String();
    HashMap<String, Integer> uniqueCount = new HashMap();
    aviaryChar =
        "Aviary Name: "
            + aviary.getAviaryLocation()
            + " Types of Birds present: "
            + aviary.getUniqueBirds();
    for (int k = 0; k < aviary.getUniqueBirds().size(); k++) {
      uniqueCount.put(aviary.getUniqueBirds().get(k), 0);
    }

    for (int k = 0; k < aviary.getBirds().size(); k++) {
      HashMap<String, String> birdFeatures = aviary.getBirds().get(k).getCharacteristics();
      if (uniqueCount.get(aviary.getBirds().get(k).getBirdType()) < 1) {
        aviaryChar =
            aviaryChar
                + " \n "
                + aviary.getBirds().get(k).getBirdType()
                + " has Special Ability: "
                + birdFeatures.get("special_ability");
        if (aviary.getBirds().get(k).getWaterBodyStatus()) {
          aviaryChar = aviaryChar + " Lives near: " + aviary.getBirds().get(k).getWaterBody();
        }
      }
      int val = uniqueCount.get(aviary.getBirds().get(k).getBirdType());
      uniqueCount.put(aviary.getBirds().get(k).getBirdType(), val + 1);
    }

    for (int k = 0; k < aviary.getUniqueBirds().size(); k++) {
      aviaryChar =
          aviaryChar
              + " \n There are "
              + uniqueCount.get(aviary.getUniqueBirds().get(k))
              + " "
              + aviary.getUniqueBirds().get(k);
    }

    return aviaryChar;
  }

  /**
   * Lists all the aviaries by location and the birds they house.
   *
   * @return the directory consisting of the information
   */
  public HashMap<String, ArrayList<String>> listBirds() {
    ArrayList<Aviaries> aviaryList = this.conservatory.getAviaryList();
    for (int x = 0; x < aviaryList.size(); x++) {
      ArrayList<Birds> birdList = aviaryList.get(x).getBirds();
      for (int y = 0; y < birdList.size(); y++) {
        String birdIDTemp = birdList.get(y).getUniqueID();
        String aviaryLoc = aviaryList.get(x).getAviaryLocation();
        if (this.directory.containsKey(aviaryLoc)) {
          ArrayList<String> existingBirds = this.directory.get(aviaryLoc);
          existingBirds.add(birdIDTemp);
          this.directory.put(aviaryList.get(x).getAviaryLocation(), existingBirds);
        } else {
          ArrayList<String> newArray = new ArrayList<String>();
          newArray.add(birdIDTemp);
          this.directory.put(aviaryList.get(x).getAviaryLocation(), newArray);
        }
      }
    }
    return this.directory;
  }

  /**
   * Index that lists all birds in the conservatory in alphabetical order and their location.
   *
   * @return sorted hashmap based on the key unique ID of the birds
   */
  public TreeMap<String, String> showAviaries() {
    TreeMap<String, String> sortedBirdIDAviary = new TreeMap<String, String>(birdIDAviary);
    Iterator iterator = sortedBirdIDAviary.keySet().iterator();
    while (iterator.hasNext()) {
      String key = (String) iterator.next();
      sortedBirdIDAviary.put(key, birdIDAviary.get(key));
    }
    return sortedBirdIDAviary;
  }
}

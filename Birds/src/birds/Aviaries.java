package birds;

import java.util.ArrayList;
import java.util.HashMap;

/** This Aviaries class represents the housing for the rescued birds. */
@SuppressWarnings("ALL")
public class Aviaries {
  private ArrayList<Birds> birds = new ArrayList<Birds>();
  private ArrayList<String> containsUniqueBirds = new ArrayList();
  private String aviaryLocation;
  private HashMap<String, Integer> foodQuantities = new HashMap();

  /**
   * Gets the location of the aviary.
   *
   * @return aviary location
   */
  public String getAviaryLocation() {
    return this.aviaryLocation;
  }

  /**
   * Sets the location of the aviary.
   *
   * @return nothing
   */
  public void setAviaryLocation(String aviaryLocation) {
    this.aviaryLocation = aviaryLocation;
  }

  /**
   * Adds the bids to the aviaries.
   *
   * @throws Exception when max capacity of birds reached (5 birds)
   */
  public void addBirdsToAviaries(Birds bird) throws Exception {
    if (this.birds.size() >= 5) {
      throw new Exception("Can't add more than 5 birds!");
    }
    this.birds.add(bird);
  }

  /**
   * Gets the birds as arraylist.
   *
   * @return the list of birds
   */
  public ArrayList<Birds> getBirds() {
    return this.birds;
  }

  /**
   * Gets unique types of birds in a aviary.
   *
   * @return the list of unique birds
   */
  public ArrayList<String> getUniqueBirds() {
    for (int i = 0; i < this.birds.size(); i++) {
      if (this.containsUniqueBirds.contains(this.birds.get(i).getBirdType())) {
        continue;
      } else {
        this.containsUniqueBirds.add(this.birds.get(i).getBirdType());
      }
    }
    return this.containsUniqueBirds;
  }

  /**
   * Calculates the quantity of food required in an aviary.
   *
   * @return the quantity of food required in an aviary as hashmap with type of birds as key and
   *     quantity of food as value
   */
  public HashMap<String, Integer> calculateFood() {
    for (int i = 0; i < this.birds.size(); i++) {
      String birdTypeCalc = this.birds.get(i).getBirdType();

      if (this.foodQuantities.containsKey(birdTypeCalc)) {
        int val = (int) this.foodQuantities.get(birdTypeCalc);
        this.foodQuantities.put(birdTypeCalc, val + 1);
      } else {
        this.foodQuantities.put(birdTypeCalc, 0);
      }
    }
    return this.foodQuantities;
  }
}

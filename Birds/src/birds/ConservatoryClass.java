package birds;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This class represents and encapsulates the Conservatory. This Conservatory is the house for
 * rescued birds and they consists of aviaries. Each aviary will contain some birds. This interface
 * is implemented by the abstract ConservatoryClass.
 */
@SuppressWarnings("ALL")
public class ConservatoryClass implements ConservatoryInterface {

  private ArrayList<Aviaries> avaiaryList = new ArrayList<Aviaries>();
  private ArrayList<String> singleClass = new ArrayList<String>();
  private HashMap<String, Integer> foodQuantities = new HashMap();

  public ConservatoryClass() {
    this.singleClass.add("flightless_birds");
    this.singleClass.add("birds_of_prey");
    this.singleClass.add("waterfowl");
  }

  /**
   * Defines an empty Aviaries list.
   *
   * @return the list of aviaries present in the conservatory
   */
  public ArrayList<Aviaries> getAviaryList() {
    return this.avaiaryList;
  }

  /**
   * Takes nothing, just returns.
   *
   * @return the list of bird types that wouldn't mix with any other type
   */
  public ArrayList<String> getSingleClass() {
    return singleClass;
  }

  /**
   * Defines an list of bird types that wouldn't mix with any other type.
   *
   * @return nothing
   */
  @Override
  public void setSingleClass(ArrayList<String> singleClass) {
    this.singleClass = singleClass;
  }

  /**
   * Takes an aviary object as input and adds it to the conservatory.
   *
   * @return nothing
   */
  @Override
  public void addAviariesToConservatory(Aviaries aviary) throws Exception {
    if (this.avaiaryList.size() >= 20) {
      throw new IllegalArgumentException("Can't add more than 20 aviaries!");
    }
    this.avaiaryList.add(aviary);
  }

  /**
   * Takes a bird object as input and adds it to the conservatory. It checks for compatibility and
   * calls the addAviariesToConservatory method whenever necessary.
   *
   * @return nothing
   */
  @Override
  public void addBirdsToConservatory(Birds bird) throws Exception {

    if (this.getAviaryList().size() == 0) {
      Aviaries aviaryObj = new Aviaries();
      aviaryObj.setAviaryLocation(
          "Aviary No: " + Integer.toString(this.getAviaryList().size() + 1));
      this.addAviariesToConservatory(aviaryObj);
    }

    for (int i = 0; i < this.getAviaryList().size(); i++) {
      Aviaries referenceAviary = this.getAviaryList().get(i);
      if (referenceAviary.getBirds().size() <= 5) {
        if (referenceAviary.getBirds().size() == 0) {
          referenceAviary.addBirdsToAviaries(bird);
          return;
        } else {
          this.checkCompatibility(referenceAviary, bird);
          return;
        }
      } else {
        if (this.singleClass.contains(bird.getBirdType())) {
          Aviaries aviaryInstance = new Aviaries();
          aviaryInstance.addBirdsToAviaries(bird);
          aviaryInstance.setAviaryLocation(
              "Aviary No: " + Integer.toString(this.getAviaryList().size() + 1));
          this.addAviariesToConservatory(aviaryInstance);
          return;
        }
      }
    }
  }

  /**
   * Takes Aviaries object and Birds object, it decides how and where to add the birds to the
   * conservatory.
   *
   * @return nothing
   */
  @Override
  public void checkCompatibility(Aviaries aviary, Birds bird) throws Exception {
    if (this.singleClass.contains(bird.getBirdType())) {
      switch (bird.getBirdType()) {
        case "flightless_birds":
          if (bird.getBirdType().equals(aviary.getUniqueBirds().get(0))) {
            aviary.addBirdsToAviaries(bird);
          } else {
            Aviaries aviaryInstance = new Aviaries();
            aviaryInstance.addBirdsToAviaries(bird);
            aviaryInstance.setAviaryLocation(
                "Aviary No: " + Integer.toString(this.getAviaryList().size() + 1));
            this.addAviariesToConservatory(aviaryInstance);
          }
          break;
        case "birds_of_prey":
          if (bird.getBirdType().equals(aviary.getUniqueBirds().get(0))) {
            aviary.addBirdsToAviaries(bird);
          } else {
            Aviaries aviaryInstance = new Aviaries();
            aviaryInstance.addBirdsToAviaries(bird);
            aviaryInstance.setAviaryLocation(
                "Aviary No: " + Integer.toString(this.getAviaryList().size() + 1));
            this.addAviariesToConservatory(aviaryInstance);
          }
          break;
        case "waterfowl":
          if (bird.getBirdType().equals(aviary.getUniqueBirds().get(0))) {
            aviary.addBirdsToAviaries(bird);
          } else {
            Aviaries aviaryInstance = new Aviaries();
            aviaryInstance.addBirdsToAviaries(bird);
            aviaryInstance.setAviaryLocation(
                "Aviary No: " + Integer.toString(this.getAviaryList().size() + 1));
            this.addAviariesToConservatory(aviaryInstance);
          }
          break;
        default:
          break;
      }

    } else {
      aviary.addBirdsToAviaries(bird);
    }
  }

  /**
   * Responsible for calculating the food required in the conservatory.
   *
   * @return the hashmap of birdType as key and quantity of food as value
   */
  @Override
  public HashMap<String, Integer> calculateFood() {
    for (int i = 0; i < this.avaiaryList.size(); i++) {
      ArrayList<Birds> birdList = this.avaiaryList.get(i).getBirds();

      for (int j = 0; j < birdList.size(); j++) {
        String birdTypeCalc = birdList.get(j).getBirdType();
        if (this.foodQuantities.containsKey(birdTypeCalc)) {
          int val = (int) this.foodQuantities.get(birdTypeCalc);
          this.foodQuantities.put(birdTypeCalc, val + 1);
        } else {
          this.foodQuantities.put(birdTypeCalc, 0);
        }
      }
    }
    return this.foodQuantities;
  }
}

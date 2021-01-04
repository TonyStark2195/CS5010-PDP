package birds;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * This interface represents and encapsulates the Conservatory. This Conservatory is the house for
 * rescued birds and they consists of aviaries. Each aviary will contain some birds. This interface
 * is implemented by the abstract ConservatoryClass.
 */
public interface ConservatoryInterface {
  /**
   * Defines an empty Aviaries list.
   *
   * @return the list of aviaries present in the conservatory
   */
  ArrayList<Aviaries> getAviaryList();

  /**
   * Takes nothing, just returns.
   *
   * @return the list of bird types that wouldn't mix with any other type
   */
  ArrayList<String> getSingleClass();

  /**
   * Defines an list of bird types that wouldn't mix with any other type.
   *
   * @return nothing
   */
  void setSingleClass(ArrayList<String> singleClass);

  /**
   * Takes an aviary object as input and adds it to the conservatory.
   *
   * @return nothing
   */
  void addAviariesToConservatory(Aviaries aviary) throws Exception;

  /**
   * Takes a bird object as input and adds it to the conservatory. It checks for compatibility and
   * calls the addAviariesToConservatory method whenever necessary.
   *
   * @return nothing
   */
  void addBirdsToConservatory(Birds bird) throws Exception;

  /**
   * Takes Aviaries object and Birds object, it decides how and where to add the birds to the
   * conservatory.
   *
   * @return nothing
   */
  void checkCompatibility(Aviaries aviary, Birds bird) throws Exception;

  /**
   * Responsible for calculating the food required in the conservatory.
   *
   * @return the hashmap of birdType as key and quantity of food as value
   */
  HashMap<String, Integer> calculateFood();
}

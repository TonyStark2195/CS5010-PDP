package birds;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The child class Parrots is a class of birds that are known for pets and can mimic human speech.
 * It inherits all the qualities from the parent class and has additional fields corresponding to
 * this class type.
 */
public class Parrots extends Birds {
  private final int numWords;
  private final String favoriteVocab;

  public Parrots(
      String birdType,
      boolean isExtinct,
      int numWings,
      boolean nearWaterBody,
      String uniqueID,
      int numWords,
      String favoriteVocab) {
    super(birdType, isExtinct, numWings, nearWaterBody, uniqueID);

    this.numWords = numWords;
    this.favoriteVocab = favoriteVocab;

    HashMap characteristics = new HashMap();
    HashMap facialFeatures = new HashMap();

    facialFeatures.put("beak_shape", "curved");
    facialFeatures.put("beak_length", "short");
    characteristics.put("special_ability", "mimicry");
    characteristics.put("intelligent_quotient", 10);

    setCharacteristics(characteristics);
    setFacialFeatures(facialFeatures);
  }

  public ArrayList<String> getFoodItems() {
    ArrayList<String> food = new ArrayList<String>();
    food.add(FoodItems.seeds.name());
    food.add(FoodItems.fruit.name());
    food.add(FoodItems.nuts.name());
    food.add(FoodItems.vegetation.name());
    food.add(FoodItems.berries.name());
    return food;
  }

  public String getWaterBody() {
    return null;
  }
}

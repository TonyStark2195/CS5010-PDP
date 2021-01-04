package birds;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The child class ShoreBirds is a class of birds that are known for living near a water body. It
 * inherits all the qualities from the parent class and has additional fields corresponding to this
 * class type.
 */
public class ShoreBirds extends Birds {

  private final String waterBody;

  public ShoreBirds(
      String birdType,
      boolean isExtinct,
      int numWings,
      boolean nearWaterBody,
      String waterBody,
      String uniqueID) {
    super(birdType, isExtinct, numWings, nearWaterBody, uniqueID);

    this.waterBody = waterBody;

    HashMap characteristics = new HashMap<>();
    HashMap facialFeatures = new HashMap<>();

    facialFeatures.put("beak_shape", "sharp");
    facialFeatures.put("beak_length", "long");
    characteristics.put("special_ability", "walks on water");
    characteristics.put("intelligent_quotient", 7);

    setCharacteristics(characteristics);
    setFacialFeatures(facialFeatures);
  }

  public ArrayList<String> getFoodItems() {
    ArrayList<String> food = new ArrayList<String>();
    food.add(FoodItems.seeds.name());
    food.add(FoodItems.aquatic_invertebrates.name());
    food.add(FoodItems.fish.name());
    food.add(FoodItems.larvae.name());
    return food;
  }

  public String getWaterBody() {
    return this.waterBody;
  }
}

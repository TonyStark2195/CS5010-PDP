package birds;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The child class BirdsofPrey is a class of birds that are known for hunting and preying on other
 * birds. It inherits all the qualities from the parent class and has additional fields
 * corresponding to this class type.
 */
public class BirdsOfPrey extends Birds {

  private final String waterBody;

  public BirdsOfPrey(
      String birdType,
      boolean isExtinct,
      int numWings,
      boolean nearWaterBody,
      String waterBody,
      String uniqueID) {
    super(birdType, isExtinct, numWings, nearWaterBody, uniqueID);

    this.waterBody = waterBody;

    HashMap characteristics = new HashMap();
    HashMap facialFeatures = new HashMap();

    facialFeatures.put("beak_shape", "curved");
    facialFeatures.put("beak_length", "short");
    characteristics.put("special_ability", "hunting");
    characteristics.put("intelligent_quotient", 5);

    setCharacteristics(characteristics);
    setFacialFeatures(facialFeatures);
  }

  public ArrayList<String> getFoodItems() {
    ArrayList<String> food = new ArrayList<String>();
    food.add(FoodItems.seeds.name());
    food.add(FoodItems.other_birds.name());
    return food;
  }

  public String getWaterBody() {
    return null;
  }
}

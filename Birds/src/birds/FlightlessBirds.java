package birds;

import java.util.ArrayList;
import java.util.HashMap;

/**
 * The child class FlightlessBirds is a class of birds that are known for not having wings It
 * inherits all the qualities from the parent class and has additional fields corresponding to this
 * class type.
 */
public class FlightlessBirds extends Birds {

  private final String waterBody;

  public FlightlessBirds(
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

    facialFeatures.put("beak_shape", "sharp");
    facialFeatures.put("beak_length", "short");
    characteristics.put("special_ability", "cuteness");
    characteristics.put("intelligent_quotient", 7);

    setCharacteristics(characteristics);
    setFacialFeatures(facialFeatures);
  }

  public ArrayList<String> getFoodItems() {
    ArrayList<String> food = new ArrayList<String>();
    food.add(FoodItems.seeds.name());
    food.add(FoodItems.buds.name());
    food.add(FoodItems.nuts.name());
    food.add(FoodItems.vegetation.name());
    return food;
  }

  public String getWaterBody() {
    return null;
  }
}

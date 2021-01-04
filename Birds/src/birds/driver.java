package birds;

/**
 * This class serves as a lightweight demo to try and run the outputs. Contains the main method that
 * runs the following code of adding 4 birds to the conservatory.
 */
public class driver {
  public static void main(String[] args) {

    System.out.println("Hello world");
    ConservatoryInterface conv = new ConservatoryClass();
    Birds bird1 = new Parrots("parrot", false, 2, false, "Par001", 10, "Good Luck");
    Birds bird2 = new ShoreBirds("shore_birds", false, 2, true, "Lake", "Shore001");
    Birds bird3 = new BirdsOfPrey("birds_of_prey", false, 2, true, "Lake", "Prey001");
    Birds bird4 = new FlightlessBirds("flightless_birds", false, 2, true, "Lake", "Flightless001");

    try {
      System.out.println(
          "number of aviaries in conservatory before " + conv.getAviaryList().size());
      conv.addBirdsToConservatory(bird1);
      System.out.println(
          "number of aviaries in conservatory after add " + conv.getAviaryList().size());

      conv.addBirdsToConservatory(bird2);
      System.out.println(
          "number of aviaries in conservatory after add " + conv.getAviaryList().size());
      conv.addBirdsToConservatory(bird3);
      System.out.println(
          "number of aviaries in conservatory after add " + conv.getAviaryList().size());
      conv.addBirdsToConservatory(bird4);
      System.out.println(
          "number of aviaries in conservatory after add " + conv.getAviaryList().size());
      System.out.println(
          "number of aviaries in conservatory after add " + conv.getAviaryList().get(0).getBirds());
      System.out.println(
          "number of aviaries in conservatory after add " + conv.getAviaryList().get(1).getBirds());
      System.out.println(
          "number of aviaries in conservatory after add " + conv.getAviaryList().get(2).getBirds());

      Directory dir = new Directory(conv);
      System.out.println("\n" + dir.getAviaryFromBird("Flightless001"));
      System.out.println("\n" + dir.listBirds());
      System.out.println("\n" + dir.showAviaries());
      System.out.println("\n" + dir.toString(conv.getAviaryList().get(0)));

    } catch (Exception e) {
      e.printStackTrace();
    }
  }
}

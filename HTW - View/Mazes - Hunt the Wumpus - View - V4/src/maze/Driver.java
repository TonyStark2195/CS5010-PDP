package maze;

public class Driver {
  public static void main(String[] args) throws IllegalArgumentException {
    String input = args[0];

    switch (input.toLowerCase()) {
      case "t":
      case "text":
        TextDriver.main();
        break;
      case "g":
      case "gui":
        View v = new View("HTW Game");
        MVCController mvcController = new MVCController();
        mvcController.setView(v);
        break;
      default:
        throw new IllegalArgumentException("Unexpected Argument Given!\n Try --text or --gui");
    }
  }
}

package maze;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.HashSet;
import java.util.List;
import java.util.Set;

/** This Class is used to represent a room in a maze. */
public class Room {

  private final String description;
  private final int xCoordinate;
  private final int yCoordinate;
  private final List<Character> characters;
  private final List<Treasure> treasures;
  private HashMap<String, Room> neighbors;
  private Set<String> legalActions;

  /**
   * This constructor is used to create a Room. It starts out initially empty.
   *
   * @param description the description of the room (x,y) format
   * @param xCoordinate the x co-ordinate of the room
   * @param yCoordinate the y co-ordinate of the room
   */
  public Room(String description, int xCoordinate, int yCoordinate) {
    this.description = description;
    this.xCoordinate = xCoordinate;
    this.yCoordinate = yCoordinate;

    this.characters = new ArrayList<>();
    this.treasures = new ArrayList<>();
    this.neighbors = new HashMap<>();
    this.legalActions = new HashSet<>();
  }

  /**
   * This method is used to add a character to this room.
   *
   * @param c the character to add
   */
  public void addCharacter(Character c) {
    characters.add(c);
  }

  /**
   * This method is used to remove a character from this room.
   *
   * @param c the character to remove
   * @throws IllegalStateException if they are trying to remove a character from a room they are not
   *     in
   */
  public void removeCharacter(Character c) {
    if (!characters.contains(c)) {
      throw new IllegalStateException("Trying to remove a character from a room they are not in.");
    }
    characters.remove(c);
  }

  /**
   * This method is used to get the characters in this room.
   *
   * @return the characters
   */
  public Character[] getCharacters() {
    return characters.toArray(new Character[0]);
  }

  /**
   * This method is used to add a treasure (gold) to this room.
   *
   * @param t the treasure to add
   */
  public void addTreasure(Treasure t) {
    treasures.add(t);
  }

  /**
   * This method is used to remove a treasure (gold) from this room.
   *
   * @param t the treasure to remove
   */
  public void removeTreasure(Treasure t) {
    if (!treasures.contains(t)) {
      throw new IllegalStateException("Trying to remove a monster from a room they are not in.");
    }
    treasures.remove(t);
  }

  /**
   * This method is used to get the treasures (golds) in this room.
   *
   * @return the treasures in this room
   */
  public Treasure[] getTreasures() {
    return treasures.toArray(new Treasure[0]);
  }

  /**
   * This method is used to add the neighbors of this room.
   *
   * @param neighbors the neighbor rooms of this current room
   */
  public void setNeighbors(HashMap<String, Room> neighbors) {
    this.neighbors = neighbors;
  }

  /**
   * This method is used to get the neighbors of this room.
   *
   * @return the neighbor rooms of this current room
   */
  public HashMap<String, Room> getNeighbors() {
    return this.neighbors;
  }

  /**
   * This method is used to add the legal actions for the player in this room.
   *
   * @param legalActions the legal actions for the player in this room
   */
  public void setLegalActions(Set<String> legalActions) {
    this.legalActions = legalActions;
  }

  /**
   * This method is used to get the legal actions for the player in this room.
   *
   * @return the legal actions for the player in this room
   */
  public Set<String> getLegalActions() {
    return this.legalActions;
  }

  /**
   * This method is used to get the description of this room.
   *
   * @return the description of this room
   */
  public String getDescription() {
    return this.description;
  }

  /**
   * This method is used to get the x co-ordinate of this room.
   *
   * @return the x co-ordinate of this room
   */
  public int getXCoordinate() {
    return this.xCoordinate;
  }

  /**
   * This method is used to get the y co-ordinate of this room.
   *
   * @return the y co-ordinate of this room
   */
  public int getYCoordinate() {
    return this.yCoordinate;
  }

  /**
   * This method is used to override the default toString method and this is used to display the
   * summary of the stats of this room.
   */
  @Override
  public String toString() {
    StringBuilder sb = new StringBuilder(description + ": ");
    sb.append("{ Characters: ");
    if (characters.size() == 0) {
      sb.append("None;");
    } else {
      for (Character c : characters) {
        sb.append(c + ";");
      }
    }
    sb.append(" Treasures: ");
    if (treasures.size() == 0) {
      sb.append("None");
    } else {
      for (Treasure t : treasures) {
        sb.append(t);
      }
    }
    sb.append(" }");
    return sb.toString();
  }
}

Course:     CS5010 Program Design Paradigm 
Term:       Fall '20
Assignment: Assignment 2 - Role Playing
Author:     Aswin Shriram Thiagarajan
Date:       08 Octiber 2020
Folders:    src     -> contains all the source code files (.java)
            test    -> contains all the test case classes
            res     -> contains all the results related to the assignment


Description:

In many role-playing games, characters go into battle with some degree of attack and defense capabilities (represented as numerical values). These values can be modified by “wearing” different articles of clothing to either increase performance (attack) or minimize damage inflicted by other characters (defense). This assignment, there are following types of clothing.

- Head gear: These items go on the character’s head (hats/helmets/visors) and are only useful for defense.

- Hand gear: These items go on the character’s hands (gloves/swords/shield) and are only used for attack. Since you have 2 hands, you can have 2 of these items.

- Footwear: These items go on the character’s feet (boots/sneakers/hoverboard) and can be for attack or defense. Since you have 2 feet, you can have 2 of these items.

Each item has a name consisting of an adjective and a noun, in that order, and the amount that it modifies the character’s attack and defense values.

Combining Items

When the game starts out, the characters start with an attack power and a defense strength. As they go through the game, they can pick up new items to add to those two values. The following rules apply:

+ They cannot pick up more items that they can hold. This means that they are limited to:

	- One piece of head gear

	- Two pieces of footwear

	-Two pieces of hand gear

+ The character’s values do not change.

+ To be clever, when a character picks up two items of the same type, their names are combined, they make a new piece of footwear that combines the powers and name. The new name is the adjective from one item and the full name from the other. For instance, to combine

	- Scurrying Sandals -- defense strength: 1, attack strength 0

	- Happy HoverBoard -- defense strength: 3, attack strength 1

You get

	- Scurrying, Happy HoverBoard -- defense strength: 4, attack strength 1

+ Only items of the same type have their names combined.

+ Only two items may be combined; a combined item may not be combined with another item.

How to Run:

- The src/roleplaying/Driver.java file contains the main() method to run a lightweight demo.
	* To play the game, instantiate the Battle object. And follow the commands.

	Battle endGame = new Battle();
    try {
      endGame.prepareRound(); // To prepare the battle field, where the characters equip the battle gears
    } catch (Exception e) {
      System.out.println("Error in preparing Game: " + e);
    }
    endGame.playGame(); // Play the game between the 2 players

    System.out.println(endGame.toString()); // Prints the winner along with the battle summary

    * Further, you can refer to this Driver.java file to look how varied inputs can be provided.

- The src/roleplaying/Battle.java file contains the platform to play the battle between 2 players and the winner is announced. This class encompasses all the functionalities that is required to battle.

- The src/roleplaying/AbstractGear.java contains all the functionalities of a gear object of the game. This is an abstract class and it encapsulates the functionalities of the children classes HandGear (HandGear.java), HeadGear (HeadGear.java), FootGear (FootGear.java). The gear is used to enhance the character's abilities in battle.

- The src/roleplaying/Abstract.java contains all the functionalities of a gear object of the game. The gear is used to enhance the character's abilities in battle.
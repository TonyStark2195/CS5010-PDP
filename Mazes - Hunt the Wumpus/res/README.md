Course:     CS5010 Program Design Paradigm 
Term:       Fall '20
Assignment: Assignment 5 - Hunt the Wumpus
Author:     Aswin Shriram Thiagarajan
Date:       17 November 2020
Folders:    src     -> contains all the source code files (.java)
            test    -> contains all the test case classes
            res     -> contains all the results related to the assignment


* Hunt the Wumpus:
    
    It is a text-based adventure game where a player navigates through a series of caves hunting a monster called the Wumpus. The game consists of a maze of caves connected by tunnels where several of the caves contain obstacles in the form of bottomless pits and superbats, as well as the Wumpus who is hiding out in one of the caves ready to eat the player.

    A player that encounters a bottomless pit falls to their death. A player that encounters a superbat has a chance of being whisked away and dropped in some other random cave in the maze. A player that encounters the Wumpus is eaten. To win the game, a player can shoot a crooked arrow into a neighboring cave. If the arrow encounters the Wumpus, it kills the monster and the player wins the game. The player has only a limited number of arrows in their quiver; if they shoot the arrow into a cave with no Wumpus, the arrow is lost. If the player runs out of arrows, they lose the game.

    A player cannot see into adjacent caves. However, to help them navigate the world, they can see tunnels moving into the darkness. In addition, when a player enters a cave, they can smell if the Wumpus is nearby (namely, in an adjacent cave) or feel a draft from a nearby pit. There is no warning for the superbats.

    The HTW game is based on the maze game from Assignment 3. Below is the description of the maze game.

                              North

                    #########################
                    #                       #
                    # (0,0)   (0,1)   (0,2) #
                    # Player                #
                    ################        #
                    #       #               #
                    # (1,0) # (1,1)   (1,2) #
                    #  Pit  #               #
            West    #               #########   East
                    #                       #
                    # (2,0)   (2,1)   (2,2) #
                    #                  Bat  #
                    #               #########
                    #       #               #
                    # (3,0) # (3,1)   (3,2) #
                    #       #        Wumpus #
                    #########################
                    
                              South

* Maze:

    This is a maze game where the player can interact with the game. The game provides intuitive guide for the user to input. A little bit about the game description, design and how you can play.

* Game Description:

    Wikipedia says "A maze is a path or collection of paths, typically from an entrance to a goal." In this game, a player starts from a starting position and the user will guide the player to reach the goal position by a set of action commands. This game consists of few types of mazes.

    A perfect maze is the simplest type of maze for a computer to generate and solve. It is defined as a maze which has one and only one path from any point in the maze to any other point in the maze. This means that the maze has no inaccessible sections, no circular paths, no open areas.

                            North

                    #########################
                    #                       #
                    # (0,0)   (0,1)   (0,2) #
                    # Start                 #
                    ################        #
                    #       #               #
                    # (1,0) # (1,1)   (1,2) #
                    #       #               #
            West    #               #########  East
                    #                       #
                    # (2,0)   (2,1)   (2,2) #
                    #                       #
                    #               #########
                    #       #               #
                    # (3,0) # (3,1)   (3,2) #
                    #       #         Goal  #
                    #########################
                    
                            South

    Each cell in the grid represents a location in the maze that has a potential exit to the north, south, east, and west. One way to look at the perfect maze is that each location is a hallway that twists and turns its way from one place to another. The challenge for this type of maze is to find the direct path from one place (the pink square) to another (the blue one).

    In the non-perfect maze, each cell in the grid also represent a location in the maze, but there can be multiple paths between any two cells. This form is useful in several applications. Computer games, for instance, use this kind of maze to create a map of the world by giving locations in the maze different characteristics.

                             North

                    #########################
                    #                       #
                    # (0,0)   (0,1)   (0,2) #
                    # Start                 #
                    #########               #
                    #       #               #
                    # (1,0) # (1,1)   (1,2) #
                    #       #               #
            West    #               #########  East
                    #                       #
                    # (2,0)   (2,1)   (2,2) #
                    #                       #
                    #                       #
                    #       #               #
                    # (3,0) # (3,1)   (3,2) #
                    #       #         Goal  #
                    #########################

                            South

    For instance, a room maze categorizes locations in the maze as either rooms or hallways, where a hallway has exactly two doors while a room has 1, 3 or 4 doors. In this example of a room maze, the locations at the top, bottom, left and right can "wrap" to the other side, we call this a wrapping room maze.

                            North

                    #########        ########
                            #              
                      (0,0) # (0,1)   (0,2) 
                      Start #               
                    ################        
                    #       #               #
                    # (1,0) # (1,1)   (1,2) #
                    #       #               #
            West    #               #########  East
                    #                       #
                    # (2,0)   (2,1)   (2,2) #
                    #                       #
                    #               #########
                    #       #               #
                    # (3,0) # (3,1)   (3,2) #
                    #       #         Goal  #
                    #########       #########

                            South


* Game Design:
    
    * Algorithm for building mazes
        
        There are a number of algorithms for building perfect mazes including Kruskal's algorithm and Prim's algorithm. Each of these requires starting with an undirected graph whose nodes represents locations in the maze, and whose edges represent walls between locations. For this game, I have implemented using a modified version of the Kruskal's algorithm.

    * The HTW game is built on top of Maze game.

    * The game consists of followin objects and their respective roles.
        - Player - Plays the game to kill the Wumpus monster
        - Wumpus - Monster that tries to eat the player
        - SuperBat - The bat which may carry the player to a random cave
        - Bottomless Pit - The pit will kill the player when entered
        - Arrow - An object that is used to kill the wumpus monster

    * The objects can only be in caves and not in tunnels. Any move that takes a player from one place to another will be in caves, and tunnels are just pathways connecting caves.

    * The game is interactive and you will play the game typing commands in the terminal.


* How to Play:

    * Go to the folder where the .jar file is present. Currently the .jar is in the same folder.

    * Open a terminal in this location.

    * In terminal, type java -jar Mazes.jar and press Enter.

    * You will see the game menu asking for what type of Maze you wanna play with.
        - p (Perfect)
        - np (Non Perfect)

    * Type your choice on the terminal as displayed.

    * Then the game will prompt what sub type of maze you wanna play with.
        - w (Wrapping)
        - nw (Non Wrapping)

    * Type your choice on the terminal as displayed.

    * Based on your choices, the game will ask to enter the number of rows and columns the maze should have.
        - You should enter some number above 0 and then hit Enter. (for row)
        - You should enter some number above 0 and then hit Enter. (for column)

    * Then if your maze is Non Perfect, it will ask for remaining number of walls.
        - You should enter some number above 0 and then hit Enter. (for number of walls)

    * The game environment will now be generated.

    * The game will now prompt to enter the percentage of bats and percentage of pits to be present in the game.
        - You should enter some number above 0 to 40 (ideally) and then hit Enter. (for percentage of bats)
        - You should enter some number above 0 to 40 (ideally) and then hit Enter. (for percentage of pits)

    * The game objects will now be generated in the game.

    * Now the game is created with the player in the random starting position and spawns bats, pits and wumpus in some random locations.

    * The game displayes the current location, whether to shoot or move, actions available and other messages whether a wumpus or pit is nearby and displays the message of victory or loss.

    * The game will ask the user whether to shoot or move.

    * The game will display where the player is and also give the choice of legal actions the user can make at that location. The game now expects the user to input the action that will move the player to the corresponding location.
        - Enter the action from the list of actions given and hit Enter.

    * This will move the player to that corresponding location.

    * When the user inputs an incorrect action, they will stay in the same location.

    * The user can play the game to collect golds and avoiding thieves and will have to kill the wumpus.

    * The game terminates when the player kills the wumpus or gets killed by the wumpus or the pits.


* Folder Structure:

- The src/Mazes/Driver.java and Controller.java file contains the main code that will enable the user to interact with the game. This is analogous to the Controller of the MVC paradigm.

- The src/Mazes/WrappingMaze.java, src/Mazes/NonWrappingMaze.java, src/Mazes/Maze.java. These are the classes that are responsible for the logic of the maze of the game. This is analogous to the Model of the MVC paradigm.

- The src/Mazes/Room.java, src/Mazes/Character.java, src/Mazes/Treasure.java. These are the classes that are responsible for the objects in the maze game. This is also part of the Model of the MVC paradigm.
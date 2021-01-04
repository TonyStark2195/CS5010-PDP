Course:     CS5010 Program Design Paradigm 
Term:       Fall '20
Assignment: Assignment 3 - Mazes
Author:     Aswin Shriram Thiagarajan
Date:       19 October 2020
Folders:    src     -> contains all the source code files (.java)
            test    -> contains all the test case classes
            res     -> contains all the results related to the assignment


Welcome to the maze of the complex dimension, here whatever you knew before is defied! Expect the unexpected!!! The directions in this maze is little twisted and this is the challenging part of the game.

This is a maze game where the player can interact with the game. The game provides intuitive guide for the user to input. A little bit about the game description, design and how you can play.

* Game Description:

    Wikipedia says "A maze is a path or collection of paths, typically from an entrance to a goal." In this game, a player starts from a starting position and the user will guide the player to reach the goal position by a set of action commands. This game consists of few types of mazes.

    A perfect maze is the simplest type of maze for a computer to generate and solve. It is defined as a maze which has one and only one path from any point in the maze to any other point in the maze. This means that the maze has no inaccessible sections, no circular paths, no open areas.

                            West

                    #########################
                    #                       #
                    # (0,0)   (0,1)   (0,2) #
                    # Start                 #
                    ################        #
                    #       #               #
                    # (1,0) # (1,1)   (1,2) #
                    #       #               #
            North   #               #########  South
                    #                       #
                    # (2,0)   (2,1)   (2,2) #
                    #                       #
                    #               #########
                    #       #               #
                    # (3,0) # (3,1)   (3,2) #
                    #       #         Goal  #
                    #########################
                    
                            East

    Each cell in the grid represents a location in the maze that has a potential exit to the north, south, east, and west. One way to look at the perfect maze is that each location is a hallway that twists and turns its way from one place to another. The challenge for this type of maze is to find the direct path from one place (the pink square) to another (the blue one).

    In the non-perfect maze, each cell in the grid also represent a location in the maze, but there can be multiple paths between any two cells. This form is useful in several applications. Computer games, for instance, use this kind of maze to create a map of the world by giving locations in the maze different characteristics.

                             West

                    #########################
                    #                       #
                    # (0,0)   (0,1)   (0,2) #
                    # Start                 #
                    #########               #
                    #       #               #
                    # (1,0) # (1,1)   (1,2) #
                    #       #               #
            North   #               #########  South
                    #                       #
                    # (2,0)   (2,1)   (2,2) #
                    #                       #
                    #                       #
                    #       #               #
                    # (3,0) # (3,1)   (3,2) #
                    #       #         Goal  #
                    #########################

                            East

    For instance, a room maze categorizes locations in the maze as either rooms or hallways, where a hallway has exactly two doors while a room has 1, 3 or 4 doors. In this example of a room maze, the locations at the top, bottom, left and right can "wrap" to the other side, we call this a wrapping room maze.

                            West

                    #########        ########
                            #              
                      (0,0) # (0,1)   (0,2) 
                      Start #               
                    ################        
                    #       #               #
                    # (1,0) # (1,1)   (1,2) #
                    #       #               #
            North   #               #########  South
                    #                       #
                    # (2,0)   (2,1)   (2,2) #
                    #                       #
                    #               #########
                    #       #               #
                    # (3,0) # (3,1)   (3,2) #
                    #       #         Goal  #
                    #########       #########

                            East


* Game Design:
    
    * Algorithm for building mazes
        
        There are a number of algorithms for building perfect mazes including Kruskal's algorithm and Prim's algorithm. Each of these requires starting with an undirected graph whose nodes represents locations in the maze, and whose edges represent walls between locations. For this game, I have implemented using a modified version of the Kruskal's algorithm.


    * The Game's directions are twisted to make the game interesting and tough.

    * The game is interactive and you will play the game typing commands in the terminal.


* How to Play:

    * Go to the folder where the .jar file is present. Currently the .jar is in the same folder.

    * Open a terminal in this location.

    * In terminal, type java -jar Mazes.jar and press Enter.

    * You will see the game menu asking for what type of Maze you wanna play with.
        - Perfect
        - Non Perfect

    * Type your choice on the terminal as displayed.

    * Then the game will prompt what sub type of maze you wanna play with.
        - Wrapping
        - Non Wrapping

    * Type your choice on the terminal as displayed.

    * Based on your choices, the game will ask to enter the number of rows and columns the maze should have.
        - You should enter some number above 0 and then hit Enter. (for row)
        - You should enter some number above 0 and then hit Enter. (for column)

    * Then if your maze is Non Perfect, it will ask for remaining number of walls.
        - You should enter some number above 0 and then hit Enter. (for number of walls)

    * The maze will now be generated.

    * The game will now prompt to enter the co ordinates of the player's starting location.
        - You should enter some number above 0 and then hit Enter. (for X)
        - You should enter some number above 0 and then hit Enter. (for Y)

    * The game will now prompt to enter the co ordinates of the goal location.
        - You should enter some number above 0 and then hit Enter. (for X)
        - You should enter some number above 0 and then hit Enter. (for Y)

    * Now the game is created with the player in the starting position and spawns gold in some random locations and thief in some random locations.

    * The game displayes the walls present, and the whole environment (map).

    * The game will display where the player is and also give the choice of legal actions the user can make at that location. The game now expects the user to input the action that will move the player to the corresponding location.
        - Enter the action from the list of actions given and hit Enter.

    * This will move the player to that corresponding location.

    * When the user inputs an incorrect action, they will stay in the same location.

    * The user can play the game to collect golds and avoiding thieves and will have to reach the goal location.

    * The game terminates when the player reaches the goal.


* Folder Structure:

- The src/Mazes/Driver.java file contains the main code that will enable the user to interact with the game. This is analogous to the Controller of the MVC paradigm.

- The src/Mazes/WrappingMaze.java, src/Mazes/NonWrappingMaze.java, src/Mazes/Maze.java. These are the classes that are responsible for the logic of the maze of the game. This is analogous to the Model of the MVC paradigm.

- The src/Mazes/Room.java, src/Mazes/Character.java, src/Mazes/Treasure.java. These are the classes that are responsible for the objects in the maze game. This is also part of the Model of the MVC paradigm.
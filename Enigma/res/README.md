Course:     CS5010 Program Design Paradigm 
Term:       Fall '20
Assignment: Assignment 4 - Coding Trees
Author:     Aswin Shriram Thiagarajan
Date:       04 November 2020
Folders:    src     -> contains all the source code files (.java)
            test    -> contains all the test case classes
            res     -> contains all the results related to the assignment


The goal of this assignment is to design and implement a program that can encode and decode data using prefix encodings.

* Codes:

    Encoding and decoding are common operations on data. Given data in the form of symbols (e.g. text), it can be encoded by translating each symbol into a unique code, possibly consisting of many symbols. Decoding applies this process in reverse. The unique codes may be made of a different set of symbols (e.g. the original symbols may be characters, but the codes are bits). We call this set "coding symbols".

    The main component of encoding and decoding can be thought of as a symbol -> code dictionary representing a coding scheme. For example, consider that all input messages are made of symbols {a,b,c,d,e}, and the coding symbols are {0,1}. An example dictionary could be {a -> 10, b -> 1100, c -> 1101, d -> 01, e -> 00}. Then, an input message "dab" will be encoded as "01101100" by replacing the symbols 'd', 'a', 'b' with their respective codes from the dictionary. Similarly, an encoded string "11001001" will be decoded to "bad" using the same dictionary.

    Coding schemes may have some unique characteristics. For example, certain encoding schemes generate codes such that no code is a prefix of another code (e.g. 10 and 101 cannot both be codes, because the first is a prefix of the second). Such schemes are called prefix codes and are often used since a sequence of codes then requires no delimiters between codes, and will have only one possible decoding. You can verify that the dictionary in the above example shows a prefix coding scheme. One way to look at prefix codes is by using prefix trees.

    As another example, consider the dictionary D:

    a:100
    b:00
    c:01
    d:11
    e:101

    Formally, in this example, the original symbol set is {a,b,c,d,e} and the code symbols are {0,1}. We can see that the above code is a prefix code meaning that no code is a prefix of another. Thus it can be represented using a tree U:

                       O
                0           1
            O                    O
          0    1             0       1
       b         c         O            d
                        0    1
                      a        e

    All the original symbols are leaves in this tree. The others are transition nodes. Since there are only two code symbols, this is a binary tree. Each edge of the tree is annotated (for illustration) with a code symbol (left is 0, right is 1).

A complete coding tree is full: each transition node has children to its full capacity (2 above, since the tree is binary).

* Decoding
    Given an encoded message "10001101", we can decode it as follows:

    Start at the root of the tree.
    Read the next symbol.
    Turn "left" or "right" depending on the read symbol.
    If a leaf is reached, output the character at that leaf, restart at the root.
    Go to step 2.
    This process is illustrated for the decoding "101" into 'e':


    Applying this process to the encoded message "10001101": we use the first three symbols "100" to arrive at 'a', the next two symbols "01" to arrive at 'c' and the last three symbols "101" to arrive at 'e'. Thus the encoded message "10001101" decodes to the string "ace".

* Encoding
    How is a prefix code generated? One can arbitrarily assign a prefix code for a symbol set. However it is possible to generate "better" prefix codes. One way is to use a scheme called Huffman encoding. This encoding generates a custom coding scheme for the message to be encoded, such that the length of the encoded message (in terms of number of symbols) is minimized.

    The Huffman encoding algorithm to generate a binary prefix code, given the source message M is as follows:

    - Create a frequency table (a,f(a)) where a is a symbol in M and f(a) is the number of times a occurs in M.
    - Initialize a coding table (a,g(a)) where a is a symbol in M and g(a) is the desired code for a. All codes are initially empty.
    - Add all symbols into a priority queue Q, using their frequencies as priorities. When the frequencies are the same, the item that is lexicographically earlier has the lower priority. In general Q contains items with one or more symbols in them.
    - Pop 2 items x and y from the queue with the lowest frequencies f(x) and f(y) respectively.
    - For each symbol xi in x, add a prefix of 0 to g(xi). Similarly, for each symbol yi in y, add a prefix of 1 to g(yi).
    - Add a new entry (x.y, f(x)+f(y)) to Q, where x.y is the concatenation of x and y.
    - If there is more than one item in Q, go to step 4. Otherwise, report the resulting coding table.
    - The above algorithm generates binary codes, i.e. its coding symbol set is {0,1}.

* How to use the demo:

    * Go to the folder where the .jar file is present. Currently the .jar is in the same folder.

    * Open a terminal in this location.

    * In terminal, type java -jar Enigma.jar and press Enter. (Alternative option is to run the driver.class file)

    * Below is how the opening interface will look like.

    * Enter the type of demo you want to try (in integer format): 
        1. Read from User 
        2. Read from File 
        3. Save to File 
        4. Print to Screen 
        5. Encode Decode 
        6. Default Easy Encoder 
        7. Custom Encoder 
        8. Default Decoder
        0. Exit

    * The interface is intuitive and it will ask the user for input message (from keyboard or file), input prefix code dictionary (from keyboard or file or generated in its own in case of huffman), encoded message (from keyboard or file).

    * The user can navigate through the menus and can try out different encoding, decoding and combination of both.


* Folder Structure:

- The src/Enigma/Driver.java file contains the main code that will enable the user to interact with the coding tree. There is a Controller.java file which acts as the Controller of the MVC paradigm.

- The src/Enigma/Encoder.java, src/Enigma/Decoder.java. These are some of the interfaces that are responsible for the logic of this project. This is analogous to the Model of the MVC paradigm.

- The model consists of several classes and sub-classes to take care of the encoding and decoding of the coding tree. There are some special cases of Huffman Binary and hexadecimal encoding.
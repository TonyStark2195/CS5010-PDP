package maze;

import java.awt.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.ItemEvent;
import java.awt.event.ItemListener;
import java.io.IOException;
import java.util.function.Consumer;

import javax.swing.*;
import javax.swing.event.DocumentEvent;
import javax.swing.event.DocumentListener;

public class View extends JFrame {
  private static final long serialVersionUID = 2784780451341216762L;

  private JButton commandButton, quitButton;
  private JLabel display1, display2, display3;
  private JRadioButton radioButton1, radioButton2, radioButton3, radioButton4;
  ButtonGroup G1, G2;
  JButton generateButton;
  private JPanel panel1, panel2, panel3, panel4;
  //  private TurtlePanel turtlePanel;
  private JScrollPane scrollPane;
  private JTextField input1, input2, input3, input4, input5;
  private transient Consumer<String> commandCallback;
  /** Default constructor. */
  public View(String caption) {

    super(caption);

    this.setTitle("Hunt the Wumpus!");
    //    this.setSize(500, 500);
    setLocation(50, 200);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    //    Panel 1
    panel1 = new JPanel();
    panel1.setLayout(new FlowLayout());
    panel1.setPreferredSize(new Dimension(500, 100));
    this.add(panel1, BorderLayout.NORTH);

    display1 = new JLabel("Enter the type of Maze to Play: ");
    panel1.add(display1);

    // Initialization of object of "JRadioButton" class.
    radioButton1 = new JRadioButton("Perfect Maze");
    panel1.add(radioButton1);
    // Initialization of object of "JRadioButton" class.
    radioButton2 = new JRadioButton("Non-Perfect Maze");
    panel1.add(radioButton2);
    // Initialization of object of "JButton" class.

    // Initialization of object of "ButtonGroup" class.
    G1 = new ButtonGroup();

    // Adding "jRadioButton1" and "jRadioButton3" in a Button Group "G2".
    G1.add(radioButton1);
    G1.add(radioButton2);

    display2 = new JLabel("\nEnter the Sub-type of Maze to Play: ");
    panel1.add(display2);
    // Initialization of object of "JRadioButton" class.
    radioButton3 = new JRadioButton("Wrapping Maze");
    panel1.add(radioButton3);
    // Initialization of object of "JRadioButton" class.
    radioButton4 = new JRadioButton("Non-Wrapping Maze");
    panel1.add(radioButton4);

    //    JTextField - getters and setters

    // Initialization of object of "ButtonGroup" class.
    G2 = new ButtonGroup();

    // Adding "jRadioButton1" and "jRadioButton3" in a Button Group "G2".
    G2.add(radioButton3);
    G2.add(radioButton4);

    // Panel 2
    panel2 = new JPanel();
    panel2.setLayout(new BorderLayout());
    panel2.setPreferredSize(new Dimension(600, 100));
    this.add(panel2, BorderLayout.CENTER);

    JPanel tempA = new JPanel(new FlowLayout());
    panel2.add(tempA, BorderLayout.NORTH);
    JPanel tempB = new JPanel(new FlowLayout());
    panel2.add(tempB, BorderLayout.CENTER);
    JPanel tempC = new JPanel(new FlowLayout());
    panel2.add(tempC, BorderLayout.SOUTH);

    display3 = new JLabel("Enter the number of rows: r (Integer)");
    tempA.add(display3);

    input1 = new JTextField(3);
    tempA.add(input1);

    display3 = new JLabel("Enter the number of columns: c (Integer)");
    tempB.add(display3);

    input2 = new JTextField(3);
    tempB.add(input2);

    //    Panel 4
    panel4 = new JPanel(new BorderLayout());
    this.add(panel4, BorderLayout.SOUTH);
    //    Panel 3
    panel3 = new JPanel();
    panel3.setLayout(new BorderLayout());
//    panel3.setPreferredSize(new Dimension(500, 150));
    panel4.add(panel3, BorderLayout.NORTH);

    JPanel temp1 = new JPanel(new FlowLayout());
    JPanel temp2 = new JPanel(new FlowLayout());
    JPanel temp3 = new JPanel(new FlowLayout());
    panel3.add(temp1, BorderLayout.NORTH);
    panel3.add(temp2, BorderLayout.CENTER);
    panel3.add(temp3, BorderLayout.SOUTH);

    display3 = new JLabel("Enter the number of remaining walls: w (Integer)");
    tempC.add(display3, BorderLayout.NORTH);
    input3 = new JTextField(3);
    tempC.add(input3, BorderLayout.NORTH);
    //    input3.enableInputMethods(false);
    input3.setEnabled(false);

    display3 = new JLabel("Enter the percentage of Super Bats (0 - 40)");
    temp2.add(display3, BorderLayout.CENTER);
    input4 = new JTextField(3);
    temp2.add(input4, BorderLayout.CENTER);
    //    input3.enableInputMethods(false);
    input4.setEnabled(false);

    display3 = new JLabel("Percentage of Bottomless pits (0 - 30)");
    temp3.add(display3, BorderLayout.SOUTH);
    input5 = new JTextField(3);
    temp3.add(input5, BorderLayout.SOUTH);
    //    input3.enableInputMethods(false);
    input5.setEnabled(false);

    generateButton = new JButton("Generate Game");
    panel4.add(generateButton);

    radioButton2.addItemListener(
        new ItemListener() {
          @Override
          public void itemStateChanged(ItemEvent e) {
            if (e.getStateChange() == ItemEvent.SELECTED) {
              input3.setEnabled(true);
            } else if (e.getStateChange() == ItemEvent.DESELECTED) {
              input3.setEnabled(false);
            }
          }
        });

    input2
        .getDocument()
        .addDocumentListener(
            new DocumentListener() {

              @Override
              public void removeUpdate(DocumentEvent e) {
                input4.setEnabled(false);
                input5.setEnabled(false);
              }

              @Override
              public void insertUpdate(DocumentEvent e) {
                input4.setEnabled(true);
                input5.setEnabled(true);
              }

              @Override
              public void changedUpdate(DocumentEvent arg0) {}
            });

    //    // Adding Listener to JButton.
    //    generateButton.addActionListener(
    //        new ActionListener() {
    //          // Anonymous class.
    //
    //          public void actionPerformed(ActionEvent e) {
    //            // Override Method
    //
    //            // Declaration of String class Objects.
    //            String qual = " ";
    //
    //            // If condition to check if jRadioButton2 is selected.
    //            if (radioButton1.isSelected()) {
    //
    //              display1 = new JLabel("Enter the type of Maze to Play: ");
    //              this.add(display1);
    //
    //            } else if (radioButton1.isSelected()) {
    //
    //              qual = "Graduate";
    //            } else {
    //
    //              qual = "NO Button selected";
    //            }
    //
    //            // MessageDialog to show information selected radion buttons.
    //            JOptionPane.showMessageDialog(Demo.this, qual);
    //          }
    //        });

    //    // the text field
    //    input = new JTextField(10);
    //    this.add(input);
    //
    //    // echo button
    //    echoButton = new JButton("Echo");
    //    echoButton.setActionCommand("Echo Button");
    //    this.add(echoButton);
    //
    //    // exit button
    //    exitButton = new JButton("Exit");
    //    exitButton.setActionCommand("Exit Button");
    //    this.add(exitButton);

    this.pack();
    this.setVisible(true);

    //    ########################
    //
    //    // use a borderlayout with drawing panel in center and button panel in south
    //    this.setLayout(new GridLayout(2, 2));
    //    turtlePanel = new TurtlePanel();
    //    turtlePanel.setPreferredSize(new Dimension(500, 500));
    //    scrollPane = new JScrollPane(turtlePanel);
    //    this.add(scrollPane, BorderLayout.CENTER);
    //
    //    // button panel
    //    buttonPanel = new JPanel();
    //    buttonPanel.setLayout(new FlowLayout());
    //    this.add(buttonPanel, BorderLayout.SOUTH);
    //
    //    // input textfield
    //    input = new JTextField(15);
    //    buttonPanel.add(input);
    //
    //    // buttons
    //    commandButton = new JButton("Execute");
    //    commandButton.addActionListener(
    //        (ActionEvent e) -> {
    //          if (commandCallback != null) { // if there is a command callback
    //            commandCallback.accept(input.getText()); // send command to be processed
    //            input.setText(""); // clear the input text field
    //          }
    //        });
    //    buttonPanel.add(commandButton);
    //
    //    // quit button
    //    quitButton = new JButton("Quit");
    //    quitButton.addActionListener(
    //        (ActionEvent e) -> {
    //          System.exit(0);
    //        });
    //    buttonPanel.add(quitButton);
    //
    //    commandCallback = null;
    //
    //    this.pack();
  }

  /**
   * Accept the set of callbacks from the controller, and hook up as needed to various things in
   * this view.
   *
   * @param mvcController the set of feature callbacks as a Features object
   */
  //  @Override
  //  public void setFeatures(MVCController mvcController) {
  //    String perfect_temp = new String(), wrapping_temp = new String();
  //    if (radioButton1.isSelected()) {
  //      System.out.println("R1");
  //      perfect_temp = new String("p");
  //    }
  //    if (radioButton2.isSelected()) {
  //      System.out.println("R2");
  //      perfect_temp = new String("np");
  //    }
  //    if (radioButton3.isSelected()) {
  //      System.out.println("R3");
  //      wrapping_temp = new String("w");
  //    }
  //    if (radioButton4.isSelected()) {
  //      System.out.println("R4");
  //      wrapping_temp = new String("nw");
  //    }
  //
  //    System.out.println("Input 1: Row " + input1.getText());
  //    System.out.println("Input 2: Column " + input1.getText());
  //
  //    if (commandCallback != null) {
  //      int r = Integer.parseInt(String.valueOf(input1.getText()));
  //      int c = Integer.parseInt(String.valueOf(input2.getText()));
  //      int w_temp;
  //      try {
  //        w_temp = Integer.parseInt(String.valueOf(input3.getText()));
  //      } catch (Exception e) {
  //        System.out.println("No number of remaining walls given! Taking default.");
  //        w_temp = 0;
  //      }
  //
  //      final String perfect = perfect_temp, wrapping = wrapping_temp;
  //      final int w = w_temp;
  //
  //      generateButton.addActionListener(
  //          new ActionListener() {
  //            public void actionPerformed(ActionEvent e) {
  //              JOptionPane.showMessageDialog(null, "Hello World");
  //              System.out.println("Before");
  //              mvcController.processInput(perfect, wrapping, r, c, w);
  //              System.out.println("After");
  //            }
  //          });
  //      //      generateButton.addActionListener(l -> mvcController.processInput(perfect,
  // wrapping, r,
  //      // c, w));
  //    }
  //  }

  public void setFeatures(MVCController mvcController) {

    generateButton.addActionListener(
        new ActionListener() {
          public void actionPerformed(ActionEvent e) {
            String perfect_temp = new String("p"), wrapping_temp = new String("w");
            if (radioButton1.isSelected()) {
              System.out.println("R1");
              perfect_temp = new String("p");
            }
            if (radioButton2.isSelected()) {
              System.out.println("R2");
              perfect_temp = new String("np");
            }
            if (radioButton3.isSelected()) {
              System.out.println("R3");
              wrapping_temp = new String("w");
            }
            if (radioButton4.isSelected()) {
              System.out.println("R4");
              wrapping_temp = new String("nw");
            }

            System.out.println("Input 1: Row " + input1.getText());
            System.out.println("Input 2: Column " + input2.getText());

            //            if (commandCallback != null) {
            int r = Integer.parseInt(String.valueOf(input1.getText()));
            int c = Integer.parseInt(String.valueOf(input2.getText()));
            int w_temp;
            try {
              w_temp = Integer.parseInt(String.valueOf(input3.getText()));
            } catch (Exception exception) {
              System.out.println("No number of remaining walls given! Taking default.");
              w_temp = 0;
            }

            int bats = Integer.parseInt(String.valueOf(input4.getText()));
            int pits = Integer.parseInt(String.valueOf(input5.getText()));

            final String perfect = perfect_temp, wrapping = wrapping_temp;
            final int w = w_temp;

            //              JOptionPane.showMessageDialog(null, "Hello World");
            System.out.println("Before");
            try {
              mvcController.processInput(perfect, wrapping, r, c, w, bats, pits);
            } catch (IOException ioException) {
              ioException.printStackTrace();
            }
            System.out.println("After");
            //            }
          }
          //      generateButton.addActionListener(l -> mvcController.processInput(perfect,
          // wrapping, r,
          // c, w));
        });
  }

  // Driver code.
  public static void main(String[] args) { // Creating object of demo class.
    View v = new View("HTW Radio");
    MVCController mvcController = new MVCController();
    mvcController.setView(v);
    //    v.setVisible(true);
  }
}

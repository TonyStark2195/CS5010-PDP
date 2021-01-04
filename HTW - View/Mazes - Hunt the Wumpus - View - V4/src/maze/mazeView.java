package maze;

import java.awt.*;

import java.awt.GridLayout;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.event.KeyEvent;
import java.awt.event.KeyListener;
import java.awt.event.MouseEvent;
import java.awt.event.MouseListener;
import java.awt.image.BufferedImage;
import java.io.File;
import java.io.IOException;
import java.util.ArrayList;
import java.util.Arrays;
import java.util.HashSet;
import java.util.List;
import java.util.Set;
import java.util.function.Consumer;

import javax.imageio.ImageIO;
import javax.swing.*;

public class mazeView extends JFrame {
  private static final long serialVersionUID = 2784780451341216762L;

  private JButton commandButton, quitButton;
  private JLabel display1, display2, display3, display4;
  private JRadioButton radioButton1, radioButton2, radioButton3, radioButton4;
  private ButtonGroup G1, G2;
  private JButton exitButton, shootButton, moveButton;
  private JPanel panel1, panel2, panel3;
  //  private TurtlePanel turtlePanel;
  private JScrollPane scrollPane;
  private JTextField input1, input2, input3, input4;
  private transient Consumer<String> commandCallback;
  private Set<Room> visitedRooms;
  private int posX, posY;

  public mazeView(String caption, MVCController controller) throws IOException {
    super();
    this.setTitle(caption);
    this.setSize(500, 500);
    this.setDefaultCloseOperation(JFrame.EXIT_ON_CLOSE);

    this.setLayout(new BorderLayout());

    visitedRooms = new HashSet<>();

    //    ImageIcon grassIcon =
    //        //        new ImageIcon(
    //        //            "D:\\Fall 2020\\CS5010 Programming Design Paradigm\\Projects\\HTW -
    //        // View\\htw-view-images\\res\\roombase-4.png");
    //        new ImageIcon("hunt-the-wumpus-images/NSEW.png");
    //    Image scaleImage = grassIcon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
    //    ImageIcon imageIcon = new ImageIcon(scaleImage);

    Maze model = controller.getModel();

    Room tempCurr = model.getCurrentRoom();
    posX = tempCurr.getXCoordinate();
    posY = tempCurr.getYCoordinate();

    //    int rows = model.getNumRows();
    //    int columns = model.getNumColumns();
    //    Room[][] rooms = model.getRooms();
    //
    //    panel1 = new JPanel(new GridLayout(rows, columns));
    //    panel1.setBackground(Color.BLACK);
    //
    //    JLabel labels[][] = new JLabel[rows][columns];
    //
    //    for (int i = 0; i < rows; i++) {
    //      for (int j = 0; j < columns; j++) {
    //        //        System.out.println(rooms[j][i].getLegalActions());
    //        String fileName =
    //            "hunt-the-wumpus-images/" + fileNameHelper(rooms[j][i].getLegalActions()) +
    // ".png";
    //        ImageIcon icon = new ImageIcon(fileName);
    //        Image scaleImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
    //        ImageIcon imageIcon = new ImageIcon(scaleImage);
    //        labels[i][j] = new JLabel(imageIcon);
    //        //        labels[i][j].setPreferredSize(new Dimension(100, 100));
    //        panel1.add(labels[i][j]);
    //        //        labels[i][j].setVisible(false);
    //      }
    //    }
    //
    //    ImageIcon playerIcon = new ImageIcon("hunt-the-wumpus-images/player.png");
    //    BufferedImage stenchIcon = ImageIO.read(new File("hunt-the-wumpus-images/stench.png"));
    //    BufferedImage combined = overlay(stenchIcon, "hunt-the-wumpus-images/player.png", 0);
    //
    //    JLabel temp =
    //
    // labels[model.getCurrentRoom().getXCoordinate()][model.getCurrentRoom().getYCoordinate()];
    //    temp = new JLabel(new ImageIcon(combined));

    rebuild(model);

    //    int rows = model.getNumRows();
    //    int columns = model.getNumColumns();
    //    Room[][] rooms = model.getRooms();
    //
    //    panel1 = new JPanel(new GridLayout(rows, columns));
    //    panel1.setBackground(Color.BLACK);
    //
    //    JLabel labels[][] = new JLabel[rows][columns];
    //
    //    for (int i = 0; i < rows; i++) {
    //      for (int j = 0; j < columns; j++) {
    //        //        System.out.println(rooms[j][i].getLegalActions());
    //        //        String fileName =
    //        //            "hunt-the-wumpus-images/" +
    // fileNameHelper(rooms[j][i].getLegalActions()) +
    //        // ".png";
    //
    //        Room tempRoom = rooms[j][i];
    //
    //        String element = fileNameHelper(tempRoom.getLegalActions());
    //        String fileName = "hunt-the-wumpus-images/" + element + ".png";
    //        //        System.out.println(fileName);
    //        String fileName1;
    //        BufferedImage combined = ImageIO.read(new File(fileName));
    //
    //        if (tempRoom.getStench()) {
    //          fileName1 = "hunt-the-wumpus-images/" + "stench" + ".png";
    //          //          System.out.println(fileName1);
    //          combined = overlay(combined, fileName1, 0);
    //        }
    //
    //        if (tempRoom.getDraft()) {
    //          fileName1 = "hunt-the-wumpus-images/" + "breeze" + ".png";
    //          //          System.out.println(fileName1);
    //          combined = overlay(combined, fileName1, 0);
    //        }
    //
    //        for (Character ch : tempRoom.getCharacters()) {
    //          if (ch.getName() == CharacterType.PLAYER) {
    //            fileName1 = "hunt-the-wumpus-images/" + "player" + ".png";
    //            //            System.out.println(fileName1);
    //            combined = overlay(combined, fileName1, 0);
    //          } else if (ch.getName() == CharacterType.WUMPUS) {
    //            fileName1 = "hunt-the-wumpus-images/" + "wumpus" + ".png";
    //            //            System.out.println(fileName1);
    //            combined = overlay(combined, fileName1, 0);
    //          } else if (ch.getName() == CharacterType.SUPER_BAT) {
    //            fileName1 = "hunt-the-wumpus-images/" + "bats" + ".png";
    //            //            System.out.println(fileName1);
    //            combined = overlay(combined, fileName1, 0);
    //          } else if (ch.getName() == CharacterType.PIT) {
    //            fileName1 = "hunt-the-wumpus-images/" + "pit" + ".png";
    //            //            System.out.println(fileName1);
    //            combined = overlay(combined, fileName1, 0);
    //          }
    //        }
    //
    //        //        combined = overlay(combined, "hunt-the-wumpus-images/player.png", 0);
    //
    //        ImageIcon icon = new ImageIcon(combined);
    //        Image scaleImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
    //        ImageIcon imageIcon = new ImageIcon(scaleImage);
    //        labels[i][j] = new JLabel(imageIcon);
    //
    //        panel1.add(labels[i][j]);
    //      }
    //    }

    //
    // labels[model.getCurrentRoom().getXCoordinate()][model.getCurrentRoom().getYCoordinate()].add(new JLabel(playerIcon), 0);
    //
    // labels[model.getCurrentRoom().getXCoordinate()][model.getCurrentRoom().getYCoordinate()].add(new JLabel(new ImageIcon(combined)));
    //    labels[model.getCurrentRoom().getXCoordinate()][model.getCurrentRoom().getYCoordinate()] =
    //        new JLabel(new ImageIcon(combined));
    //
    //    panel.add(
    //
    // labels[model.getCurrentRoom().getXCoordinate()][model.getCurrentRoom().getYCoordinate()]);
    //    panel.remove(0);
    //    labels[0][0] = temp;
    //    panel.removeAll();
    //    panel.add(labels[0][0]);
    //    this.setVisible(false);

    this.add(panel1, BorderLayout.NORTH);

    panel2 = new JPanel(new BorderLayout());
    this.add(panel2, BorderLayout.SOUTH);

    panel3 = new JPanel(new FlowLayout());
    panel2.add(panel3, BorderLayout.CENTER);

    display3 = new JLabel("Move");
    panel3.add(display3);
    input2 = new JTextField(10);
    panel3.add(input2);

    moveButton = new JButton("Move!");
    panel3.add(moveButton);

    moveButton.addActionListener(
        (ActionEvent e) -> {
          if (commandCallback != null) { // if there is a command callback
            commandCallback.accept("MOVE " + input2.getText()); // send command to be processed
            input2.setText(""); // clear the input text field
          }
        });

    display3 = new JLabel("Shoot Direction");
    panel3.add(display3);
    input3 = new JTextField(10);
    panel3.add(input3);

    display4 = new JLabel("Shoot Distance");
    panel3.add(display4, BorderLayout.CENTER);
    input4 = new JTextField(2);
    panel3.add(input4, BorderLayout.CENTER);

    shootButton = new JButton("Shoot!");
    panel3.add(shootButton, BorderLayout.CENTER);

    shootButton.addActionListener(
        (ActionEvent e) -> {
          if (commandCallback != null) { // if there is a command callback
            commandCallback.accept(
                "SHOOT "
                    + input3.getText()
                    + " "
                    + input4.getText()); // send command to be processed
            input2.setText(""); // clear the input text field
          }
        });

    exitButton = new JButton("Exit");
    //    GridBagConstraints gbc = new GridBagConstraints();
    //    gbc.anchor = GridBagConstraints.CENTER;
    panel2.add(exitButton, BorderLayout.WEST);
    exitButton.addActionListener(
        new ActionListener() {
          @Override
          public void actionPerformed(ActionEvent e) {
            System.exit(0);
          }
        });

    BufferedImage compass = ImageIO.read(new File("hunt-the-wumpus-images/" + "compass" + ".png"));
    ImageIcon compassIcon = new ImageIcon(compass);
    Image scaleCompass = compassIcon.getImage().getScaledInstance(100, 100, Image.SCALE_DEFAULT);
    compassIcon = new ImageIcon(scaleCompass);
    panel2.add(new JLabel(compassIcon, 10), BorderLayout.EAST);

    this.addKeyListener(
        new KeyListener() {

          @Override
          public void keyTyped(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
              //              System.out.println("UP");
              if (commandCallback != null) { // if there is a command callback
                commandCallback.accept("MOVE North"); // send command to be processed
              }
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
              if (commandCallback != null) { // if there is a command callback
                commandCallback.accept("MOVE South"); // send command to be processed
              }
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
              if (commandCallback != null) { // if there is a command callback
                commandCallback.accept("MOVE West"); // send command to be processed
              }
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
              if (commandCallback != null) { // if there is a command callback
                commandCallback.accept("MOVE East"); // send command to be processed
              }
            }
          }

          @Override
          public void keyPressed(KeyEvent e) {
            if (e.getKeyCode() == KeyEvent.VK_UP) {
              //              System.out.println("UP");
              if (commandCallback != null) { // if there is a command callback
                commandCallback.accept("MOVE North"); // send command to be processed
              }
            } else if (e.getKeyCode() == KeyEvent.VK_DOWN) {
              if (commandCallback != null) { // if there is a command callback
                commandCallback.accept("MOVE South"); // send command to be processed
              }
            } else if (e.getKeyCode() == KeyEvent.VK_LEFT) {
              if (commandCallback != null) { // if there is a command callback
                commandCallback.accept("MOVE West"); // send command to be processed
              }
            } else if (e.getKeyCode() == KeyEvent.VK_RIGHT) {
              if (commandCallback != null) { // if there is a command callback
                commandCallback.accept("MOVE East"); // send command to be processed
              }
            }
          }

          @Override
          public void keyReleased(KeyEvent e) {}
        });

    this.addMouseListener(
        new MouseListener() {
          @Override
          public void mouseClicked(MouseEvent e) {
            //            System.out.println(panel1.getHeight());
            //            System.out.println(panel1.getWidth());

            int pX = posX * 150 + 75;
            int pY = posY * 150 + 75;

            int dX = pX - e.getX();
            int dY = pY - e.getY();

            System.out.println(pX);
            System.out.println(pY);

            System.out.println(e.getX());
            System.out.println(e.getY());

            if (Math.abs(dX) >= Math.abs(dY)) {
              if (dX >= 0) {
                if (commandCallback != null) { // if there is a command callback
                  commandCallback.accept("MOVE West"); // send command to be processed
                }
              } else {
                if (commandCallback != null) { // if there is a command callback
                  commandCallback.accept("MOVE East"); // send command to be processed
                }
              }
            } else {
              if (dY >= 0) {
                if (commandCallback != null) { // if there is a command callback
                  commandCallback.accept("MOVE North"); // send command to be processed
                }
              } else {
                if (commandCallback != null) { // if there is a command callback
                  commandCallback.accept("MOVE South"); // send command to be processed
                }
              }
            }

            //            if (e.getX() > pX && e.getY() > pY) {
            //              if (e.getX() - pX > e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() < e.getY() - pY) {
            //                //                East
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE East"); // send command to be processed
            //                }
            //
            //              } else if (e.getX() - pX < e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() < e.getY() - pY) {
            //                //                  South
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE South"); // send command to be
            // processed
            //                }
            //              }
            //
            //            } else if (e.getX() > pX && e.getY() < pY) {
            //              if (e.getX() - pX > e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() < e.getY() - pY) {
            //                //                  East
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE East"); // send command to be processed
            //                }
            //              } else if (e.getX() - pX > e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() > e.getY() - pY) {
            //                //                    North
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE North"); // send command to be
            // processed
            //                }
            //              }
            //
            //            } else if (e.getX() < pX && e.getY() > pY) {
            //              if (e.getX() - pX < e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() > e.getY() - pY) {
            //                //                  West
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE West"); // send command to be processed
            //                }
            //              } else if (e.getX() - pX < e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() < e.getY() - pY) {
            //                //                South
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE South"); // send command to be
            // processed
            //                }
            //              }
            //
            //            } else if (e.getX() < pX && e.getY() < pY) {
            //              if (e.getX() - pX > e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() > e.getY() - pY) {
            //                // North
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE North"); // send command to be
            // processed
            //                }
            //              } else if (e.getX() - pX < e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() > e.getY() - pY) {
            //                // West
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE West"); // send command to be processed
            //                }
            //              }
            //            }

            //            if (e.getX() > pX && e.getY() > pY) {
            //              if (e.getX() - pX > e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() < e.getY() - pY) {
            //                //                East
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE East"); // send command to be processed
            //                }
            //
            //              } else if (e.getX() - pX < e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() < e.getY() - pY) {
            //                //                  South
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE South"); // send command to be
            // processed
            //                }
            //              }
            //
            //            } else if (e.getX() > pX && e.getY() < pY) {
            //              if (e.getX() - pX > e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() < e.getY() - pY) {
            //                //                  East
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE East"); // send command to be processed
            //                }
            //              } else if (e.getX() - pX > e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() > e.getY() - pY) {
            //                //                    North
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE North"); // send command to be
            // processed
            //                }
            //              }
            //
            //            } else if (e.getX() < pX && e.getY() > pY) {
            //              if (e.getX() - pX < e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() > e.getY() - pY) {
            //                //                  West
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE West"); // send command to be processed
            //                }
            //              } else if (e.getX() - pX < e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() < e.getY() - pY) {
            //                //                South
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE South"); // send command to be
            // processed
            //                }
            //              }
            //
            //            } else if (e.getX() < pX && e.getY() < pY) {
            //              if (e.getX() - pX > e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() > e.getY() - pY) {
            //                // North
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE North"); // send command to be
            // processed
            //                }
            //              } else if (e.getX() - pX < e.getY() - pY
            //                  && panel1.getWidth() - pX - e.getX() > e.getY() - pY) {
            //                // West
            //                if (commandCallback != null) { // if there is a command callback
            //                  commandCallback.accept("MOVE West"); // send command to be processed
            //                }
            //              }
            //            }

            //            System.out.println(e.getX() + "," + e.getY());
          }

          @Override
          public void mousePressed(MouseEvent e) {}

          @Override
          public void mouseReleased(MouseEvent e) {}

          @Override
          public void mouseEntered(MouseEvent e) {}

          @Override
          public void mouseExited(MouseEvent e) {}
        });

    this.setFocusable(true);

    this.pack();
    this.setVisible(true);
  }

  private String fileNameHelper(Set<String> directions) {
    Set<String> fileNames = new HashSet<>();
    fileNames.add("N");
    fileNames.add("E");
    fileNames.add("W");
    fileNames.add("S");
    fileNames.add("EW");
    fileNames.add("SE");
    fileNames.add("NE");
    fileNames.add("SW");
    fileNames.add("NS");
    fileNames.add("NW");
    fileNames.add("NSE");
    fileNames.add("NEW");
    fileNames.add("NSW");
    fileNames.add("SEW");
    fileNames.add("NSEW");

    String file = new String();
    for (String str : directions) {
      file += str.substring(0, 1);
    }
    //    System.out.println(file);

    char[] chars2 = file.toCharArray();
    Arrays.sort(chars2);
    String actString = new String(chars2);

    String dirString;

    for (String dir : fileNames) {
      char[] chars1 = dir.toCharArray();
      Arrays.sort(chars1);
      dirString = new String(chars1);

      //      System.out.println(actString);
      //      System.out.println(dirString);

      if (dirString.trim().equalsIgnoreCase(actString.trim())) {
        //        System.out.println("Dir: " + dir);
        return dir;
      }
    }

    return "NSEW";
  }

  private BufferedImage overlay(BufferedImage starting, String fpath, int offset)
      throws IOException {
    BufferedImage overlay = ImageIO.read(new File(fpath));
    int w = Math.max(starting.getWidth(), overlay.getWidth());
    int h = Math.max(starting.getHeight(), overlay.getHeight());
    BufferedImage combined = new BufferedImage(w, h, BufferedImage.TYPE_INT_ARGB);
    Graphics g = combined.getGraphics();
    g.drawImage(starting, 0, 0, null);
    g.drawImage(overlay, offset, offset, null);
    return combined;
  }

  //  public void setFeatures(MVCController mvcController) {
  //    moveButton.addActionListener(
  //        new ActionListener() {
  //          @Override
  //          public void actionPerformed(ActionEvent e) {
  //            String move = input2.getText();
  //            if (move != null) {
  //              System.out.println("Action Listener: " + move);
  //              //              mvcController.playGame(move);
  //            } else {
  //              throw new IllegalArgumentException();
  //            }
  //          }
  //        });
  //    shootButton.addActionListener(
  //        new ActionListener() {
  //          @Override
  //          public void actionPerformed(ActionEvent e) {}
  //        });
  //    exitButton.addActionListener(
  //        new ActionListener() {
  //          @Override
  //          public void actionPerformed(ActionEvent e) {
  //            mvcController.exitProgram();
  //          }
  //        });
  //  }

  public void refresh(Maze model, boolean stopCondition, String moveShoot, String command)
      throws IOException {
    //    this.panel2.requestFocus();
    //    this.setFocusable(true);
    //    this.input2.requestFocus();
    //    this.input3.requestFocus();
    //    this.input4.requestFocus();
    this.panel1.removeAll();

    this.rebuild(model);
    this.add(panel1, BorderLayout.NORTH);
    this.pack();
    this.setVisible(true);

    this.panel1.revalidate();
    this.panel1.repaint();
    if (stopCondition && moveShoot.equals("MOVE")) {
      JOptionPane.showMessageDialog(null, "You Lost!\nGame Over!");
    } else if (stopCondition && moveShoot.equals("SHOOT")) {
      JOptionPane.showMessageDialog(null, "You Won!\nGame Over!");
    }
    if (command.equals("")) {
      JOptionPane.showMessageDialog(null, "Incorrect Input!");
    }
    this.setFocusable(true);
  }

  private void rebuild(Maze model) throws IOException {

    int rows = model.getNumRows();
    int columns = model.getNumColumns();
    Room[][] rooms = model.getRooms();

    Room tempCurr = model.getCurrentRoom();
    posX = tempCurr.getXCoordinate();
    posY = tempCurr.getYCoordinate();

    panel1 = new JPanel(new GridLayout(rows, columns));
    panel1.setBackground(Color.BLACK);

    JLabel labels[][] = new JLabel[rows][columns];

    for (int i = 0; i < rows; i++) {
      for (int j = 0; j < columns; j++) {

        Room tempRoom = rooms[j][i];

        String element = fileNameHelper(tempRoom.getLegalActions());
        String fileName = "hunt-the-wumpus-images/" + element + ".png";

        String fileName1;

        BufferedImage combined = ImageIO.read(new File(fileName));

        if (tempRoom.getStench()) {
          fileName1 = "hunt-the-wumpus-images/" + "stench" + ".png";
          combined = overlay(combined, fileName1, 0);
        }

        if (tempRoom.getDraft()) {
          fileName1 = "hunt-the-wumpus-images/" + "breeze" + ".png";
          combined = overlay(combined, fileName1, 0);
        }

        for (Character ch : tempRoom.getCharacters()) {
          if (ch.getName() == CharacterType.PLAYER) {
            visitedRooms.add(tempRoom);
            visitedRooms.addAll(model.getCaveTunnels());
            fileName1 = "hunt-the-wumpus-images/" + "player" + ".png";
            combined = overlay(combined, fileName1, 0);
          } else if (ch.getName() == CharacterType.WUMPUS) {
            fileName1 = "hunt-the-wumpus-images/" + "wumpus" + ".png";
            combined = overlay(combined, fileName1, 0);
          } else if (ch.getName() == CharacterType.SUPER_BAT) {
            fileName1 = "hunt-the-wumpus-images/" + "bats" + ".png";
            combined = overlay(combined, fileName1, 0);
          } else if (ch.getName() == CharacterType.PIT) {
            fileName1 = "hunt-the-wumpus-images/" + "pit" + ".png";
            combined = overlay(combined, fileName1, 0);
          }
        }

        ImageIcon icon = new ImageIcon(combined);
        Image scaleImage = icon.getImage().getScaledInstance(150, 150, Image.SCALE_DEFAULT);
        ImageIcon imageIcon = new ImageIcon(scaleImage);
        labels[i][j] = new JLabel(imageIcon);

        panel1.add(labels[i][j]);
        labels[i][j].setVisible(false);

        //        if (visitedRooms.contains(tempRoom)) {
        //          labels[i][j].setVisible(true);
        //        }
      }
    }

    for (Room explored : visitedRooms) {
      int X = explored.getXCoordinate();
      int Y = explored.getYCoordinate();
      labels[Y][X].setVisible(true);
    }

    //    this.setFocusable(true);
  }

  //  @Override
  public void setCommandCallback(Consumer<String> callback) {
    commandCallback = callback;
  }

  //  public static void main(String[] args) throws IOException { // Creating object of demo class.
  //
  //    Maze model = new NonWrappingMaze(4, 5);
  //    model.generate();
  //    model.generateMazeObjects(0, 10, 10);
  //
  //    mazeView mv = new mazeView("HTW Radio", model);
  //    //    MVCController mvcController = new MVCController();
  //    //    mvcController.setView(v);
  //    //    v.setVisible(true);
  //  }
}

package ui;

import model.WorkRoom;
import model.Thingy;
import org.json.JSONObject;
import model.EventLog;
import model.Event;
import ui.ManageInformation;

import javax.imageio.ImageIO;
import javax.swing.*;
import javax.swing.border.Border;
import java.awt.*;
import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.awt.image.BufferedImage;
import java.io.FileNotFoundException;
import java.net.URL;
import java.util.ArrayList;
import java.util.List;


public class GatherInformation extends JFrame implements ActionListener {


    WorkRoom workRoom;
    Thingy player = null;
    Thingy newPlayer = null;
    JSONObject playerProfileNewPlayer = new JSONObject();
    String playerNameOfNewlyAddedPlayer;
    ManageInformation manager;





    JButton jb1 = new JButton("First time here");
    JButton jb2 = new JButton("Have been here");
    JTextField jtf = new JTextField();
    JTextField namefield = new JTextField();
    JTextField gameWonField = new JTextField();
    JTextField gameLostField = new JTextField();
    JTextField goalsField = new JTextField();
    JTextField concealsField = new JTextField();
    JTextField positionField = new JTextField();

    JTextField newPlayerName = new JTextField();
    JTextField newPlayerGoals = new JTextField();
    JTextField newPlayerConceals = new JTextField();
    JTextField newPlayerGameWon = new JTextField();
    JTextField newPlayerGameLost = new JTextField();
    JTextField newPlayerPosition = new JTextField();

    JTextField removalPlayerNameField = new JTextField("enter the name of player you want to remove");
    JTextArea informationMessageArea;
    JTextArea statusMessageArea;


    JButton displayCurrentTeamPlayerSummary = new JButton("To display current team");
    JButton modifying = new JButton("To update player's information");
    JButton saveHistory = new JButton("To save your update performed");
    JButton confirmingAddingNewPlayer = new JButton("<html> Press here for adding the "
            + "newly inputted Player when you are done inputting <br> all information"
            + " of the new player");
    JButton playerRemoval = new JButton("Click here to remove the player");




    JPanel jpanel = new JPanel();
    String customerName;
    JLabel jlabel2 = new JLabel();


    ImageIcon summer1998 = new ImageIcon("./data/japan1998.jpg");
    JLabel sum1998 = new JLabel(summer1998);

    ImageIcon running = new ImageIcon("./data/running.jpg");
    JLabel run = new JLabel(running);

    ImageIcon scoring = new ImageIcon("/data/scoring.jpg");
    JLabel score = new JLabel(scoring);



    // EFFECT: a constructor that also initiates a panel for displaying, manage informations from users
    public GatherInformation() throws FileNotFoundException {
        makePanel();


        this.setDefaultCloseOperation(JFrame.DO_NOTHING_ON_CLOSE);
        this.addWindowListener(new java.awt.event.WindowAdapter() {
            @Override
            public void windowClosing(java.awt.event.WindowEvent e) {
                System.out.println("Uncomment following to open another window!");
                //MainPage m = new MainPage();
                //m.setVisible(true);
                e.getWindow().dispose();
                System.out.println("JFrame Closed!");
                printLog(EventLog.getInstance());
                System.out.println();
            }
        });
    }

    // MODIFIES: this
    // EFFECT: set out the panel and add the components such as JButton, jtextarea, jtextfield inside it
    public void makePanel() {
        jpanel.setLayout(null);


        jbuttonJtextPutOnPanel();
        jtf.setToolTipText("Enter without spaces");
        jtf.setBackground(Color.yellow);
        JLabel jlab = new JLabel("Enter Your Name here: ");
        setBoundAndAddtoPanel(jpanel, jlab,70, 200, 200, 70);

        // set Panel size and location
        jpanel.setBackground(Color.lightGray);

        Border blackline = BorderFactory.createLineBorder(Color.black);
        jpanel.setBorder(blackline);

        add(jpanel);
        setVisible(true);

        setTitle("Team Formation");
        setSize(1800, 2400);

        setBoundAndAddtoPanel(jpanel, jlabel2, 70, 350, 400, 100);

        addingListener();
        messageArea();

        jb1.addActionListener(this);
        jb2.addActionListener(this);
        displayCurrentTeamPlayerSummary.addActionListener(this);
        confirmingAddingNewPlayer.addActionListener(this);



    }


    // EFFECT: print all the log messages
    public void printLog(EventLog el) {
        for (Event next : el) {
            System.out.println(next);
        }
    }


    // MODIFIES: this
    // EFFECT: adding some actionlisteners to the fields of the class
    public void addingListener() {
        modifying.addActionListener(this);
        namefield.addActionListener(this);
        saveHistory.addActionListener(this);
        namefield.setToolTipText("Enter without spaces and small letter only");
        goalsField.setToolTipText("Enter goals here");


        nameListen(jtf);
        goalsFieldListen(goalsField);
        concealsFieldListen(concealsField);
        gameLostFieldListen(gameLostField);
        gameWonFieldListen(gameWonField);
        positionFieldListen(positionField);

        nameListen(newPlayerName);
        goalsFieldListen(newPlayerGoals);
        concealsFieldListen(newPlayerConceals);
        gameLostFieldListen(newPlayerGameLost);
        gameWonFieldListen(newPlayerGameWon);
        positionFieldListen(newPlayerPosition);

        removalPlayerListener(removalPlayerNameField);
    }

    // MODIFIES: this
    // EFFECT: adding various labels of the fields to the panel, and also
    //         call the panelforAddingNewPlayer() function
    public void jbuttonJtextPutOnPanel() {
        panelforAddingNewPlayer();
        helperForSetBoundAndAddtoPanel();
        JLabel goalFieldLabel = new JLabel("Enter number of goals here: ");
        JLabel concealFieldLabel = new JLabel("Enter number of conceals here: ");
        JLabel gameWonFieldLabel = new JLabel("Enter number of game won here: ");
        JLabel gameLostFieldLabel = new JLabel("Enter number of game lost here: ");
        JLabel positionFieldLabel = new JLabel("Enter position here: ");
        JLabel playerNameFieldLabel = new JLabel("Enter the player's Name here: ");
        setBoundAndAddtoPanel(jpanel, playerNameFieldLabel, 280, 200, 250, 30);
        setBoundAndAddtoPanel(jpanel, concealFieldLabel, 280, 250, 250, 30);
        setBoundAndAddtoPanel(jpanel, gameWonFieldLabel, 280, 300, 250, 30);
        setBoundAndAddtoPanel(jpanel, gameLostFieldLabel, 280, 350, 250, 30);
        setBoundAndAddtoPanel(jpanel, positionFieldLabel, 280, 400, 250, 30);
        setBoundAndAddtoPanel(jpanel, goalFieldLabel, 280, 450, 250, 30);

    }

    // MODIFIES: this
    // EFFECT: a helper that set the position of the various JButton, JTextfield to the panel
    //       since if these are called inside another function, it exceeds the 25 lines limits
    public void helperForSetBoundAndAddtoPanel() {

        setBoundAndAddtoPanel(jpanel, jb1, 70, 50, 150, 30);
        setBoundAndAddtoPanel(jpanel, jb2, 280, 50, 200, 30);
        setBoundAndAddtoPanel(jpanel, jtf, 70, 250, 150, 30);
        setBoundAndAddtoPanel(jpanel, displayCurrentTeamPlayerSummary, 70, 100, 250, 30);
        setBoundAndAddtoPanel(jpanel,modifying, 70, 150, 450, 30);
        setBoundAndAddtoPanel(jpanel, saveHistory, 70, 550, 250, 30);

        setBoundAndAddtoPanel(jpanel, namefield, 580, 200, 250, 30);
        setBoundAndAddtoPanel(jpanel, concealsField, 580, 250, 250, 30);
        setBoundAndAddtoPanel(jpanel, gameWonField, 580, 300, 250, 30);
        setBoundAndAddtoPanel(jpanel, gameLostField, 580, 350, 250, 30);
        setBoundAndAddtoPanel(jpanel, positionField, 580, 400, 250, 30);
        setBoundAndAddtoPanel(jpanel, goalsField, 580, 450, 250, 30);

        setBoundAndAddtoPanel(jpanel, playerRemoval, 70, 850, 250, 30);
        setBoundAndAddtoPanel(jpanel, removalPlayerNameField, 70, 900, 250, 30);
        setBoundAndAddtoPanel(jpanel, sum1998, 1280,550, 490, 350);

    }

    // EFFECT: a helper for adding new player. It set the various JLabel, and also JTextfield
    //       required to store the user input for the specific field of the new player. It also adds
    //       actionlistener to those fields. So that gatherinformation class
    //       will store information from those fields and respond input from those fields
    public void panelforAddingNewPlayer() {
        JLabel newPlayerPanelLabel = new JLabel("Panel below is for filling out information for "
                + "adding New Player only");
        JLabel instructionsLabel = new JLabel();
        instructionsLabel.setText("<html> Start by entering your name in the yellow box: "
                + "<br> then follow the instructions in the message panel <br> on the right");
        instructionsLabel.setForeground(Color.red);
        instructionsLabel.setFont(new Font("Serif", Font.PLAIN, 20));
        setBoundAndAddtoPanel(jpanel, newPlayerPanelLabel, 580, 555, 500, 50);
        setBoundAndAddtoPanel(jpanel, confirmingAddingNewPlayer, 70, 600, 300, 50);
        setBoundAndAddtoPanel(jpanel, instructionsLabel, 500, 0, 470, 140);
        newPlayerName = new JTextField("New Player's name to be added here");
        newPlayerGoals = new JTextField("New Player's Goals Scored to be added here");
        newPlayerConceals = new JTextField("New Player's number of Conceals to be added here");
        newPlayerGameWon = new JTextField("New Player's Game Won to be added here");
        newPlayerGameLost = new JTextField("New Player's Game Lost to be added here");
        newPlayerPosition = new JTextField("New Player's Position to be added here");
        setBoundAndAddtoPanel(jpanel, newPlayerName, 580, 600, 250, 30);
        setBoundAndAddtoPanel(jpanel, newPlayerGoals, 580, 650, 250, 30);
        setBoundAndAddtoPanel(jpanel, newPlayerConceals, 580, 700, 250, 30);
        setBoundAndAddtoPanel(jpanel, newPlayerGameWon, 580, 750, 250, 30);
        setBoundAndAddtoPanel(jpanel, newPlayerGameLost, 580, 800, 250, 30);
        setBoundAndAddtoPanel(jpanel, newPlayerPosition, 580, 850, 250, 30);
    }

    // Something called an anonymous action listener
    // add an actionlistener to the "Enter user name" field
    public void nameListen(JTextField textField) {
        textField.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent event) {
                JTextField jtextField = (JTextField) event.getSource();
                String textToDisplay = jtextField.getText();
                customerName = textToDisplay;
                if (event.getSource().equals(newPlayerName)) {
                    JTextField newPlayerNameField = (JTextField) event.getSource();
                    playerNameOfNewlyAddedPlayer = newPlayerNameField.getText();
                    statusMessageArea.setText("New Player's name is being added");
                } else if (event.getSource().equals(jtf)) {
                    informationMessageArea.setText("Hello " + textToDisplay + "\n");
                    informationMessageArea.append("If you have been here: please click "
                            +
                            "\" Have been here\" on the left \n ");
                    informationMessageArea.append("Or click \" First time here \" on the left");
                    setBoundAndAddtoPanel(jpanel, run, 900, 570, 350,250);
                }

            }
        });
    }


    // EFFECT: add an actionlistener to the goalsField
    public void goalsFieldListen(JTextField textField) {
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Integer goals;
                JTextField jtextGoals = (JTextField) event.getSource();
                String goalsString = jtextGoals.getText();
                goals = Integer.parseInt(goalsString);

                try {
                    if (event.getSource().equals(goalsField)) {
                        statusMessageArea.append("\n Goals field is being EDITED now");
                        player.setGoals(goals);

                    } else if (event.getSource().equals(newPlayerGoals)) {
                        statusMessageArea.append("\n New Player Goals field is ADDED now");
                        playerProfileNewPlayer.put("goals", goals);
                    }
                } catch (Exception exp) {
                    // catch it
                }
            }
        });
    }

    // EFFECT: add an actionlistener to the conceals Field
    public void concealsFieldListen(JTextField textField) {
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Integer conceals;
                JTextField jtextConceal = (JTextField) event.getSource();
                String concealString = jtextConceal.getText();
                conceals = Integer.parseInt(concealString);

                try {
                    if (event.getSource().equals(concealsField)) {
                        statusMessageArea.append("\n Conceal field is being EDITED now");
                        player.setConceal(conceals);

                    } else if (event.getSource().equals(newPlayerConceals)) {
                        statusMessageArea.append("\n New Player's Conceal field is being ADDED now");
                        playerProfileNewPlayer.put("conceals", conceals);
                    }
                } catch (Exception exp) {
                    // catch it
                }
            }
        });
    }

    // EFFECT: add an actionlistener to the gameWon field
    public void gameWonFieldListen(JTextField textField) {
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Integer gameWon;
                JTextField jtextgameWon = (JTextField) event.getSource();
                String gameWonString = jtextgameWon.getText();
                gameWon = Integer.parseInt(gameWonString);
                try {
                    if (event.getSource().equals(gameWonField)) {
                        statusMessageArea.append("\n gameWon field is being EDITED now");
                        player.setGameWon(gameWon);
                    } else if (event.getSource().equals(newPlayerGameWon)) {
                        statusMessageArea.append("\n New Player's gameWon field is being ADDED now");
                        playerProfileNewPlayer.put("gameWon", gameWon);
                    }
                } catch (Exception exp) {
                    // catch it
                }
            }
        });
    }

    // EFFECT: add an actionlistener to the gameLost
    public void gameLostFieldListen(JTextField textField) {
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                Integer gameLost;
                JTextField jtextgamelost = (JTextField) event.getSource();
                String gameloststring = jtextgamelost.getText();
                gameLost = Integer.parseInt(gameloststring);
                try {
                    if (event.getSource().equals(gameLostField)) {
                        statusMessageArea.append("\n gameLost field is being EDITED now");
                        player.setGameLost(gameLost);
                        statusMessageArea.append("\n " + player.getGameLost().toString());
                    } else if (event.getSource().equals(newPlayerGameLost)) {
                        statusMessageArea.append("\n New Player's gameLost field is being ADDED now");
                        playerProfileNewPlayer.put("gameLost", gameLost);
                    }
                } catch (Exception exp) {
                    // catch it
                }
            }
        });
    }

    // EFFECT: add an actionlistener to the position field
    public void positionFieldListen(JTextField textField) {
        textField.addActionListener(new ActionListener() {
            @Override
            public void actionPerformed(ActionEvent event) {
                JTextField jtextField = (JTextField) event.getSource();
                String position = jtextField.getText();
                try {
                    if (event.getSource().equals(positionField)) {
                        statusMessageArea.append("\n Position field is being EDITED now");
                        player.setPosition(position);
                    } else if (event.getSource().equals(newPlayerPosition)) {
                        statusMessageArea.append("\n New Player's Position field is being ADDED now");
                        playerProfileNewPlayer.put("position", position);
                    }
                } catch (Exception exp) {
                    // catch it
                }
            }
        });
    }

    // EFFECT: listens to the player removal text field
    public void removalPlayerListener(JTextField jtextfield) {

        jtextfield.addActionListener(new ActionListener() {

            @Override
            public void actionPerformed(ActionEvent e) {
                JTextField jtf = (JTextField) e.getSource();
                String nameOfPlayerToRemove = jtf.getText();
                List<Thingy> thingylist;

                thingylist = workRoom.getThingies();
                workRoom.playerRemoval(thingylist, nameOfPlayerToRemove);


                statusMessageArea.append("\n Player : " + nameOfPlayerToRemove
                        + " has been removed");
                setBoundAndAddtoPanel(jpanel, score, 900,670, 350, 300);
            }
        });
    }





    //EFFECTS: Display an area to display message
    public void messageArea() {
        informationMessageArea = new JTextArea();
        informationMessageArea.setBounds(900, 10, 450, 500);
        jpanel.add(informationMessageArea);
        jpanel.setSize(1200, 1300);
        informationMessageArea.setEditable(false);

        statusMessageArea = new JTextArea();
        statusMessageArea.setBounds(1400, 10, 350,500);
        jpanel.add(statusMessageArea);
    }

    //EFFECT: Add component to panel and set sizes and coordinates on the panel
    public void setBoundAndAddtoPanel(JPanel jp, Component cp, int x, int y, int width, int height) {
        cp.setBounds(x,y, width, height);
        jp.add(cp);
    }



    // EFFECT: This is a function to displace some current players' status within the team
    public void displayCurrentTeam() {
        workRoom = manager.getWorkRoom();
        informationMessageArea.setText("Here are list of your current players and their status: \n ");
        for (Thingy thingy : workRoom.getThingies()) {
            informationMessageArea.append("Player's  Name: " + thingy.getThingName() + "\n");
            informationMessageArea.append("         Goals: " + thingy.getGoals() + "\n");
            informationMessageArea.append("      Conceals: " + thingy.getConceal() + "\n");
            informationMessageArea.append("       Gamewon: " + thingy.getGameWon() + "\n");
            informationMessageArea.append("      GameLost: " + thingy.getGameLost() + "\n");
            informationMessageArea.append("      Position: " + thingy.getPosition() + "\n");
        }
    }



    // EFFECT: this is a helper that is used during updating existing player's status when editing
    public void playerStatusUpdater(String playerName) {
        statusMessageArea.append("The player you will be editing is :  \n " + playerName);
        for (Thingy thing : workRoom.getThingies()) {
            if (thing.getThingName().equals(playerName)) {
                player =  thing;
                statusMessageArea.append("\n Currently being edit player IS THE FOLLOWING: "
                        + player.getThingName());
            }
        }
        // EventLog: this is to capture the event when a Player is being editted in the list
//        EventLog.getInstance().logEvent(new Event("A player " + player.getThingName()
//                + " has just been editted"));
    }




    @SuppressWarnings({"checkstyle:MethodLength", "checkstyle:SuppressWarnings"})
    @Override
    public void actionPerformed(ActionEvent e) {
        if (e.getSource().equals(jb2)) {
            manager = new ManageInformation(customerName, true, this);
        } else if (e.getSource().equals(jb1)) {
            manager = new ManageInformation(customerName, false, this);
            workRoom = manager.getWorkRoom();
        } else if (e.getSource().equals(saveHistory)) {
            statusMessageArea.append("\n Data being saved");
            manager.setWorkRoom(workRoom);
            manager.saveWorkRoom();
        } else if (e.getSource().equals(displayCurrentTeamPlayerSummary)) {
            displayCurrentTeam();
        } else if (e.getSource().equals(modifying)) {
            statusMessageArea.setText("Please update player's status by entering the changes"
                    + " into the \n box on the left. \n Please first enter the name of the "
                    + "player that you want to edit.\n  ");
        } else if (e.getSource().equals(namefield)) {
            String playerToBeEdited = ((JTextField) namefield).getText();
            playerStatusUpdater(playerToBeEdited);
        } else if (e.getSource().equals(confirmingAddingNewPlayer)) {
            statusMessageArea.append("\n New Player has been added!!");
            // EventLog: capture the instance when a new Player is added to the current list of players
//            EventLog.getInstance().logEvent(new Event("A new player " + playerNameOfNewlyAddedPlayer
//                    + " has been added to the current list of players!"));

            newPlayer = new Thingy(playerNameOfNewlyAddedPlayer, playerProfileNewPlayer);

            workRoom.playerAdded(newPlayer, playerNameOfNewlyAddedPlayer);
        }
    }

    public JTextArea returnJTextArea() {
        return this.statusMessageArea;
    }
}

package ui;

import model.*;
import org.json.JSONObject;
import persistence.JsonReader;
import persistence.JsonWriter;

import java.awt.event.ActionEvent;
import java.awt.event.ActionListener;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.util.ArrayList;
import java.util.List;
import java.util.Scanner;

// this class is not used in Phase 3 when I implemented the GUI. It is only used
//  during Phase 2 when we only have User Interface available. So this does not appear
//  in the UI (user interface) diagram in Phase 4.

// citation: codes here are borrowed or modified based on the demo application
//          from CPSC 210
//   https://edge.edx.org/courses/course-v1:UBC+CPSC210+all/courseware

// this class starts the application and will also load, and save workRoom to .json file
public class PlayerApp implements ActionListener {

    private static final String JSON_STORE = "./data/jamesworkroom.json";

    private WorkRoom workRoom;
    private JsonWriter jsonWriter;
    private JsonReader jsonReader;
    private List<Thingy> thingyList;

    private Scanner input;

    private String name;
    private Integer goals;
    private Integer conceal;
    private Integer gameWon;
    private Integer gameLost;
    private String position;

    // EFFECT: run the player app
    public PlayerApp() throws FileNotFoundException {
        input = new Scanner(System.in);
        workRoom = new WorkRoom("Alex's workroom");
        jsonWriter = new JsonWriter(JSON_STORE);
        jsonReader = new JsonReader(JSON_STORE);


        try {
            runPlayer();
        } catch (Exception exc) {
            System.out.println("Illegal value exception entered");
        }
    }

    // MODIFIES: this
    // EFFECT: process user input
    public void runPlayer() throws Exception {
        boolean keepGoing = true;
        String command = null;


        input = new Scanner(System.in);

        while (keepGoing) {
            //displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("See you next time!");
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
        System.out.println("finish building line-up");
    }



    // EFFECT: display a list of options to choose from by the user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\tm -> Modify current team");
        System.out.println("\td -> display some current team's statistics");
        System.out.println("\tl -> load data stored previously");
        System.out.println("\ts -> save current modifications to the data");
        System.out.println("\tq -> quit");
    }



    // MODIFIES: this
    private void processCommand(String command) throws Exception {
        if (command.equals("m")) {
            modifyCurrentTeam();
        } else if (command.equals("d")) {
            displayStatistics();
        } else if (command.equals("l")) {
            loadWorkRoom();
        } else if (command.equals("s")) {
            saveWorkRoom();
        }
    }


    // EFFECT: add a player to the currentTeam
    public void addingAnPlayerToTeam() throws Exception {
        System.out.println("Please enter Name, goals, conceal, game won, game lost, position"
                + "of the player");
        Scanner scan = new Scanner(System.in);

        System.out.println("Please enter name: \n");
        name = scan.next();
        System.out.println("Please enter goals: \n");
        goals = scan.nextInt();
        System.out.println("Please enter conceal: \n");
        conceal = scan.nextInt();
        System.out.println("Please enter gameWon: \n");
        gameWon = scan.nextInt();
        System.out.println("Please enter gameLost: \n");
        gameLost = scan.nextInt();
        System.out.println("Please enter position: Striker, defender, midfield, goalkeeper\n");
        position = scan.next();

        JSONObject profile = new JSONObject();
        Thingy newPlayer = new Thingy(name, profile);
        newPlayer.setAllFieldsOfPlayer(goals,conceal,gameWon,gameLost, position);
        thingyList.add(newPlayer);

    }

    // EFFECT: remove a player from the team
    public void removingAnPlayerFromTeam() {
        System.out.println("Enter the name of the player you want to remove from player deposits \n");
        Scanner scan = new Scanner(System.in);
        String name = scan.next();
        List<Thingy> listPlayers = new ArrayList<Thingy>();
        for (Thingy thingy : workRoom.getThingies()) {
            if (!thingy.getThingName().equals(name)) {
                listPlayers.add(thingy);
            }
        }
        workRoom.setThingies(listPlayers);

        System.out.println(workRoom.getThingies());

    }

    // EFFECT: add or remove players in the current team
    public void modifyCurrentTeam() throws Exception {
        System.out.println("Press a to add a player to the "
                + "team, m to modify a player, and r to remove a player:");
        Scanner scan = new Scanner(System.in);
        String command = scan.next();
        if (command.equals("a")) {
            System.out.println("player to add");
            addingAnPlayerToTeam();
        } else if (command.equals("m")) {
            System.out.println("modify a player");
            editPlayer();
        } else if (command.equals("r")) {
            removingAnPlayerFromTeam();
        }
    }

    // EFFECT: edit a player's field
    public void editPlayer() {
        System.out.println("Enter name of player you want to edit: \n");
        System.out.println("List of current players: \n");
        System.out.println("Players that could you edit are: ");

        for (Thingy t : workRoom.getThingies()) {
            System.out.println(t.getThingName() + "\n");
        }

        System.out.println("Enter name of player to be edit: ");
        Scanner scan = new Scanner(System.in);
        String playerName = scan.next();

        playerEditing(playerName);

    }

    // EFFECT: A helper for the editPlayer function.
    public void playerEditing(String playerName) {
        Boolean keepGoing = true;
        String choices = null;
        Integer updateNumb = 0;
        JSONObject playerProfileObjectToEditted = new JSONObject();
        for (Thingy thing : workRoom.getThingies()) {
            if (thing.getThingName().equals(playerName)) {
                playerProfileObjectToEditted =  thing.returnPlayerProfile();
            }
        }

        helperPlayerEditting(true, choices, updateNumb, playerProfileObjectToEditted);
    }

    // EFFECT: A helper to shorten the playerEditting function (note: since originally function
    //        exceeds the limit set by checkstyle)
    public void helperPlayerEditting(boolean going, String choices, Integer updateNumb, JSONObject profile) {
        while (going) {
            System.out.println("Select player's aspect that you want to update: \n "
                    + "g goals, c conceal, w games won, l games lost, p pos, q quit");
            choices = input.next();
            if (choices.equals("g")) {
                updateNumb = input.nextInt();
                profile.put("goals", updateNumb);
            } else if (choices.equals("c")) {
                updateNumb = input.nextInt();
                profile.put("conceals", updateNumb);
            } else if (choices.equals("w")) {
                updateNumb = input.nextInt();
                profile.put("gameWon", updateNumb);
            } else if (choices.equals("l")) {
                updateNumb = input.nextInt();
                profile.put("gameLost", updateNumb);
            } else if (choices.equals("p")) {
                choices = input.next();
                profile.put("position", choices);
            } else if (choices.equals("q")) {
                going = false;
            }
        }
    }

    //EFFECT: show player's statistics
    public void displayStatistics() {
        System.out.println("Please enter the player's name to display his stats: \n");
        System.out.println("the list of players currently on the team are: \n");
        for (Thingy t: workRoom.getThingies()) {
            System.out.println(t.getThingName() + "\n");
        }
        String name = null;
        JSONObject playerToShow = null;
        Scanner scan = new Scanner(System.in);
        name = scan.next();

        for (Thingy thingy: workRoom.getThingies()) {
            if (thingy.getThingName().equals(name)) {
                playerToShow = thingy.returnPlayerProfile();
            }
        }
        showPlayer(playerToShow);
    }

    // EFFECT: A helper to show player's statistics
    public void showPlayer(JSONObject playerProfile) {

        System.out.println("Player's Position: " + playerProfile.getString("position") + "\n ");
        System.out.println("Player's Goals: " + playerProfile.getInt("goals") + "\n ");
        System.out.println("Player's Conceal: " + playerProfile.getInt("conceals") + "\n ");
        System.out.println("Player's Game won: " + playerProfile.getInt("gameWon") + "\n ");
        System.out.println("Player's Game lost: " + playerProfile.getInt("gameLost") + "\n ");
    }


    // EFFECTS: saves the workroom to file
    private void saveWorkRoom() {
        try {
            jsonWriter.open();
            jsonWriter.write(workRoom);
            jsonWriter.close();
            System.out.println("Saved " + workRoom.getName() + " to " + JSON_STORE);
        } catch (FileNotFoundException e) {
            System.out.println("Unable to write to file: " + JSON_STORE);
        }
    }


    // MODIFIES: this
    // EFFECTS: loads workroom from file
    private void loadWorkRoom() {
        try {
            workRoom = jsonReader.read();
            thingyList = workRoom.getThingies();
            System.out.println("Loaded " + workRoom.getName() + " from " + JSON_STORE);
        } catch (IOException e) {
            System.out.println("Unable to read from file: " + JSON_STORE);
        }
    }


    @Override
    public void actionPerformed(ActionEvent e) {

    }
}





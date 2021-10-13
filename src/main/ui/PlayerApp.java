package ui;

import model.Player;
import model.PlayersDeposits;
import model.Team;

import java.util.ArrayList;
import java.util.Scanner;

public class PlayerApp {

    private Team smallTeam;
    private PlayersDeposits playersDepos;
    private Scanner input;

    private String name;
    private Integer goals;
    private Integer conceal;
    private Integer gameWon;
    private Integer gameLost;
    private String position;

    // EFFECT: run the player app
    public PlayerApp() {
        runPlayer();
    }

    // MODIFIES: this
    // EFFECT: process user input
    public void runPlayer() {
        boolean keepGoing = true;
        String command = null;

        init();
        input = new Scanner(System.in);

        while (keepGoing) {
            displayMenu();
            command = input.next();
            command = command.toLowerCase();

            if (command.equals("q")) {
                System.out.println("See you next time!");
                keepGoing = false;
            } else {
                processCommand(command);
            }
        }
    }



    // EFFECT: display a list of options to choose from by the user
    private void displayMenu() {
        System.out.println("\nSelect from:");
        System.out.println("\ta -> Add/Remove Player to/from Players Deposits");
        System.out.println("\tm -> Modify current team");
        System.out.println("\td -> display some current team's statistics");
        System.out.println("\tq -> quit");
    }



    // MODIFIES: this
    private void processCommand(String command) {
        if (command.equals("a")) {
            addRemoveFromDeposit();
        } else if (command.equals("m")) {
            modifyCurrentTeam();
        } else if (command.equals("d")) {
            displayStatistics();
        }
    }

    // EFFECT: initialize an empty team and an empty player deposits
    public void init() {
        smallTeam = new Team();
        playersDepos = new PlayersDeposits();
    }


    // EFFECT: add or remove a player from Squad
    public void addRemoveFromDeposit() {
        System.out.println("Add or remove a player from Players deposits. ");
        System.out.println("a to add, r to remove \n");
        String command;
        Scanner scan = new Scanner(System.in);
        command = scan.next();
        command = command.toLowerCase();
        if (command.equals("a")) {
            adding("PlayerDeposits");
        } else if (command.equals("r")) {
            removing("PlayerDeposits");
        }

    }

    // EFFECT: add a player to the PlayersDeposits or currentTeam
    public void adding(String type) {
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
        Player namePlayer = new Player(name, goals, conceal, gameWon, gameLost, position);
        if (type.equals("PlayerDeposits")) {
            playersDepos.addPlayer(namePlayer);
            System.out.println("Player has been added to deposits");
        } else if (type.equals("currentTeam")) {
            smallTeam.addPlayerTeam(namePlayer);
            System.out.println("Player has been added to current team");
        }
    }

    // EFFECT: remove a player from the Player deposits
    public void removing(String type) {
        System.out.println("Enter the name of the player you want to remove from player deposits \n");
        Scanner scan = new Scanner(System.in);
        String name = scan.next();
        if (type.equals("PlayerDeposits")) {
            playersDepos.removePlayer(name);
        } else if (type.equals("currentTeam")) {
            smallTeam.removePlayerTeam(name);
        }
    }

    // EFFECT: add or remove players in the current team
    public void modifyCurrentTeam() {
        System.out.println("Press a to add a player to the "
                + "team, m to modify a player, and r to remove a player:");
        Scanner scan = new Scanner(System.in);
        String command = scan.next();
        if (command.equals("a")) {
            System.out.println("player to add");
            adding("currentTeam");
        } else if (command.equals("m")) {
            System.out.println("modify a player");
            editPlayer();
        } else if (command.equals("r")) {
            removing("currentTeam");
        }
    }

    // EFFECT: edit a player's field
    public void editPlayer() {
        System.out.println("Enter name of player you want to edit: \n");
        System.out.println("List of current players: \n");

        for (String p : smallTeam.listPlayer()) {
            System.out.println(p + "\n");
        }

        System.out.println("Enter name of player to be edit: ");
        Scanner scan = new Scanner(System.in);
        String playerName = scan.next();
        Player toEdit;
        toEdit = smallTeam.returnPlayerCurrentTeam(playerName);

        playerEditing(toEdit);

    }

    // EFFECT: edit a player's information
    public void playerEditing(Player player) {
        Boolean keepGoing = true;
        String choices = null;
        Integer updateNumb = 0;
        helperPlayerEditting(true, choices, updateNumb, player);
    }

    // EFFECT: helper to shorten the playerEditting function
    public void helperPlayerEditting(boolean going, String choices, Integer updateNumb, Player player) {
        while (going) {
            System.out.println("Select player's aspect that you want to update: \n "
                    + "g goals, c conceal, w games won, l games lost, p pos, q quit");
            choices = input.next();
            if (choices.equals("g")) {
                updateNumb = input.nextInt();
                player.setGoals(updateNumb);
            } else if (choices.equals("c")) {
                updateNumb = input.nextInt();
                player.setConceal(updateNumb);
            } else if (choices.equals("w")) {
                updateNumb = input.nextInt();
                player.setWon(updateNumb);
            } else if (choices.equals("l")) {
                updateNumb = input.nextInt();
                player.setLost(updateNumb);
            } else if (choices.equals("p")) {
                choices = input.next();
                player.setPosition(choices);
            } else if (choices.equals("q")) {
                going = false;
            }
        }
    }

    //EFFECT: show player's statistics
    public void displayStatistics() {
        System.out.println("Please enter the player's name to display his stats: \n");
        String name = null;
        Scanner scan = new Scanner(System.in);
        name = scan.next();
        showPlayer(name);
    }

    // EFFECT: A helper to show player's statistics
    public void showPlayer(String name) {
        Player p;
        p = smallTeam.returnPlayerCurrentTeam(name);
        System.out.println("Player's Name: " + p.getName() + "\n ");
        System.out.println("Player's Position: " + p.getPosition() + "\n ");
        System.out.println("Player's Goals: " + p.getGoals() + "\n ");
        System.out.println("Player's Conceal: " + p.getConceal() + "\n ");
        System.out.println("Player's Game won: " + p.getGameWon() + "\n ");
        System.out.println("Player's Game lost: " + p.getGameLost() + "\n ");
    }

}





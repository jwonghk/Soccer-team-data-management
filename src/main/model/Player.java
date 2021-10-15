package model;

public class Player {
    // delete or rename this class!
    private String name;
    private Integer goals;
    private Integer conceal;
    private Integer gameWon;
    private Integer gameLost;
    private String position;


    // Constructor for a player
    public Player(String name, Integer goals, Integer conceal,Integer gameWon,
                  Integer gameLost, String pos) {

        this.name = name;
        this.goals = goals;
        this.conceal = conceal;
        this.gameWon = gameWon;
        this.gameLost = gameLost;
        this.position = pos;
    }

    // EFFECT: Return the name of the player
    public String getName() {
        return name;
    }

    // EFFECT: Return number of goals of the player
    public Integer getGoals() {
        return goals;
    }

    // EFFECT: Return the number of conceals of the player
    public Integer getConceal() {
        return conceal;
    }

    // EFFECT: Return the number of game won of the player
    public Integer getGameWon() {
        return gameWon;
    }

    // EFFECT: Return the number of game lost of the player
    public Integer getGameLost() {
        return gameLost;
    }

    // EFFECT: Return the position of the player
    public String getPosition() {
        return position;
    }

    // Modifies: this
    // Effect: update number of goals of player
    public void setGoals(int goal) {
        this.goals = goal;
    }


    // Modifies: this
    // Effect: update number of goals of player
    public void setConceal(int conceal) {
        this.conceal = conceal;
    }

    // Modifies: this
    // Effect: update number game won
    public void setWon(int won) {
        this.gameWon = won;
    }

    // Modifies: this
    // Effect: update number game lost
    public void setLost(int lost) {
        this.gameLost = lost;
    }

    // Modifies: this
    // Effect: update position of player
    public void setPosition(String pos) {
        this.position = pos;
    }

}

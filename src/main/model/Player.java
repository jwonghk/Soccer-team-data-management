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

    // Return the name of the player
    public String getName() {
        return name;
    }

    public Integer getGoals() {
        return goals;
    }

    public Integer getConceal() {
        return conceal;
    }


    public Integer getGameWon() {
        return gameWon;
    }

    public Integer getGameLost() {
        return gameLost;
    }

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

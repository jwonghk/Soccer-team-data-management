package model;

import org.json.JSONObject;


// this class contains some profile of players
public class SmallThingy {

    private String position;
    private Integer goals;
    private Integer conceals;
    private Integer gameWon;
    private Integer gameLost;




    // Instatiates a SamllThingy object
    public SmallThingy(String position, Integer goals, Integer conceals, Integer gameWon, Integer gameLost) {
        this.position = position;
        this.goals = goals;
        this.conceals = conceals;
        this.gameWon = gameWon;
        this.gameLost = gameLost;

    }

    // EFFECT: this return a JSONObject that contains the player profile
    public JSONObject playerProfile() {
        JSONObject playerprofile = new JSONObject();
        playerprofile.put("position", this.position);
        playerprofile.put("goal", this.goals);
        playerprofile.put("conceals", this.conceals);
        playerprofile.put("gameWon", this.gameWon);
        playerprofile.put("gameLost", this.gameLost);
        return playerprofile;

    }

    //EFFECT: return position
    public String getPosition() {
        return this.position;
    }

    //EFFECT: return goals scored
    public Integer getGoals() {
        return goals;
    }

    //EFFECT: return number of conceals
    public Integer getConceals() {
        return conceals;
    }

    //EFFECT: return number of game won
    public Integer getGameWon() {
        return gameWon;
    }

    //EFFECT: return number of game lost
    public Integer getGameLost() {
        return gameLost;
    }



}

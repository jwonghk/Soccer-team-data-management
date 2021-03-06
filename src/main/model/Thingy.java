package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

// citation: codes here are borrowed or modified based on the demo application
//          from CPSC 210
//   https://edge.edx.org/courses/course-v1:UBC+CPSC210+all/courseware

// Represents a thingy having a name and a category
// ****thingy actually represents player***** in my dataset, and name of the player will
//    be used as a Key to the JSONObject with player name as key, and playerProfile as value.
//    Thingy is used as a name, because persistance feature is implemented and is based upon
//   the example given in the course and they call the class Thingy
public class Thingy implements Writable {
    private String name;
    private Integer gameWon;
    private Integer gameLost;
    private Integer goals;
    private Integer conceal;
    private String position;

    private JSONObject playerProfile;


    // EFFECTS: constructs a thingy with a name and playerProfile
    // comments: thingy actually is an object that represents a player
    //           together with his/her profile status
    public Thingy(String name, JSONObject playerProfile) {
        this.name = name;
        this.gameWon = playerProfile.getInt("gameWon");
        this.gameLost = playerProfile.getInt("gameLost");
        this.goals = playerProfile.getInt("goals");
        this.conceal = playerProfile.getInt("conceals");
        this.position = playerProfile.getString("position");
        this.playerProfile = playerProfile;
        setPlayerProfile();
    }

    // EFFECT: return the name of player
    public String getThingName() {
        return this.name;
    }

    // EFFECTS: return playerprofile
    public JSONObject returnPlayerProfile() {
        return this.playerProfile;
    }

    //EFFECT: put the key and value into the playerProfile JSONObject
    public void setPlayerProfile() {
        playerProfile.put("goals", this.goals);
        playerProfile.put("conceals", this.conceal);
        playerProfile.put("gameWon", this.gameWon);
        playerProfile.put("gameLost", this.gameLost);
        playerProfile.put("position", this.position);

    }

    // EFFECTS: returns string representation of this thingy
//    public String toString() {
//        return position + ": " + name;
//    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put(name, playerProfile);
        return json;
    }


    // MODIFIES: this
    // EFFECT: set all fields at once
    public void setAllFieldsOfPlayer(Integer goals,Integer conceal,Integer gameWon,Integer gameLost,
                                     String position) throws Exception {
        setGoals(goals);
        setConceal(conceal);
        setGameWon(gameWon);
        setGameLost(gameLost);
        setPosition(position);
        setPlayerProfile();
    }

    // MODIFIES: this
    // EFFECT: change the goals field of Thingy (thingy is just a player object)
    public void setGoals(Integer goals) throws Exception {
        if (goals < 0) {
            throw new Exception();
        }

        this.goals = goals;
        EventLog.getInstance().logEvent(new Event("Player " + this.name + "'s goal field is set"));
        setPlayerProfile();
    }

    // MODIFIES: this
    // EFFECT: set the name of Thingy (thingy is a player)
    public void setName(String name) {
        this.name = name;
        EventLog.getInstance().logEvent(new Event("Player " + name + "'s name field is set"));
    }

    // MODIFIES: this
    // EFFECT: change the conceal field of Thingy (thingy is just a player object)
    public void setConceal(Integer conceals) throws Exception {
        if (conceals < 0) {
            throw new Exception();
        }
        this.conceal = conceals;
        EventLog.getInstance().logEvent(new Event("Player " + this.name + "'s conceal field is set"));
        setPlayerProfile();
    }

    // MODIFIES: this
    // EFFECT: change the gameWon field of Thingy (thingy is just a player object)
    public void setGameWon(Integer gameWon) throws Exception {
        if (gameWon < 0) {
            throw new Exception();
        }
        this.gameWon = gameWon;
        EventLog.getInstance().logEvent(new Event("Player " + this.name + "'s gameWon field is set"));
        setPlayerProfile();
    }

    // MODIFIES: this
    // EFFECT: change the gameLost field of Thingy (thingy is just a player object)
    public void setGameLost(Integer gameLost) throws Exception {
        if (gameLost < 0) {
            throw new Exception();
        }
        this.gameLost = gameLost;
        EventLog.getInstance().logEvent(new Event("Player " + this.name + "'s gameLost field is set"));
        setPlayerProfile();
    }

    // MODIFIES: this
    // EFFECT: change the position field of Thingy (thingy is just a player object)
    public void setPosition(String position) throws Exception {
        if (!(position.equals("midfield")
                || position.equals("striker")
                || position.equals("defender") || position.equals("goal keeper"))) {
            throw new Exception();
        }
        this.position = position;
        EventLog.getInstance().logEvent(new Event("Player " + this.name + "'s position field is set"));
        setPlayerProfile();
    }


    // EFFECT: Return number of goals of the player
    public Integer getGoals() {
        return this.goals;
    }

    // EFFECT: Return the number of conceals of the player
    public Integer getConceal() {
        return this.conceal;
    }

    // EFFECT: Return the number of game won of the player
    public Integer getGameWon() {
        return this.gameWon;
    }

    // EFFECT: Return the number of game lost of the player
    public Integer getGameLost() {
        return this.gameLost;
    }

    // EFFECT: Return the position of the player
    public String getPosition() {
        return this.position;
    }


}

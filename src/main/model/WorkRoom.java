package model;

import org.json.JSONArray;
import org.json.JSONObject;
import persistence.Writable;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;

// Represents a workroom having a collection of thingies (collection of thingies are the collection of
// players in my project). WorkRoom is a manager consisting of the manager and a list of thingies (i.e.
// list of players).
// This class is for managing workoom's field and return fields value

// citation: codes here are borrowed or modified based on the demo application
//          from CPSC 210
//   https://edge.edx.org/courses/course-v1:UBC+CPSC210+all/courseware
public class WorkRoom implements Writable {
    private String name;
    private List<Thingy> thingies;

    // EFFECTS: constructs workroom with a name and empty list of thingies
    public WorkRoom(String name) {
        this.name = name;
        this.thingies = new ArrayList<>();
    }

    // Effect: return name of player
    public String getName() {
        return name;
    }


    // MODIFIES: this
    // EFFECTS: adds thingy to this workroom
    public void addThingy(Thingy thingy) {
        thingies.add(thingy);
        EventLog.getInstance().logEvent(new Event("A new player called "
                + thingy.getThingName() + " has been added to the database "
                ));
    }

    // EFFECTS: returns an unmodifiable list of thingies in this workroom
    public List<Thingy> getThingies() {
        return thingies;
    }

    // EFFECT: set the thingies field
    // MODIFIES: this, PlayerList
    public void setThingies(List<Thingy> playerList) {
        this.thingies = playerList;
    }



    // EFFECTS: returns number of thingies in this workroom
    public int numThingies() {
        return thingies.size();
    }

    @Override
    public JSONObject toJson() {
        JSONObject json = new JSONObject();
        json.put("name", name);
        json.put("players", thingiesToJson());
        return json;
    }

    // EFFECTS: returns things in this workroom as a JSON array
    private JSONArray thingiesToJson() {
        JSONArray jsonArray = new JSONArray();

        for (Thingy t : thingies) {
            jsonArray.put(t.toJson());
        }

        return jsonArray;
    }



    // EFFECT: this is to remove player
    // MODIFIES: this
    public void playerRemoval(List<Thingy> thingylist, String playerRemovedName) {
        List<Thingy> newPlayerListAfterRemovalofAPlayer = new ArrayList<Thingy>();
        for (Thingy thingy : thingylist) {
            if (!thingy.getThingName().equals(playerRemovedName)) {
                newPlayerListAfterRemovalofAPlayer.add(thingy);
            }
        }

        // EventLog: capture the instance when a Player is removed
        EventLog.getInstance().logEvent(new Event("Player " + playerRemovedName
                + " is removed"));


        this.setThingies(newPlayerListAfterRemovalofAPlayer);
    }

    // EFFECT: this is to add a player to the current WorkRoom
    // MODIFIES: this
    public void playerAdded(Thingy player, String nameOfPlayer) {
        this.addThingy(player);

         //EventLog: capture the instance when a new Player is added to the current list of players
        EventLog.getInstance().logEvent(new Event("A new player " + nameOfPlayer
                + " has been added to the current list of players!"));

    }
}

